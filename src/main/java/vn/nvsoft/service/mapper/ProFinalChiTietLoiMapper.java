package vn.nvsoft.service.mapper;

import vn.nvsoft.domain.*;
import vn.nvsoft.service.dto.ProFinalChiTietLoiDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ProFinalChiTietLoi} and its DTO {@link ProFinalChiTietLoiDTO}.
 */
@Mapper(componentModel = "spring", uses = {ProFinalMapper.class, ProLoiMapper.class})
public interface ProFinalChiTietLoiMapper extends EntityMapper<ProFinalChiTietLoiDTO, ProFinalChiTietLoi> {

    @Mapping(source = "proFinal.id", target = "proFinalId")
    @Mapping(source = "proLoi.id", target = "proLoiId")
    @Mapping(source = "proLoi", target = "proLoi")
    ProFinalChiTietLoiDTO toDto(ProFinalChiTietLoi proFinalChiTietLoi);

    @Mapping(source = "proFinalId", target = "proFinal")
    @Mapping(source = "proLoiId", target = "proLoi")
    ProFinalChiTietLoi toEntity(ProFinalChiTietLoiDTO proFinalChiTietLoiDTO);

    default ProFinalChiTietLoi fromId(Long id) {
        if (id == null) {
            return null;
        }
        ProFinalChiTietLoi proFinalChiTietLoi = new ProFinalChiTietLoi();
        proFinalChiTietLoi.setId(id);
        return proFinalChiTietLoi;
    }
}
