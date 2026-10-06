package vn.nvsoft.service.mapper;

import vn.nvsoft.domain.*;
import vn.nvsoft.service.dto.ProLoaiChuyenDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ProLoaiChuyen} and its DTO {@link ProLoaiChuyenDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ProLoaiChuyenMapper extends EntityMapper<ProLoaiChuyenDTO, ProLoaiChuyen> {



    default ProLoaiChuyen fromId(Long id) {
        if (id == null) {
            return null;
        }
        ProLoaiChuyen proLoaiChuyen = new ProLoaiChuyen();
        proLoaiChuyen.setId(id);
        return proLoaiChuyen;
    }
}
