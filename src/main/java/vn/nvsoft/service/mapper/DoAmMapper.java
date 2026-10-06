package vn.nvsoft.service.mapper;

import vn.nvsoft.domain.*;
import vn.nvsoft.service.dto.DoAmDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link DoAm} and its DTO {@link DoAmDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DoAmMapper extends EntityMapper<DoAmDTO, DoAm> {



    default DoAm fromId(Long id) {
        if (id == null) {
            return null;
        }
        DoAm doAm = new DoAm();
        doAm.setId(id);
        return doAm;
    }
}
