package vn.nvsoft.service.mapper;

import vn.nvsoft.domain.*;
import vn.nvsoft.service.dto.GYCLQuyCachNPLDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link GYCLQuyCachNPL} and its DTO {@link GYCLQuyCachNPLDTO}.
 */
@Mapper(componentModel = "spring", uses = {GopYChatLuongMapper.class})
public interface GYCLQuyCachNPLMapper extends EntityMapper<GYCLQuyCachNPLDTO, GYCLQuyCachNPL> {

    @Mapping(source = "proGopYChatLuong.id", target = "proGopYChatLuongId")
    GYCLQuyCachNPLDTO toDto(GYCLQuyCachNPL gYCLQuyCachNPL);

    @Mapping(source = "proGopYChatLuongId", target = "proGopYChatLuong")
    GYCLQuyCachNPL toEntity(GYCLQuyCachNPLDTO gYCLQuyCachNPLDTO);

    default GYCLQuyCachNPL fromId(Long id) {
        if (id == null) {
            return null;
        }
        GYCLQuyCachNPL gYCLQuyCachNPL = new GYCLQuyCachNPL();
        gYCLQuyCachNPL.setId(id);
        return gYCLQuyCachNPL;
    }
}
