package vn.nvsoft.service.mapper;

import vn.nvsoft.domain.*;
import vn.nvsoft.service.dto.ProNhaMayDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ProNhaMay} and its DTO {@link ProNhaMayDTO}.
 */
@Mapper(componentModel = "spring", uses = {ProCongTyMapper.class})
public interface ProNhaMayMapper extends EntityMapper<ProNhaMayDTO, ProNhaMay> {

    @Mapping(source = "proCongTy.id", target = "proCongTyId")
    @Mapping(source = "proCongTy.ten", target = "proCongTyTen")
    ProNhaMayDTO toDto(ProNhaMay proNhaMay);

    @Mapping(source = "proCongTyId", target = "proCongTy")
    ProNhaMay toEntity(ProNhaMayDTO proNhaMayDTO);

    default ProNhaMay fromId(Long id) {
        if (id == null) {
            return null;
        }
        ProNhaMay proNhaMay = new ProNhaMay();
        proNhaMay.setId(id);
        return proNhaMay;
    }
}
