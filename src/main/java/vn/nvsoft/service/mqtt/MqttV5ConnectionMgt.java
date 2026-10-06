package vn.nvsoft.service.mqtt;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.annotation.PostConstruct;
import org.eclipse.paho.mqttv5.client.IMqttToken;
import org.eclipse.paho.mqttv5.client.MqttAsyncClient;
import org.eclipse.paho.mqttv5.client.MqttCallback;
import org.eclipse.paho.mqttv5.client.MqttDisconnectResponse;
import org.eclipse.paho.mqttv5.client.persist.MqttDefaultFilePersistence;
import org.eclipse.paho.mqttv5.common.MqttException;
import org.eclipse.paho.mqttv5.common.MqttMessage;
import org.eclipse.paho.mqttv5.common.MqttPersistenceException;
import org.eclipse.paho.mqttv5.common.packet.MqttProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import vn.nvsoft.domain.enumeration.TopicType;
import vn.nvsoft.mqtt.MqttOption;
import vn.nvsoft.mqtt.MqttV5Connection;
import vn.nvsoft.mqtt.MqttV5Publish;
import vn.nvsoft.mqtt.MqttV5Subscribe;
import vn.nvsoft.service.GroupTopicService;
import vn.nvsoft.service.dto.GroupTopicDTO;
import vn.nvsoft.service.mqtt.factory.MessageListenerFactory;

@Service
public class MqttV5ConnectionMgt implements MqttCallback {
    private final Logger log = LoggerFactory.getLogger(MqttV5ConnectionMgt.class.getName());

    private final MqttOption m_mqttOption;
    private final GroupTopicService m_groupTopicService;
    private final DeviceMgtService m_deviceMgtService;

    private MqttAsyncClient m_v5Client;
    private MqttV5Connection m_v3ConnectionParameters;
    private List<IMessageListener> m_messageListeners = new ArrayList<>();
    private ThreadPoolExecutor m_executorPool;

    public MqttV5ConnectionMgt(MqttOption mqttOption, GroupTopicService groupTopicService, DeviceMgtService deviceMgtService) {
        m_mqttOption = mqttOption;
        m_groupTopicService = groupTopicService;
        m_deviceMgtService = deviceMgtService;
        m_deviceMgtService.setMqttV5ConnectionMgt(this);
    }

//    @PostConstruct //TODO hien tai ko dung den nen comment out
    public void init() throws URISyntaxException, MqttException {
        log.info("Create thread pool with option corePoolSize: {}, maximumPoolSize: {}, keepAliveTime: {}", m_mqttOption.getCorePoolSize(), m_mqttOption.getMaximumPoolSize(),
            m_mqttOption.getKeepAliveTime());
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        m_executorPool = new ThreadPoolExecutor(m_mqttOption.getCorePoolSize(), m_mqttOption.getMaximumPoolSize(), m_mqttOption.getKeepAliveTime(), TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(), threadFactory);

        registerMessageListener();

        m_v3ConnectionParameters = new MqttV5Connection(m_mqttOption);
        m_v5Client = new MqttAsyncClient(m_v3ConnectionParameters.getHostURI(), m_v3ConnectionParameters.getClientID(), new MqttDefaultFilePersistence());
        m_v5Client.setCallback(this);
        log.info("Connecting to MQTT Broker: {}, Client ID: {}", m_v5Client.getServerURI(), m_v5Client.getClientId());
        IMqttToken connectToken = m_v5Client.connect(m_v3ConnectionParameters.getConOpts());
    }

    private void registerMessageListener() {
        List<GroupTopicDTO> groupTopics = m_groupTopicService.findAll();
        if (groupTopics != null && !groupTopics.isEmpty()) {
            for (GroupTopicDTO groupTopic : groupTopics) {
                List<IMessageListener> messageListeners = MessageListenerFactory.createMessageListener(groupTopic, m_deviceMgtService);
                if (messageListeners != null) {
                    m_messageListeners.addAll(messageListeners);
                }
            }
        } else {
            log.warn("The group topic is empty!!!");
        }
    }

