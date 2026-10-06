package vn.nvsoft.service.mapper;

import vn.nvsoft.domain.*;
import vn.nvsoft.service.dto.CauHinhZaloDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link CauHinhZalo} and its DTO {@link CauHinhZaloDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CauHinhZaloMapper extends EntityMapper<CauHinhZaloDTO, CauHinhZalo> {



    default CauHinhZalo fromId(Long id) {
        if (id == null) {
            return null;
        }
        CauHinhZalo cauHinhZalo = new CauHinhZalo();
        cauHinhZalo.setId(id);
        return cauHinhZalo;
    }
}
