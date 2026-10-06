package vn.nvsoft.service.mapper;

import vn.nvsoft.domain.*;
import vn.nvsoft.service.dto.ProLoiDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ProLoi} and its DTO {@link ProLoiDTO}.
 */
@Mapper(componentModel = "spring", uses = {ProCumLoiMapper.class})
public interface ProLoiMapper extends EntityMapper<ProLoiDTO, ProLoi> {

    @Mapping(source = "proCumLoi.id", target = "proCumLoiId")
    @Mapping(source = "proCumLoi.ten", target = "proCumLoiTen")
    ProLoiDTO toDto(ProLoi proLoi);

    @Mapping(source = "proCumLoiId", target = "proCumLoi")
    ProLoi toEntity(ProLoiDTO proLoiDTO);

    default ProLoi fromId(Long id) {
        if (id == null) {
            return null;
        }
        ProLoi proLoi = new ProLoi();
        proLoi.setId(id);
        return proLoi;
    }
}
