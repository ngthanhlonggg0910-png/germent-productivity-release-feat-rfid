package vn.nvsoft.service.mqtt.factory;

import org.eclipse.paho.mqttv5.common.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.nvsoft.domain.enumeration.GroupButtonType;
import vn.nvsoft.mqtt.model.HomeAssistantDiscoverySensorModel;
import vn.nvsoft.mqtt.model.NVAssistantConst;
import vn.nvsoft.service.dto.GroupTopicDTO;
import vn.nvsoft.service.dto.NVButtonDTO;
import vn.nvsoft.service.mapper.JsonMapper;
import vn.nvsoft.service.mqtt.DeviceMgtService;
import vn.nvsoft.service.mqtt.IMessageListener;

public class DiscoverySewingMessageListener implements IMessageListener {
    private static final Logger log = LoggerFactory.getLogger(DiscoverySewingMessageListener.class.getName());

    private static final String BUTTON_CMD_FORMAT = "/stat/BUTTON%dT";
    private static final String AV_TOPIC = "/tele/LWT";
    private static final String PL_ON_OFF = "{\"TRIG\":\"SINGLE\"}";

    private GroupTopicDTO m_groupTopicDTO;
    private String m_topicPre;
    private DeviceMgtService m_deviceMgtService;

    public DiscoverySewingMessageListener(GroupTopicDTO groupTopicDTO, DeviceMgtService deviceMgtService) {
        m_groupTopicDTO = groupTopicDTO;
        m_topicPre = groupTopicDTO.getName().substring(0, groupTopicDTO.getName().indexOf("#"));
        m_deviceMgtService = deviceMgtService;
    }

    @Override
    public boolean isHandleMessage(String topic, MqttMessage mqttMessage) {
        return topic.startsWith(m_topicPre);
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
        Received msg from topic:homeassistant/sensor/756F7C_status/config msg:
        {
            "name": "Tasmota status",
            "stat_t": "nvsoft/sewing/tasmota_756F7C/tele/HASS_STATE",
            "avty_t": "nvsoft/sewing/tasmota_756F7C/tele/LWT",
            "pl_avail": "Online",
            "pl_not_avail": "Offline",
            "json_attr_t": "nvsoft/sewing/tasmota_756F7C/tele/HASS_STATE",
            "unit_of_meas": "%",
            "val_tpl": "{{value_json['RSSI']}}",
            "ic": "mdi:information-outline",
            "uniq_id": "756F7C_status",
            "dev": {
                "ids": ["756F7C"],
                "name": "Tasmota",
                "mdl": "NVSoft",
                "sw": "8.4.0(tasmota)",
                "mf": "Tasmota"
            }
        }
        Compute cmt_t to nvsoft/sewing/tasmota_756F7C/stat/BUTTON3T by replace /tele/LWT
        */
        HomeAssistantDiscoverySensorModel buttonModel = JsonMapper.convertToObject(payload, HomeAssistantDiscoverySensorModel.class);
        if (buttonModel != null && buttonModel.getAvty_t().startsWith(NVAssistantConst.SEWING_TOPIC_PRE)) {
            for (int i = 1; i <= 3; i++) {
                NVButtonDTO nvButtonDTO = new NVButtonDTO();
                nvButtonDTO.setTeleTopic(buttonModel.getStat_t());
                nvButtonDTO.setAvtyTopic(buttonModel.getAvty_t());
                nvButtonDTO.setPlAvail(buttonModel.getPl_avail());
                nvButtonDTO.setPlNotAvail(buttonModel.getPl_not_avail());
                nvButtonDTO.setCmndTopic(getCmdTopic(buttonModel, i));
                nvButtonDTO.setPlOff(PL_ON_OFF);
                nvButtonDTO.setPlOn(PL_ON_OFF);
                nvButtonDTO.setUniqId(buttonModel.getUniq_id());
                nvButtonDTO.setGroupType(GroupButtonType.SEWING);
                nvButtonDTO.setStatTopic(nvButtonDTO.getCmndTopic());
                nvButtonDTO.setName(getButtonName(buttonModel, i));
                nvButtonDTO.setDiscoveredOnTopic(topic);
                m_deviceMgtService.discoveredSwitch(nvButtonDTO);
            }
        }
    }

    private String getButtonName(HomeAssistantDiscoverySensorModel buttonModel, int index) {
        int start = NVAssistantConst.SEWING_TOPIC_PRE.length();
        int end = buttonModel.getAvty_t().lastIndexOf(AV_TOPIC);
        if (end > start) {
            return buttonModel.getAvty_t().substring(start, end) + "_" + index;
        }
        return String.format(BUTTON_CMD_FORMAT, index);
    }

    private String getCmdTopic(HomeAssistantDiscoverySensorModel buttonModel, int index) {
        String cmd = String.format(BUTTON_CMD_FORMAT, index);
        return buttonModel.getAvty_t().replace(AV_TOPIC, cmd);
    }

    @Override
    public void connectionClosed() {

    }
}
