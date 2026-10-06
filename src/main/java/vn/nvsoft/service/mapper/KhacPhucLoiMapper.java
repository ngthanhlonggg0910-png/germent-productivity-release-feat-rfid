package vn.nvsoft.service.mapper;

import vn.nvsoft.domain.*;
import vn.nvsoft.service.dto.KhacPhucLoiDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link KhacPhucLoi} and its DTO {@link KhacPhucLoiDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface KhacPhucLoiMapper extends EntityMapper<KhacPhucLoiDTO, KhacPhucLoi> {



    default KhacPhucLoi fromId(Long id) {
        if (id == null) {
            return null;
        }
        KhacPhucLoi khacPhucLoi = new KhacPhucLoi();
        khacPhucLoi.setId(id);
        return khacPhucLoi;
    }
}
