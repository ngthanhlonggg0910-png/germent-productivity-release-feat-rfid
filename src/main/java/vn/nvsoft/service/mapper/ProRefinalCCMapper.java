package vn.nvsoft.service.mapper;

import vn.nvsoft.domain.*;
import vn.nvsoft.service.dto.ProRefinalCCDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ProRefinalCC} and its DTO {@link ProRefinalCCDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ProRefinalCCMapper extends EntityMapper<ProRefinalCCDTO, ProRefinalCC> {



    default ProRefinalCC fromId(Long id) {
        if (id == null) {
            return null;
        }
        ProRefinalCC proRefinalCC = new ProRefinalCC();
        proRefinalCC.setId(id);
        return proRefinalCC;
    }
}
