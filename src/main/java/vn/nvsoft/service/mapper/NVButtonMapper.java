package vn.nvsoft.service.mapper;

import vn.nvsoft.domain.*;
import vn.nvsoft.service.dto.NVButtonDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link NVButton} and its DTO {@link NVButtonDTO}.
 */
@Mapper(componentModel = "spring", uses = {ButtonTrackingMapper.class, DeviceMapper.class, NVButtonTrackingTypeMapper.class})
public interface NVButtonMapper extends EntityMapper<NVButtonDTO, NVButton> {

    @Mapping(source = "buttonTracking.id", target = "buttonTrackingId")
    @Mapping(source = "device.id", target = "deviceId")
    @Mapping(source = "device.name", target = "deviceName")
    @Mapping(source = "trackingType.id", target = "trackingTypeId")
    @Mapping(source = "trackingType.name", target = "trackingTypeName")
    NVButtonDTO toDto(NVButton nVButton);

    @Mapping(source = "buttonTrackingId", target = "buttonTracking")
    @Mapping(source = "deviceId", target = "device")
    @Mapping(source = "trackingTypeId", target = "trackingType")
    NVButton toEntity(NVButtonDTO nVButtonDTO);

    default NVButton fromId(Long id) {
        if (id == null) {
            return null;
        }
        NVButton nVButton = new NVButton();
        nVButton.setId(id);
        return nVButton;
    }
}
