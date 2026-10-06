package vn.nvsoft.service.mqtt.factory;

import org.eclipse.paho.mqttv5.common.MqttMessage;
import vn.nvsoft.mqtt.model.NVAssistantConst;
import vn.nvsoft.service.dto.GroupTopicDTO;
import vn.nvsoft.service.mqtt.DeviceMgtService;
import vn.nvsoft.service.mqtt.IMessageListener;

/**
 * This listener handle message on topic nvsoft/#/stat/POWERx
 * The payload message is a string not json object
 */

public class ButtonUpdateStatPowerMessageListener implements IMessageListener {

    private DeviceMgtService m_deviceMgtService;

    public ButtonUpdateStatPowerMessageListener(GroupTopicDTO groupTopicDTO, DeviceMgtService deviceMgtService) {
        m_deviceMgtService = deviceMgtService;
    }

    @Override
    public boolean isHandleMessage(String topic, MqttMessage mqttMessage) {
        return !topic.startsWith(NVAssistantConst.SEWING_TOPIC_PRE) && topic.contains(NVAssistantConst.STAT_POWER_TOPIC_PRE) && !MqttMessageUtil.isJsonObject(mqttMessage);
    }

    @Override
    public void handleMessage(String topic, MqttMessage mqttMessage) {
        m_deviceMgtService.updateButtonState(topic, mqttMessage.toString().trim());
    }

    @Override
    public void connectionClosed() {

    }

}
