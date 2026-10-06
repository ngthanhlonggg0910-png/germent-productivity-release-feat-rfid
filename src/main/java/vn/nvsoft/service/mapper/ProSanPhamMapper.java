package vn.nvsoft.service.mapper;

import vn.nvsoft.domain.*;
import vn.nvsoft.service.dto.ProSanPhamDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ProSanPham} and its DTO {@link ProSanPhamDTO}.
 */
@Mapper(componentModel = "spring", uses = {ProKhachHangMapper.class, ProChungLoaiSanPhamMapper.class, ProTenMuaMapper.class})
public interface ProSanPhamMapper extends EntityMapper<ProSanPhamDTO, ProSanPham> {

    @Mapping(source = "proKhachHang.id", target = "proKhachHangId")
    @Mapping(source = "proKhachHang.ten", target = "proKhachHangTen")
    @Mapping(source = "proChungLoaiSanPham.id", target = "proChungLoaiSanPhamId")
    @Mapping(source = "proChungLoaiSanPham.tenChungLoai", target = "proChungLoaiSanPhamTenChungLoai")
    @Mapping(source = "proTenMua.id", target = "proTenMuaId")
    @Mapping(source = "proTenMua.tenMua", target = "proTenMuaTenMua")
    ProSanPhamDTO toDto(ProSanPham proSanPham);

    @Mapping(source = "proKhachHangId", target = "proKhachHang")
    @Mapping(source = "proChungLoaiSanPhamId", target = "proChungLoaiSanPham")
    @Mapping(source = "proTenMuaId", target = "proTenMua")
    ProSanPham toEntity(ProSanPhamDTO proSanPhamDTO);

    default ProSanPham fromId(Long id) {
        if (id == null) {
            return null;
        }
        ProSanPham proSanPham = new ProSanPham();
        proSanPham.setId(id);
        return proSanPham;
    }
}
