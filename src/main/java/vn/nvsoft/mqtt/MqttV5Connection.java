package vn.nvsoft.mqtt;

import com.google.common.base.Strings;
import java.net.URISyntaxException;
import org.eclipse.paho.mqttv5.client.MqttConnectionOptions;
import org.eclipse.paho.mqttv5.common.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MqttV5Connection {
    private final Logger log = LoggerFactory.getLogger(MqttV5Connection.class.getName());

    private String hostURI;
    private String clientID;
    private MqttConnectionOptions conOpts = new MqttConnectionOptions();
    private boolean automaticReconnect = false;

    public MqttV5Connection(MqttOption mqttOptions) throws URISyntaxException {

        // Get the Host URI
        if (!Strings.isNullOrEmpty(mqttOptions.getHost())) {
            hostURI = mqttOptions.getHost();
        } else {
            log.warn("MQTT host setting is empty");
        }

        if (!Strings.isNullOrEmpty(mqttOptions.getClientId())) {
            clientID = mqttOptions.getClientId();
        } else {
            clientID = generateClientId();
        }
        log.debug("MQTT client id: {}", clientID);

        if (mqttOptions.getKeepAlive() != null) {
            conOpts.setKeepAliveInterval(mqttOptions.getKeepAlive());
        }

        if (!Strings.isNullOrEmpty(mqttOptions.getPassword())) {
            conOpts.setPassword(mqttOptions.getPassword().getBytes());
        }
        if (!Strings.isNullOrEmpty(mqttOptions.getUserName())) {
            conOpts.setUserName(mqttOptions.getUserName());
        }
        if (!Strings.isNullOrEmpty(mqttOptions.getWillPayload()) && !Strings.isNullOrEmpty(mqttOptions.getWillTopic())) {
            String willPayload = mqttOptions.getWillPayload();
            String willTopic = mqttOptions.getWillTopic();
            int qos = 0;
            boolean retained = false;
            if (mqttOptions.getWillQos() != null) {
                qos = mqttOptions.getWillQos();
            }
            if (mqttOptions.getWillRetain() != null) {
                retained = mqttOptions.getWillRetain();
            }
            MqttMessage willMessage = new MqttMessage(willPayload.getBytes(), qos, retained, null);
            conOpts.setWill(willTopic, willMessage);
        }

        if (mqttOptions.getCleanSession() != null) {
            conOpts.setCleanStart(mqttOptions.getCleanSession());
        }

        if (mqttOptions.getMaxInflight() != null) {
            conOpts.setReceiveMaximum(mqttOptions.getMaxInflight());
        }
        if (mqttOptions.getAutomaticReconnect() != null) {
            conOpts.setAutomaticReconnect(mqttOptions.getAutomaticReconnect());
            this.automaticReconnect = mqttOptions.getAutomaticReconnect();
        }

    }

    private String generateClientId() {
        long pid = Thread.currentThread().getId(); //ProcessHandle.current().pid();
        return "mqtt-client-" + pid;
    }

    public String getHostURI() {
        return hostURI;
    }

    public String getClientID() {
        return clientID;
    }

    public MqttConnectionOptions getConOpts() {
        return conOpts;
    }

    public boolean isAutomaticReconnectEnabled() {
        return this.automaticReconnect;
    }
}
