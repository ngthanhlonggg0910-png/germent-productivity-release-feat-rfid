package vn.nvsoft.service.mapper;

import vn.nvsoft.domain.*;
import vn.nvsoft.service.dto.NVButtonTrackingTypeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link NVButtonTrackingType} and its DTO {@link NVButtonTrackingTypeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface NVButtonTrackingTypeMapper extends EntityMapper<NVButtonTrackingTypeDTO, NVButtonTrackingType> {



    default NVButtonTrackingType fromId(Long id) {
        if (id == null) {
            return null;
        }
        NVButtonTrackingType nVButtonTrackingType = new NVButtonTrackingType();
        nVButtonTrackingType.setId(id);
        return nVButtonTrackingType;
    }
}
