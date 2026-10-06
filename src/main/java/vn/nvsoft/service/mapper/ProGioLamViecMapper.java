package vn.nvsoft.service.mapper;

import vn.nvsoft.domain.*;
import vn.nvsoft.service.dto.ProGioLamViecDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ProGioLamViec} and its DTO {@link ProGioLamViecDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ProGioLamViecMapper extends EntityMapper<ProGioLamViecDTO, ProGioLamViec> {



    default ProGioLamViec fromId(Long id) {
        if (id == null) {
            return null;
        }
        ProGioLamViec proGioLamViec = new ProGioLamViec();
        proGioLamViec.setId(id);
        return proGioLamViec;
    }
}
