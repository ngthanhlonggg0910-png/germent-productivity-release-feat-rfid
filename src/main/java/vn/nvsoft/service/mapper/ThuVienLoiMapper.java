package vn.nvsoft.service.mapper;

import vn.nvsoft.domain.*;
import vn.nvsoft.service.dto.ThuVienLoiDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ThuVienLoi} and its DTO {@link ThuVienLoiDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ThuVienLoiMapper extends EntityMapper<ThuVienLoiDTO, ThuVienLoi> {



    default ThuVienLoi fromId(Long id) {
        if (id == null) {
            return null;
        }
        ThuVienLoi thuVienLoi = new ThuVienLoi();
        thuVienLoi.setId(id);
        return thuVienLoi;
    }
}
