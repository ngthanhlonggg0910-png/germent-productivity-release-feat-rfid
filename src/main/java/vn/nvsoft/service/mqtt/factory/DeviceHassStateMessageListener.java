package vn.nvsoft.service.mqtt.factory;

import org.eclipse.paho.mqttv5.common.MqttMessage;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.nvsoft.mqtt.model.NVAssistantConst;
import vn.nvsoft.mqtt.model.SensorHassState;
import vn.nvsoft.service.dto.GroupTopicDTO;
import vn.nvsoft.service.mqtt.DeviceMgtService;
import vn.nvsoft.service.mqtt.IMessageListener;

public class DeviceHassStateMessageListener implements IMessageListener {
    private static final Logger log = LoggerFactory.getLogger(DeviceHassStateMessageListener.class.getName());

    private static final String VERSION = "Version";
    private static final String BUILD_DATE = "BuildDateTime";
    private static final String MODULE = "Module or Template";
    private static final String RESTART_REASON = "RestartReason";
    private static final String UP_TIME = "Uptime";
    private static final String HOST_NAME = "Hostname";
    private static final String IP_ADDRESS = "IPAddress";
    private static final String RSSI = "RSSI";
    private static final String SIGNAL = "Signal (dBm)";
    private static final String WIFI_LINK_COUNT = "WiFi LinkCount";
    private static final String WIFI_DOWN_TIME = "WiFi Downtime";
    private static final String MQTT_COUNT = "MqttCount";
    private static final String LOAD_AVG = "LoadAvg";
    private GroupTopicDTO m_groupTopicDTO;
    private DeviceMgtService m_deviceMgtService;

    public DeviceHassStateMessageListener(GroupTopicDTO groupTopicDTO, DeviceMgtService deviceMgtService) {
        m_groupTopicDTO = groupTopicDTO;
        m_deviceMgtService = deviceMgtService;
    }

    @Override
    public boolean isHandleMessage(String topic, MqttMessage mqttMessage) {
        return topic.endsWith(NVAssistantConst.HASS_STATE_PRE);
    }

    @Override
    public void handleMessage(String topic, MqttMessage mqttMessage) {
        String payload = mqttMessage.toString().trim();
        if (!payload.isEmpty()) {
            parserSwitchPayload(topic, mqttMessage, payload);
        }

    }

    private void parserSwitchPayload(String topic, MqttMessage mqttMessage, String payload) {
        /*
        nvsoft/light/light_E07DEE/tele/HASS_STATE
        {
            "Version": "8.4.0(tasmota)",
            "BuildDateTime": "2020-07-29T12:05:28",
            "Module or Template": "Sonoff Basic",
            "RestartReason": "External System",
            "Uptime": "0T00:10:02",
            "Hostname": "light_E07DEE-7662",
            "IPAddress": "192.168.3.5",
            "RSSI": "82",
            "Signal (dBm)": "-59",
            "WiFi LinkCount": 1,
            "WiFi Downtime": "0T00:01:44",
            "MqttCount": 1,
            "LoadAvg": 19
        }
        */
//        SensorHassState buttonModel = JsonMapper.convertToObject(payload, SensorHassState.class);
        try {
            SensorHassState buttonModel = new SensorHassState();
            JSONObject jsonObject = new JSONObject(payload);
            if (jsonObject.has(VERSION)) {
                buttonModel.setVersion(jsonObject.getString(VERSION));
            }

            if (jsonObject.has(BUILD_DATE)) {
                buttonModel.setBuildDateTime(jsonObject.getString(BUILD_DATE));
            }
            if (jsonObject.has(MODULE)) {
                buttonModel.setModuleOrTemplate(jsonObject.getString(MODULE));
            }
            if (jsonObject.has(RESTART_REASON)) {
                buttonModel.setRestartReason(jsonObject.getString(RESTART_REASON));
            }
            if (jsonObject.has(UP_TIME)) {
                buttonModel.setUptime(jsonObject.getString(UP_TIME));
            }
            if (jsonObject.has(HOST_NAME)) {
                buttonModel.setHostname(jsonObject.getString(HOST_NAME));
            }
            if (jsonObject.has(IP_ADDRESS)) {
                buttonModel.setiPAddress(jsonObject.getString(IP_ADDRESS));
            }
            if (jsonObject.has(RSSI)) {
                buttonModel.setrSSI(jsonObject.getString(RSSI));
            }
//            if (jsonObject.has(WIFI_LINK_COUNT)) {
//                buttonModel.setWiFiLinkCount(jsonObject.getString(WIFI_LINK_COUNT));
//            }
            if (jsonObject.has(WIFI_DOWN_TIME)) {
                buttonModel.setWiFiDowntime(jsonObject.getString(WIFI_DOWN_TIME));
            }
//            if (jsonObject.has(MQTT_COUNT)) {
//                buttonModel.setMqttCount(jsonObject.getString(MQTT_COUNT));
//            }
            String lwtTopic = topic.replace(NVAssistantConst.HASS_STATE_PRE, NVAssistantConst.LWT_PRE);
            m_deviceMgtService.updateDeviceDetails(lwtTopic, buttonModel);
        } catch (JSONException e) {
            log.warn("Parser json object error {}", payload, e);
        }

    }

    @Override
    public void connectionClosed() {

    }
}
