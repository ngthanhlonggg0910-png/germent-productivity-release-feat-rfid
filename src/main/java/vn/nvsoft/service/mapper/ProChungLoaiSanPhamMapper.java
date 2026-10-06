package vn.nvsoft.service.mapper;

import vn.nvsoft.domain.*;
import vn.nvsoft.service.dto.ProChungLoaiSanPhamDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ProChungLoaiSanPham} and its DTO {@link ProChungLoaiSanPhamDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ProChungLoaiSanPhamMapper extends EntityMapper<ProChungLoaiSanPhamDTO, ProChungLoaiSanPham> {



    default ProChungLoaiSanPham fromId(Long id) {
        if (id == null) {
            return null;
        }
        ProChungLoaiSanPham proChungLoaiSanPham = new ProChungLoaiSanPham();
        proChungLoaiSanPham.setId(id);
        return proChungLoaiSanPham;
    }
}
