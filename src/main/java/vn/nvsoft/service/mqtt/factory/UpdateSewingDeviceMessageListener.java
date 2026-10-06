package vn.nvsoft.service.mqtt.factory;

import org.eclipse.paho.mqttv5.common.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.nvsoft.domain.enumeration.GroupButtonType;
import vn.nvsoft.mqtt.model.HomeAssistantDiscoverySensorModel;
import vn.nvsoft.mqtt.model.NVAssistantConst;
import vn.nvsoft.mqtt.model.TeleInfo;
import vn.nvsoft.service.dto.GroupTopicDTO;
import vn.nvsoft.service.dto.NVButtonDTO;
import vn.nvsoft.service.mapper.JsonMapper;
import vn.nvsoft.service.mqtt.DeviceMgtService;
import vn.nvsoft.service.mqtt.IMessageListener;

public class UpdateSewingDeviceMessageListener implements IMessageListener {
    private static final Logger log = LoggerFactory.getLogger(UpdateSewingDeviceMessageListener.class.getName());

    private static final String AV_TOPIC = "/tele/LWT";
    private static final String PL_ON_OFF = "{\"TRIG\":\"SINGLE\"}";
    private static final String POS_TOPIC= "/tele/INFO2";

    private GroupTopicDTO m_groupTopicDTO;
    private String m_topicPre;
    private DeviceMgtService m_deviceMgtService;

    public UpdateSewingDeviceMessageListener(GroupTopicDTO groupTopicDTO, DeviceMgtService deviceMgtService) {
        m_groupTopicDTO = groupTopicDTO;
        m_topicPre = groupTopicDTO.getName().substring(0, groupTopicDTO.getName().indexOf("#"));
        m_deviceMgtService = deviceMgtService;
    }

    @Override
    public boolean isHandleMessage(String topic, MqttMessage mqttMessage) {
        return topic.startsWith(m_topicPre) && topic.endsWith(POS_TOPIC);
    }

    @Override
    public void handleMessage(String topic, MqttMessage mqttMessage) {
        String payload = mqttMessage.toString().trim();
        if (!payload.isEmpty()) {
            if (topic.contains(NVAssistantConst.SENSOR_TOPIC_PRE)) {
                parserSwitchPayload(topic, mqttMessage, payload);
            }
        }

    }

    /**
     * Auto create NVButton for device which has button
     * Hard code it has 3 button
     * @param topic
     * @param mqttMessage
     * @param payload
     */
    private void parserSwitchPayload(String topic, MqttMessage mqttMessage, String payload) {
        /*
        Received msg from topic: nvsoft/sewing/nv57976_75CF4C/tele/INFO2 msg:
        {
	"WebServerMode": "Admin",
	"Hostname": "nv57976_75CF4C-3916",
	"IPAddress": "192.168.1.4"
}

        Compute cmt_t to nvsoft/sewing/tasmota_756F7C/stat/BUTTON3T by replace /tele/LWT
        */
        TeleInfo buttonModel = JsonMapper.convertToObject(payload, TeleInfo.class);
        if (buttonModel != null) {
            buttonModel.setReceivedOnTopic(topic);
            m_deviceMgtService.updateSewingDeviceInfo(buttonModel);
        }
    }

    private String getCmdTopic(TeleInfo buttonModel) {
        return buttonModel.getReceivedOnTopic().replace(POS_TOPIC, AV_TOPIC);
    }

    @Override
    public void connectionClosed() {

    }
}
