package vn.nvsoft.service.mapper;

import vn.nvsoft.domain.*;
import vn.nvsoft.service.dto.ThongSoDoSPSizeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ThongSoDoSPSize} and its DTO {@link ThongSoDoSPSizeDTO}.
 */
@Mapper(componentModel = "spring", uses = {ThongSoDoSPMapper.class})
public interface ThongSoDoSPSizeMapper extends EntityMapper<ThongSoDoSPSizeDTO, ThongSoDoSPSize> {

    @Mapping(source = "thongSoDoSP.id", target = "thongSoDoSPId")
    ThongSoDoSPSizeDTO toDto(ThongSoDoSPSize thongSoDoSPSize);

    @Mapping(target = "thongSoThucThis", ignore = true)
    @Mapping(target = "removeThongSoThucThi", ignore = true)
    @Mapping(source = "thongSoDoSPId", target = "thongSoDoSP")
    ThongSoDoSPSize toEntity(ThongSoDoSPSizeDTO thongSoDoSPSizeDTO);

    default ThongSoDoSPSize fromId(Long id) {
        if (id == null) {
            return null;
        }
        ThongSoDoSPSize thongSoDoSPSize = new ThongSoDoSPSize();
        thongSoDoSPSize.setId(id);
        return thongSoDoSPSize;
    }
}
