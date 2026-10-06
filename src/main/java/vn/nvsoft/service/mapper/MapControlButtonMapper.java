package vn.nvsoft.service.mapper;

import vn.nvsoft.domain.*;
import vn.nvsoft.service.dto.MapControlButtonDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MapControlButton} and its DTO {@link MapControlButtonDTO}.
 */
@Mapper(componentModel = "spring", uses = {NVButtonMapper.class})
public interface MapControlButtonMapper extends EntityMapper<MapControlButtonDTO, MapControlButton> {

    @Mapping(source = "sourceButton.id", target = "sourceButtonId")
    @Mapping(source = "sourceButton.cmndTopic", target = "sourceButtonCmndTopic")
    @Mapping(source = "targetButton.id", target = "targetButtonId")
    @Mapping(source = "targetButton.cmndTopic", target = "targetButtonCmndTopic")
    MapControlButtonDTO toDto(MapControlButton mapControlButton);

    @Mapping(source = "sourceButtonId", target = "sourceButton")
    @Mapping(source = "targetButtonId", target = "targetButton")
    MapControlButton toEntity(MapControlButtonDTO mapControlButtonDTO);

    default MapControlButton fromId(Long id) {
        if (id == null) {
            return null;
        }
        MapControlButton mapControlButton = new MapControlButton();
        mapControlButton.setId(id);
        return mapControlButton;
    }
}
