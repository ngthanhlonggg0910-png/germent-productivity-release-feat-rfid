package vn.nvsoft.service.mapper;

import vn.nvsoft.domain.*;
import vn.nvsoft.service.dto.ProAqlDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ProAql} and its DTO {@link ProAqlDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ProAqlMapper extends EntityMapper<ProAqlDTO, ProAql> {



    default ProAql fromId(Long id) {
        if (id == null) {
            return null;
        }
        ProAql proAql = new ProAql();
        proAql.setId(id);
        return proAql;
    }
}
