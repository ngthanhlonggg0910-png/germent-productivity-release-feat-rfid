package vn.nvsoft.service.mapper;

import vn.nvsoft.domain.*;
import vn.nvsoft.service.dto.GopYChatLuongDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link GopYChatLuong} and its DTO {@link GopYChatLuongDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface GopYChatLuongMapper extends EntityMapper<GopYChatLuongDTO, GopYChatLuong> {



    default GopYChatLuong fromId(Long id) {
        if (id == null) {
            return null;
        }
        GopYChatLuong gopYChatLuong = new GopYChatLuong();
        gopYChatLuong.setId(id);
        return gopYChatLuong;
    }
}
