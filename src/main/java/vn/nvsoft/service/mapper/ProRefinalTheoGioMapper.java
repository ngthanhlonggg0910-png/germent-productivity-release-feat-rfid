package vn.nvsoft.service.mapper;

import vn.nvsoft.domain.*;
import vn.nvsoft.service.dto.ProRefinalTheoGioDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ProRefinal} and its DTO {@link ProRefinalTheoGioDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ProRefinalTheoGioMapper extends EntityMapper<ProRefinalTheoGioDTO, ProRefinal> {



    default ProRefinal fromId(Long id) {
        if (id == null) {
            return null;
        }
        ProRefinal proRefinal = new ProRefinal();
        proRefinal.setId(id);
        return proRefinal;
    }
}
