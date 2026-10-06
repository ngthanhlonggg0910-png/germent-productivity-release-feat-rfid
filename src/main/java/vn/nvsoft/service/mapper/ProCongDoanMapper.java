package vn.nvsoft.service.mapper;

import vn.nvsoft.domain.*;
import vn.nvsoft.service.dto.ProCongDoanDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ProCongDoan} and its DTO {@link ProCongDoanDTO}.
 */
@Mapper(componentModel = "spring", uses = {ProNhomCongDoanMapper.class})
public interface ProCongDoanMapper extends EntityMapper<ProCongDoanDTO, ProCongDoan> {

    @Mapping(source = "proNhomCongDoan.id", target = "proNhomCongDoanId")
    @Mapping(source = "proNhomCongDoan.ten", target = "proNhomCongDoanTen")
    ProCongDoanDTO toDto(ProCongDoan proCongDoan);

    @Mapping(source = "proNhomCongDoanId", target = "proNhomCongDoan")
    ProCongDoan toEntity(ProCongDoanDTO proCongDoanDTO);

    default ProCongDoan fromId(Long id) {
        if (id == null) {
            return null;
        }
        ProCongDoan proCongDoan = new ProCongDoan();
        proCongDoan.setId(id);
        return proCongDoan;
    }
}
