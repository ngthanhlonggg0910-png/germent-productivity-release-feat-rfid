package vn.nvsoft.service.mapper;

import vn.nvsoft.domain.*;
import vn.nvsoft.service.dto.ButtonTrackingDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ButtonTracking} and its DTO {@link ButtonTrackingDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ButtonTrackingMapper extends EntityMapper<ButtonTrackingDTO, ButtonTracking> {


    @Mapping(target = "nVButton", ignore = true)
    ButtonTracking toEntity(ButtonTrackingDTO buttonTrackingDTO);

    default ButtonTracking fromId(Long id) {
        if (id == null) {
            return null;
        }
        ButtonTracking buttonTracking = new ButtonTracking();
        buttonTracking.setId(id);
        return buttonTracking;
    }
}