    private void subscribeOnTopic() {
        List<GroupTopicDTO> groupTopics = m_groupTopicService.findAll();
        if (groupTopics != null && !groupTopics.isEmpty()) {
            for (GroupTopicDTO groupTopic : groupTopics) {
                if (TopicType.DISCOVERY.equals(groupTopic.getTopicType()) || TopicType.STAT.equals(groupTopic.getTopicType())
                    || TopicType.TELE.equals(groupTopic.getTopicType())) {
                    MqttV5Subscribe subscribe = new MqttV5Subscribe(groupTopic.getName(), m_mqttOption);
                    log.info("Subscribing to {}, with Qos {}", subscribe.getTopic(), subscribe.getQos());
                    try {
                        m_v5Client.subscribe(subscribe.getTopic(), subscribe.getQos());
                    } catch (MqttException e) {
                        log.error("Subscribing to {} error: ", groupTopic.getName(), e);
                    }
                }
            }
        } else {
            log.warn("The group topic is empty!!!");
        }
    }

    public void publishMessage(String message, String topic) {
        try {
            log.debug("Publish to topic {} msg {}", topic, message);
            MqttV5Publish mqttV5Publish = new MqttV5Publish(m_mqttOption, topic, message);
            publishMessage(mqttV5Publish.getPayload(), mqttV5Publish.getQos(), mqttV5Publish.isRetain(), mqttV5Publish.getTopic());
        } catch (MqttPersistenceException e) {
            log.warn("Publish message {} to topic {} error: ", e);
        } catch (MqttException e) {
            log.warn("Publish message {} to topic {} error: ", e);
        }
    }

    /**
     * Helper function to publish a message.
     *
     * @param payload
     * @param qos
     * @param retain
     * @param topic
     * @throws MqttPersistenceException
     * @throws MqttException
     */
    private void publishMessage(byte[] payload, int qos, boolean retain, String topic) throws MqttPersistenceException, MqttException {
        MqttMessage v5Message = new MqttMessage(payload);
        v5Message.setQos(qos);
        v5Message.setRetained(retain);
        IMqttToken deliveryToken = m_v5Client.publish(topic, v5Message);
    }

    @Override
    public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
        log.debug("Received msg on topic {}, id {}, Qos {}, is duplicate {}, retained {}, payload {}", topic, mqttMessage.getId(), mqttMessage.getQos(), mqttMessage.isDuplicate(),
            mqttMessage.isRetained(),
            mqttMessage.toString());
        try {
            for (IMessageListener messageListener : m_messageListeners) {
                if (messageListener.isHandleMessage(topic, mqttMessage)) {
                    m_executorPool.submit(new HandleMessageWorker(messageListener, topic, mqttMessage));
                }
            }
        } catch (Exception e) {
            log.warn("Handle message on topic {} error: ", topic, e);
        }
    }

    @Override
    public void connectComplete(boolean b, String s) {
        log.info("Open connection to MQTT broker success");
        subscribeOnTopic();
    }

    @Override
    public void authPacketArrived(int i, MqttProperties mqttProperties) {

    }

    @Override
    public void deliveryComplete(IMqttToken iMqttToken) {
        log.info("delivery complete {}", iMqttToken.toString());
    }

    @Override
    public void disconnected(MqttDisconnectResponse mqttDisconnectResponse) {
        log.warn("The connection to MQTT server was closed with reason {} ", mqttDisconnectResponse.getReasonString(), mqttDisconnectResponse.getException());
        try {
            for (IMessageListener messageListener : m_messageListeners) {
                m_executorPool.submit(new HandleMqttClosedConnection(messageListener));
            }
        } catch (Exception handlerListenerException) {
            log.warn("Handle closed MQTT error: ", handlerListenerException);
        }
    }

    @Override
    public void mqttErrorOccurred(MqttException e) {
        log.warn("MQTT error occurred: ", e);
    }


    class HandleMessageWorker implements Runnable {
        IMessageListener messageHandler;
        String topic;
        MqttMessage mqttMessage;

        public HandleMessageWorker(IMessageListener messageHandler, String topic, MqttMessage mqttMessage) {
            this.messageHandler = messageHandler;
            this.topic = topic;
            this.mqttMessage = mqttMessage;
        }

        @Override
        public void run() {
            this.messageHandler.handleMessage(topic, mqttMessage);
        }
    }

    class HandleMqttClosedConnection implements Runnable {
        IMessageListener messageHandler;

        public HandleMqttClosedConnection(IMessageListener messageHandler) {
            this.messageHandler = messageHandler;
        }

        @Override
        public void run() {
            messageHandler.connectionClosed();
        }
    }


}
