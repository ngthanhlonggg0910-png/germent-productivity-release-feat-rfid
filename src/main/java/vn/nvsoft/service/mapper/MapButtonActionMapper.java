package vn.nvsoft.service.mapper;

import vn.nvsoft.domain.*;
import vn.nvsoft.service.dto.MapButtonActionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MapButtonAction} and its DTO {@link MapButtonActionDTO}.
 */
@Mapper(componentModel = "spring", uses = {NVButtonTrackingTypeMapper.class})
public interface MapButtonActionMapper extends EntityMapper<MapButtonActionDTO, MapButtonAction> {

    @Mapping(source = "trackingType.id", target = "trackingTypeId")
    @Mapping(source = "trackingType.name", target = "trackingTypeName")
    MapButtonActionDTO toDto(MapButtonAction mapButtonAction);

    @Mapping(source = "trackingTypeId", target = "trackingType")
    MapButtonAction toEntity(MapButtonActionDTO mapButtonActionDTO);

    default MapButtonAction fromId(Long id) {
        if (id == null) {
            return null;
        }
        MapButtonAction mapButtonAction = new MapButtonAction();
        mapButtonAction.setId(id);
        return mapButtonAction;
    }
}
