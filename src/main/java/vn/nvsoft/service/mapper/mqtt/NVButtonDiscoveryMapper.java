package vn.nvsoft.service.mapper.mqtt;

import java.util.Arrays;
import java.util.Optional;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import vn.nvsoft.domain.NVButton;
import vn.nvsoft.domain.enumeration.GroupButtonType;
import vn.nvsoft.mqtt.model.HomeAssistantDiscoverySwitchModel;
import vn.nvsoft.mqtt.model.NVAssistantConst;
import vn.nvsoft.service.dto.NVButtonDTO;

/**
 * Mapper for the entity {@link NVButton} and its DTO {@link NVButtonDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface NVButtonDiscoveryMapper {

    NVButtonDiscoveryMapper INSTANCE = Mappers.getMapper(NVButtonDiscoveryMapper.class);

    @Mapping(source = "stat_t", target = "teleTopic")
    @Mapping(source = "avty_t", target = "avtyTopic")
    @Mapping(source = "pl_avail", target = "plAvail")
    @Mapping(source = "pl_not_avail", target = "plNotAvail")
    @Mapping(source = "cmd_t", target = "cmndTopic")
    @Mapping(source = "pl_off", target = "plOff")
    @Mapping(source = "pl_on", target = "plOn")
    @Mapping(source = "uniq_id", target = "uniqId")
//    @Mapping(source = "dev.ids", target = "devIds")
    @Mapping(source = "stat_t", target = "groupType", qualifiedByName = "detectGroupButtonType")
    @Mapping(source = "cmd_t", target = "statTopic", qualifiedByName = "detectStatTopic")
    NVButtonDTO toDto(HomeAssistantDiscoverySwitchModel discoveryModel);

    @Named("detectGroupButtonType")
    static GroupButtonType detectGroupButtonType(String cmdTopic) {
        Optional<GroupButtonType> result =  Arrays.stream(GroupButtonType.values()).filter(groupButtonType -> cmdTopic.contains(groupButtonType.toString().toLowerCase())).findFirst();
        if (result.isPresent()) {
            return result.get();
        }
        return GroupButtonType.LIGHT;
    }

    @Named("detectStatTopic")
    static String detectStatTopic(String cmdTopic) {
        if (cmdTopic != null && cmdTopic.contains(NVAssistantConst.CMND_TOPIC_PRE)) {
            return cmdTopic.replaceFirst(NVAssistantConst.CMND_TOPIC_PRE, NVAssistantConst.STATUS_TOPIC_PRE);
        }
        return null;
    }
}
