package vn.nvsoft.service.mqtt.factory;

import com.google.common.collect.Lists;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.nvsoft.service.dto.GroupTopicDTO;
import vn.nvsoft.service.mqtt.DeviceMgtService;
import vn.nvsoft.service.mqtt.IMessageListener;

public class MessageListenerFactory {
    private static final Logger log = LoggerFactory.getLogger(MessageListenerFactory.class.getName());

    public static List<IMessageListener> createMessageListener(GroupTopicDTO groupTopicDTO, DeviceMgtService deviceMgtService) {
        switch (groupTopicDTO.getTopicType()) {
            case DISCOVERY:
                return Lists.newArrayList(new DiscoveryMessageListener(groupTopicDTO, deviceMgtService),
                                        new DiscoverySewingMessageListener(groupTopicDTO, deviceMgtService),
                                        new UpdateSewingDeviceMessageListener(groupTopicDTO, deviceMgtService));
            case STAT:
            case TELE:
                return Lists.newArrayList(new ButtonUpdateStatPowerMessageListener(groupTopicDTO, deviceMgtService),
                                    new DeviceButtonUpdateAvailMessageListener(groupTopicDTO, deviceMgtService),
                                    new DeviceHassStateMessageListener(groupTopicDTO, deviceMgtService),
                                    new ButtonUpdateStatButtonMessageListener(groupTopicDTO, deviceMgtService));
            default:
                log.warn("Can not find message listener for topic type {}", groupTopicDTO.getTopicType());
                return null;
        }
    }
}
