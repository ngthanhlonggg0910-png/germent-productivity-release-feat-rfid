package vn.nvsoft.service.mapper;

import vn.nvsoft.domain.*;
import vn.nvsoft.service.dto.ProRefinalDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ProRefinal} and its DTO {@link ProRefinalDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ProRefinalMapper extends EntityMapper<ProRefinalDTO, ProRefinal> {



    default ProRefinal fromId(Long id) {
        if (id == null) {
            return null;
        }
        ProRefinal proRefinal = new ProRefinal();
        proRefinal.setId(id);
        return proRefinal;
    }
}
