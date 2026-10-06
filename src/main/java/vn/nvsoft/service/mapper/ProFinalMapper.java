package vn.nvsoft.service.mapper;

import vn.nvsoft.domain.*;
import vn.nvsoft.service.dto.ProFinalDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ProFinal} and its DTO {@link ProFinalDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ProFinalMapper extends EntityMapper<ProFinalDTO, ProFinal> {



    default ProFinal fromId(Long id) {
        if (id == null) {
            return null;
        }
        ProFinal proFinal = new ProFinal();
        proFinal.setId(id);
        return proFinal;
    }
}
