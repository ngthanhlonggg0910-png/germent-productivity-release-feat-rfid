package vn.nvsoft.service.mapper;

import vn.nvsoft.domain.*;
import vn.nvsoft.service.dto.ProCheckPointDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ProCheckPoint} and its DTO {@link ProCheckPointDTO}.
 */
@Mapper(componentModel = "spring", uses = {ProChuyenMapper.class, ProCongDoanMapper.class})
public interface ProCheckPointMapper extends EntityMapper<ProCheckPointDTO, ProCheckPoint> {

    @Mapping(source = "proChuyen.id", target = "proChuyenId")
    @Mapping(source = "proChuyen.ten", target = "proChuyenTen")
    @Mapping(source = "proCongDoan.id", target = "proCongDoanId")
    @Mapping(source = "proCongDoan.ten", target = "proCongDoanTen")
    ProCheckPointDTO toDto(ProCheckPoint proCheckPoint);

    @Mapping(source = "proChuyenId", target = "proChuyen")
    @Mapping(source = "proCongDoanId", target = "proCongDoan")
    ProCheckPoint toEntity(ProCheckPointDTO proCheckPointDTO);

    default ProCheckPoint fromId(Long id) {
        if (id == null) {
            return null;
        }
        ProCheckPoint proCheckPoint = new ProCheckPoint();
        proCheckPoint.setId(id);
        return proCheckPoint;
    }
}
