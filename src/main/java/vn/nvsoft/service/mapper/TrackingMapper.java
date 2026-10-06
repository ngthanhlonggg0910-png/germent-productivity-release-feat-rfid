package vn.nvsoft.service.mapper;

import vn.nvsoft.domain.*;
import vn.nvsoft.service.dto.TrackingDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Tracking} and its DTO {@link TrackingDTO}.
 */
@Mapper(componentModel = "spring", uses = {NVButtonTrackingTypeMapper.class})
public interface TrackingMapper extends EntityMapper<TrackingDTO, Tracking> {

    @Mapping(source = "trackingType.id", target = "trackingTypeId")
    @Mapping(source = "trackingType.name", target = "trackingTypeName")
    TrackingDTO toDto(Tracking tracking);

    @Mapping(source = "trackingTypeId", target = "trackingType")
    Tracking toEntity(TrackingDTO trackingDTO);

    default Tracking fromId(Long id) {
        if (id == null) {
            return null;
        }
        Tracking tracking = new Tracking();
        tracking.setId(id);
        return tracking;
    }
}
