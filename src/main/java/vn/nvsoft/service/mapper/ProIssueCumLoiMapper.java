package vn.nvsoft.service.mapper;

import vn.nvsoft.domain.*;
import vn.nvsoft.service.dto.ProIssueCumLoiDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ProIssueCumLoi} and its DTO {@link ProIssueCumLoiDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ProIssueCumLoiMapper extends EntityMapper<ProIssueCumLoiDTO, ProIssueCumLoi> {



    default ProIssueCumLoi fromId(Long id) {
        if (id == null) {
            return null;
        }
        ProIssueCumLoi proIssueCumLoi = new ProIssueCumLoi();
        proIssueCumLoi.setId(id);
        return proIssueCumLoi;
    }
}
