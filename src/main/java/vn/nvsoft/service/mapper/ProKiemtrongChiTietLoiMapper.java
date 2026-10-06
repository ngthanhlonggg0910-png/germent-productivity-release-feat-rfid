package vn.nvsoft.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import vn.nvsoft.domain.ProKiemtrongChiTietLoi;
import vn.nvsoft.domain.ProLoi;
import vn.nvsoft.service.dto.ProKiemtrongChiTietLoiDTO;

@Mapper(componentModel = "spring", uses = {ProLoiMapper.class})
public interface ProKiemtrongChiTietLoiMapper extends EntityMapper<ProKiemtrongChiTietLoiDTO, ProKiemtrongChiTietLoi> {

    @Mapping(source = "proLoi.id", target = "proLoiId")
    @Mapping(source = "proLoi", target = "proLoi")
    @Mapping(source = "proLoi.maLoi", target = "proLoiMaLoi")
    ProKiemtrongChiTietLoiDTO toDto(ProKiemtrongChiTietLoi entity);

    @Mapping(source = "proLoiId", target = "proLoi", qualifiedByName = "proLoiFromId")
    ProKiemtrongChiTietLoi toEntity(ProKiemtrongChiTietLoiDTO dto);

    @Named("proLoiFromId")
    default ProLoi proLoiFromId(Long id) {
        if (id == null) {
            return null;
        }
        ProLoi proLoi = new ProLoi();
        proLoi.setId(id);
        return proLoi;
    }
}
