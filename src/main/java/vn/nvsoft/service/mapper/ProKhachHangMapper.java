package vn.nvsoft.service.mapper;

import vn.nvsoft.domain.*;
import vn.nvsoft.service.dto.ProKhachHangDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ProKhachHang} and its DTO {@link ProKhachHangDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ProKhachHangMapper extends EntityMapper<ProKhachHangDTO, ProKhachHang> {



    default ProKhachHang fromId(Long id) {
        if (id == null) {
            return null;
        }
        ProKhachHang proKhachHang = new ProKhachHang();
        proKhachHang.setId(id);
        return proKhachHang;
    }
}
