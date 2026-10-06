package vn.nvsoft.service.mapper;

import vn.nvsoft.domain.*;
import vn.nvsoft.service.dto.DungSaiDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link DungSai} and its DTO {@link DungSaiDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DungSaiMapper extends EntityMapper<DungSaiDTO, DungSai> {


    @Mapping(target = "thongSoThucThis", ignore = true)
    @Mapping(target = "removeThongSoThucThi", ignore = true)
    DungSai toEntity(DungSaiDTO dungSaiDTO);

    default DungSai fromId(Long id) {
        if (id == null) {
            return null;
        }
        DungSai dungSai = new DungSai();
        dungSai.setId(id);
        return dungSai;
    }
}
