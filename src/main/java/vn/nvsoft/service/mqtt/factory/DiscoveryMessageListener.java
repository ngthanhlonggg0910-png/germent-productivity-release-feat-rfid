package vn.nvsoft.service.mqtt.factory;

import org.eclipse.paho.mqttv5.common.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.nvsoft.mqtt.model.HomeAssistantDiscoverySwitchModel;
import vn.nvsoft.mqtt.model.NVAssistantConst;
import vn.nvsoft.service.dto.GroupTopicDTO;
import vn.nvsoft.service.dto.NVButtonDTO;
import vn.nvsoft.service.mapper.JsonMapper;
import vn.nvsoft.service.mapper.mqtt.NVButtonDiscoveryMapper;
import vn.nvsoft.service.mqtt.DeviceMgtService;
import vn.nvsoft.service.mqtt.IMessageListener;

public class DiscoveryMessageListener implements IMessageListener {
    private static final Logger log = LoggerFactory.getLogger(DiscoveryMessageListener.class.getName());

    private GroupTopicDTO m_groupTopicDTO;
    private String m_topicPre;
    private DeviceMgtService m_deviceMgtService;

    public DiscoveryMessageListener(GroupTopicDTO groupTopicDTO, DeviceMgtService deviceMgtService) {
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
            if (topic.contains(NVAssistantConst.SWITCH_TOPIC_PRE)) {
                parserSwitchPayload(topic, mqttMessage, payload);
            }
        }

    }

    private void parserSwitchPayload(String topic, MqttMessage mqttMessage, String payload) {
        /*
        Received msg from topic:homeassistant/switch/DBECE3_RL_1/config msg:
        {
            "name": "may_1 button_1",
            "stat_t": "xuong_1/sonoff_topic/tele/STATE",
            "avty_t": "xuong_1/sonoff_topic/tele/LWT",
            "pl_avail": "Online",
            "pl_not_avail": "Offline",
            "cmd_t": "xuong_1/sonoff_topic/cmnd/POWER1",
            "val_tpl": "{{value_json.POWER1}}",
            "pl_off": "OFF",
            "pl_on": "ON",
            "uniq_id": "DBECE3_RL_1",
            "dev": {
                "ids": ["DBECE3"]
            }
        }
        */
        HomeAssistantDiscoverySwitchModel buttonModel = JsonMapper.convertToObject(payload, HomeAssistantDiscoverySwitchModel.class);
        if (buttonModel != null) {
            NVButtonDTO nvButtonDTO = NVButtonDiscoveryMapper.INSTANCE.toDto(buttonModel);
            nvButtonDTO.setDiscoveredOnTopic(topic);
            m_deviceMgtService.discoveredSwitch(nvButtonDTO);
        }
    }

    @Override
    public void connectionClosed() {

    }
}
