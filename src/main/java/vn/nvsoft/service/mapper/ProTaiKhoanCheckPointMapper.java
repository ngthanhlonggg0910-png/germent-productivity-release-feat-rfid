package vn.nvsoft.service.mapper;

import vn.nvsoft.domain.*;
import vn.nvsoft.service.dto.ProTaiKhoanCheckPointDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ProTaiKhoanCheckPoint} and its DTO {@link ProTaiKhoanCheckPointDTO}.
 */
@Mapper(componentModel = "spring", uses = {ProCheckPointMapper.class})
public interface ProTaiKhoanCheckPointMapper extends EntityMapper<ProTaiKhoanCheckPointDTO, ProTaiKhoanCheckPoint> {

    @Mapping(source = "proCheckPoint.id", target = "proCheckPointId")
    @Mapping(source = "proCheckPoint.ten", target = "proCheckPointTen")
    ProTaiKhoanCheckPointDTO toDto(ProTaiKhoanCheckPoint proTaiKhoanCheckPoint);

    @Mapping(source = "proCheckPointId", target = "proCheckPoint")
    ProTaiKhoanCheckPoint toEntity(ProTaiKhoanCheckPointDTO proTaiKhoanCheckPointDTO);

    default ProTaiKhoanCheckPoint fromId(Long id) {
        if (id == null) {
            return null;
        }
        ProTaiKhoanCheckPoint proTaiKhoanCheckPoint = new ProTaiKhoanCheckPoint();
        proTaiKhoanCheckPoint.setId(id);
        return proTaiKhoanCheckPoint;
    }
}
