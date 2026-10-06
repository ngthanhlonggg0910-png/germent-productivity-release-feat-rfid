package vn.nvsoft.service.mapper;


import vn.nvsoft.domain.*;
import vn.nvsoft.service.dto.DeviceTokenDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link DeviceToken} and its DTO {@link DeviceTokenDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DeviceTokenMapper extends EntityMapper<DeviceTokenDTO, DeviceToken> {



    default DeviceToken fromId(Long id) {
        if (id == null) {
            return null;
        }
        DeviceToken deviceToken = new DeviceToken();
        deviceToken.setId(id);
        return deviceToken;
    }
}
