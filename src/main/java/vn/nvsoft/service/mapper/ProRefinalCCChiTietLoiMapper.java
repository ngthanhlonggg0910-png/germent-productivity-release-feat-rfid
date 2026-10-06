package vn.nvsoft.service.mapper;

import vn.nvsoft.domain.*;
import vn.nvsoft.service.dto.ProRefinalCCChiTietLoiDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ProRefinalCCChiTietLoi} and its DTO {@link ProRefinalCCChiTietLoiDTO}.
 */
@Mapper(componentModel = "spring", uses = {ProRefinalCCMapper.class, ProLoiMapper.class})
public interface ProRefinalCCChiTietLoiMapper extends EntityMapper<ProRefinalCCChiTietLoiDTO, ProRefinalCCChiTietLoi> {

    @Mapping(source = "proRefinalCC.id", target = "proRefinalCCId")
    @Mapping(source = "proLoi.id", target = "proLoiId")
    @Mapping(source = "proLoi", target = "proLoi")
    ProRefinalCCChiTietLoiDTO toDto(ProRefinalCCChiTietLoi proRefinalCCChiTietLoi);

    @Mapping(source = "proRefinalCCId", target = "proRefinalCC")
    @Mapping(source = "proLoiId", target = "proLoi")
    ProRefinalCCChiTietLoi toEntity(ProRefinalCCChiTietLoiDTO proRefinalCCChiTietLoiDTO);

    default ProRefinalCCChiTietLoi fromId(Long id) {
        if (id == null) {
            return null;
        }
        ProRefinalCCChiTietLoi proRefinalCCChiTietLoi = new ProRefinalCCChiTietLoi();
        proRefinalCCChiTietLoi.setId(id);
        return proRefinalCCChiTietLoi;
    }
}
