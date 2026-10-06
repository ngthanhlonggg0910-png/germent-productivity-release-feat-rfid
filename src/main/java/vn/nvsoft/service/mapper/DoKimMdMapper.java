package vn.nvsoft.service.mapper;

import vn.nvsoft.domain.*;
import vn.nvsoft.service.dto.DoKimMdDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link DoKimMd} and its DTO {@link DoKimMdDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DoKimMdMapper extends EntityMapper<DoKimMdDTO, DoKimMd> {



    default DoKimMd fromId(Long id) {
        if (id == null) {
            return null;
        }
        DoKimMd doKimMd = new DoKimMd();
        doKimMd.setId(id);
        return doKimMd;
    }
}
