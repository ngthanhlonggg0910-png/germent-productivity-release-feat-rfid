package vn.nvsoft.service.mapper;

import vn.nvsoft.domain.*;
import vn.nvsoft.service.dto.ProKeHoachSanXuatDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ProKeHoachSanXuat} and its DTO {@link ProKeHoachSanXuatDTO}.
 */
@Mapper(componentModel = "spring", uses = {ProChuyenMapper.class, ProSanPhamMapper.class})
public interface ProKeHoachSanXuatMapper extends EntityMapper<ProKeHoachSanXuatDTO, ProKeHoachSanXuat> {

    @Mapping(source = "proChuyen.id", target = "proChuyenId")
    @Mapping(source = "proChuyen.ten", target = "proChuyenTen")
    @Mapping(source = "proSanPham.id", target = "proSanPhamId")
    @Mapping(source = "proSanPham.maSanPham", target = "proSanPhamMaSanPham")
    ProKeHoachSanXuatDTO toDto(ProKeHoachSanXuat proKeHoachSanXuat);

    @Mapping(source = "proChuyenId", target = "proChuyen")
    @Mapping(source = "proSanPhamId", target = "proSanPham")
    ProKeHoachSanXuat toEntity(ProKeHoachSanXuatDTO proKeHoachSanXuatDTO);

    default ProKeHoachSanXuat fromId(Long id) {
        if (id == null) {
            return null;
        }
        ProKeHoachSanXuat proKeHoachSanXuat = new ProKeHoachSanXuat();
        proKeHoachSanXuat.setId(id);
        return proKeHoachSanXuat;
    }
}
