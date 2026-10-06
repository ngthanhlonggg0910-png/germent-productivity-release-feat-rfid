package vn.nvsoft.service.mapper;

import vn.nvsoft.domain.*;
import vn.nvsoft.service.dto.ProTenMuaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ProTenMua} and its DTO {@link ProTenMuaDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ProTenMuaMapper extends EntityMapper<ProTenMuaDTO, ProTenMua> {



    default ProTenMua fromId(Long id) {
        if (id == null) {
            return null;
        }
        ProTenMua proTenMua = new ProTenMua();
        proTenMua.setId(id);
        return proTenMua;
    }
}
