package vn.nvsoft.service.mapper;


import vn.nvsoft.domain.*;
import vn.nvsoft.service.dto.ProNSCLDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ProNSCL} and its DTO {@link ProNSCLDTO}.
 */
@Mapper(componentModel = "spring", uses = {ProKeHoachSanXuatMapper.class, ProCheckPointMapper.class})
public interface ProNSCLMapper extends EntityMapper<ProNSCLDTO, ProNSCL> {

    @Mapping(source = "proKeHoachSanXuat.id", target = "proKeHoachSanXuatId")
    @Mapping(source = "proCheckPoint.id", target = "proCheckPointId")
    @Mapping(source = "proCheckPoint.ten", target = "proCheckPointTen")
    ProNSCLDTO toDto(ProNSCL proNSCL);

    @Mapping(source = "proKeHoachSanXuatId", target = "proKeHoachSanXuat")
    @Mapping(source = "proCheckPointId", target = "proCheckPoint")
    ProNSCL toEntity(ProNSCLDTO proNSCLDTO);

    default ProNSCL fromId(Long id) {
        if (id == null) {
            return null;
        }
        ProNSCL proNSCL = new ProNSCL();
        proNSCL.setId(id);
        return proNSCL;
    }
}
