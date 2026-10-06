package vn.nvsoft.service.mqtt.factory;

import org.eclipse.paho.mqttv5.common.MqttMessage;
import vn.nvsoft.mqtt.model.NVAssistantConst;
import vn.nvsoft.service.dto.GroupTopicDTO;
import vn.nvsoft.service.mqtt.DeviceMgtService;
import vn.nvsoft.service.mqtt.IMessageListener;

/**
 * This listener handle message on topic nvsoft/sewing/tasmota_756F7C/stat/BUTTON3T = {"TRIG":"SINGLE"}
 * The payload message is a string not json object
 */

public class ButtonUpdateStatButtonMessageListener implements IMessageListener {

    private DeviceMgtService m_deviceMgtService;

    public ButtonUpdateStatButtonMessageListener(GroupTopicDTO groupTopicDTO, DeviceMgtService deviceMgtService) {
        m_deviceMgtService = deviceMgtService;
    }

    @Override
    public boolean isHandleMessage(String topic, MqttMessage mqttMessage) {
        return topic.contains(NVAssistantConst.STAT_BUTTON_TOPIC_PRE) && MqttMessageUtil.isJsonObject(mqttMessage);
    }

    @Override
    public void handleMessage(String topic, MqttMessage mqttMessage) {
        m_deviceMgtService.buttonTrigger(topic, mqttMessage.toString().trim());
    }

    @Override
    public void connectionClosed() {

    }

}
