package vn.nvsoft.service.mqtt.factory;

import org.eclipse.paho.mqttv5.common.MqttMessage;
import vn.nvsoft.mqtt.model.NVAssistantConst;
import vn.nvsoft.service.dto.GroupTopicDTO;
import vn.nvsoft.service.mqtt.DeviceMgtService;
import vn.nvsoft.service.mqtt.IMessageListener;

/**
 * Listen on topic nvsoft/#/tele/LWT to detect device/button Online or Offline
 */
public class DeviceButtonUpdateAvailMessageListener implements IMessageListener {

    private DeviceMgtService m_deviceMgtService;

    public DeviceButtonUpdateAvailMessageListener(GroupTopicDTO groupTopicDTO, DeviceMgtService deviceMgtService) {
        m_deviceMgtService = deviceMgtService;
    }

    @Override
    public boolean isHandleMessage(String topic, MqttMessage mqttMessage) {
        return topic.endsWith(NVAssistantConst.LWT_PRE) && !MqttMessageUtil.isJsonObject(mqttMessage);
    }

    @Override
    public void handleMessage(String topic, MqttMessage mqttMessage) {
        m_deviceMgtService.updateDeviceAndButtonAvailState(topic, mqttMessage.toString().trim());
    }

    @Override
    public void connectionClosed() {

    }
}
