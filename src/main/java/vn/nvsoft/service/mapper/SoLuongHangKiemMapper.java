package vn.nvsoft.service.mapper;

import vn.nvsoft.domain.*;
import vn.nvsoft.service.dto.SoLuongHangKiemDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link SoLuongHangKiem} and its DTO {@link SoLuongHangKiemDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SoLuongHangKiemMapper extends EntityMapper<SoLuongHangKiemDTO, SoLuongHangKiem> {



    default SoLuongHangKiem fromId(Long id) {
        if (id == null) {
            return null;
        }
        SoLuongHangKiem soLuongHangKiem = new SoLuongHangKiem();
        soLuongHangKiem.setId(id);
        return soLuongHangKiem;
    }
}
