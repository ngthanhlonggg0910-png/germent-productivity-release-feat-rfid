package vn.nvsoft.service.mapper;

import vn.nvsoft.domain.*;
import vn.nvsoft.service.dto.ProCongTyDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ProCongTy} and its DTO {@link ProCongTyDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ProCongTyMapper extends EntityMapper<ProCongTyDTO, ProCongTy> {



    default ProCongTy fromId(Long id) {
        if (id == null) {
            return null;
        }
        ProCongTy proCongTy = new ProCongTy();
        proCongTy.setId(id);
        return proCongTy;
    }
}
