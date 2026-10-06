package vn.nvsoft.service.mapper;

import vn.nvsoft.domain.*;
import vn.nvsoft.service.dto.ProRefinalChiTietLoiDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ProRefinalChiTietLoi} and its DTO {@link ProRefinalChiTietLoiDTO}.
 */
@Mapper(componentModel = "spring", uses = {ProRefinalMapper.class, ProLoiMapper.class})
public interface ProRefinalChiTietLoiMapper extends EntityMapper<ProRefinalChiTietLoiDTO, ProRefinalChiTietLoi> {

    @Mapping(source = "proRefinal.id", target = "proRefinalId")
    @Mapping(source = "proLoi.id", target = "proLoiId")
    ProRefinalChiTietLoiDTO toDto(ProRefinalChiTietLoi proRefinalChiTietLoi);

    @Mapping(source = "proRefinalId", target = "proRefinal")
    @Mapping(source = "proLoiId", target = "proLoi")
    ProRefinalChiTietLoi toEntity(ProRefinalChiTietLoiDTO proRefinalChiTietLoiDTO);

    default ProRefinalChiTietLoi fromId(Long id) {
        if (id == null) {
            return null;
        }
        ProRefinalChiTietLoi proRefinalChiTietLoi = new ProRefinalChiTietLoi();
        proRefinalChiTietLoi.setId(id);
        return proRefinalChiTietLoi;
    }
}
