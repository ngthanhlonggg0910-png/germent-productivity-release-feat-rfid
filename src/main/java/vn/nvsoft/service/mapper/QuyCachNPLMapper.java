package vn.nvsoft.service.mapper;

import vn.nvsoft.domain.*;
import vn.nvsoft.service.dto.QuyCachNPLDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link QuyCachNPL} and its DTO {@link QuyCachNPLDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface QuyCachNPLMapper extends EntityMapper<QuyCachNPLDTO, QuyCachNPL> {



    default QuyCachNPL fromId(Long id) {
        if (id == null) {
            return null;
        }
        QuyCachNPL quyCachNPL = new QuyCachNPL();
        quyCachNPL.setId(id);
        return quyCachNPL;
    }
}
