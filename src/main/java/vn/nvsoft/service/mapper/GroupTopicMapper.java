package vn.nvsoft.service.mapper;

import vn.nvsoft.domain.*;
import vn.nvsoft.service.dto.GroupTopicDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link GroupTopic} and its DTO {@link GroupTopicDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface GroupTopicMapper extends EntityMapper<GroupTopicDTO, GroupTopic> {



    default GroupTopic fromId(Long id) {
        if (id == null) {
            return null;
        }
        GroupTopic groupTopic = new GroupTopic();
        groupTopic.setId(id);
        return groupTopic;
    }
}
