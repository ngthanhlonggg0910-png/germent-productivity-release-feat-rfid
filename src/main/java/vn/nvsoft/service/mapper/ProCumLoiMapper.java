package vn.nvsoft.service.mapper;

import vn.nvsoft.domain.*;
import vn.nvsoft.service.dto.ProCumLoiDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ProCumLoi} and its DTO {@link ProCumLoiDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ProCumLoiMapper extends EntityMapper<ProCumLoiDTO, ProCumLoi> {



    default ProCumLoi fromId(Long id) {
        if (id == null) {
            return null;
        }
        ProCumLoi proCumLoi = new ProCumLoi();
        proCumLoi.setId(id);
        return proCumLoi;
    }
}
