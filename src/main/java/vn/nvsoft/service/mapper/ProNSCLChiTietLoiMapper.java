package vn.nvsoft.service.mapper;

import vn.nvsoft.domain.*;
import vn.nvsoft.service.dto.ProNSCLChiTietLoiDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ProNSCLChiTietLoi} and its DTO {@link ProNSCLChiTietLoiDTO}.
 */
@Mapper(componentModel = "spring", uses = {ProNSCLMapper.class, ProLoiMapper.class})
public interface ProNSCLChiTietLoiMapper extends EntityMapper<ProNSCLChiTietLoiDTO, ProNSCLChiTietLoi> {

    @Mapping(source = "proNSCL.id", target = "proNSCLId")
    @Mapping(source = "proLoi.id", target = "proLoiId")
    @Mapping(source = "proLoi", target = "proLoi")
    @Mapping(source = "proLoi.maLoi", target = "proLoiMaLoi")
    ProNSCLChiTietLoiDTO toDto(ProNSCLChiTietLoi proNSCLChiTietLoi);

    @Mapping(source = "proNSCLId", target = "proNSCL")
    @Mapping(source = "proLoiId", target = "proLoi")
    ProNSCLChiTietLoi toEntity(ProNSCLChiTietLoiDTO proNSCLChiTietLoiDTO);

    default ProNSCLChiTietLoi fromId(Long id) {
        if (id == null) {
            return null;
        }
        ProNSCLChiTietLoi proNSCLChiTietLoi = new ProNSCLChiTietLoi();
        proNSCLChiTietLoi.setId(id);
        return proNSCLChiTietLoi;
    }
}
