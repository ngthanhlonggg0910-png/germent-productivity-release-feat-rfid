package vn.nvsoft.service.mapper;

import vn.nvsoft.domain.*;
import vn.nvsoft.service.dto.ProChuyenDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ProChuyen} and its DTO {@link ProChuyenDTO}.
 */
@Mapper(componentModel = "spring", uses = {ProLoaiChuyenMapper.class, ProNhaMayMapper.class})
public interface ProChuyenMapper extends EntityMapper<ProChuyenDTO, ProChuyen> {

    @Mapping(source = "proLoaiChuyen.id", target = "proLoaiChuyenId")
    @Mapping(source = "proLoaiChuyen.ten", target = "proLoaiChuyenTen")
    @Mapping(source = "proNhaMay.id", target = "proNhaMayId")
    @Mapping(source = "proNhaMay.ten", target = "proNhaMayTen")
    ProChuyenDTO toDto(ProChuyen proChuyen);

    @Mapping(source = "proLoaiChuyenId", target = "proLoaiChuyen")
    @Mapping(source = "proNhaMayId", target = "proNhaMay")
    ProChuyen toEntity(ProChuyenDTO proChuyenDTO);

    default ProChuyen fromId(Long id) {
        if (id == null) {
            return null;
        }
        ProChuyen proChuyen = new ProChuyen();
        proChuyen.setId(id);
        return proChuyen;
    }
}
