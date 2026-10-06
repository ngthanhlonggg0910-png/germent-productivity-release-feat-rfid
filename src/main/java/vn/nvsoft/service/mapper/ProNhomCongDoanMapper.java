package vn.nvsoft.service.mapper;

import vn.nvsoft.domain.*;
import vn.nvsoft.service.dto.ProNhomCongDoanDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ProNhomCongDoan} and its DTO {@link ProNhomCongDoanDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ProNhomCongDoanMapper extends EntityMapper<ProNhomCongDoanDTO, ProNhomCongDoan> {



    default ProNhomCongDoan fromId(Long id) {
        if (id == null) {
            return null;
        }
        ProNhomCongDoan proNhomCongDoan = new ProNhomCongDoan();
        proNhomCongDoan.setId(id);
        return proNhomCongDoan;
    }
}
