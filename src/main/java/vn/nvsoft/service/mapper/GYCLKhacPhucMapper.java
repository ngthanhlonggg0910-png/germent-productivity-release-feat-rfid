package vn.nvsoft.service.mapper;

import vn.nvsoft.domain.*;
import vn.nvsoft.service.dto.GYCLKhacPhucDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link GYCLKhacPhuc} and its DTO {@link GYCLKhacPhucDTO}.
 */
@Mapper(componentModel = "spring", uses = {GopYChatLuongMapper.class, ThuVienLoiMapper.class, KhacPhucLoiMapper.class})
public interface GYCLKhacPhucMapper extends EntityMapper<GYCLKhacPhucDTO, GYCLKhacPhuc> {

    @Mapping(source = "proGopYChatLuong.id", target = "proGopYChatLuongId")
    @Mapping(source = "thuVienLoi.id", target = "thuVienLoiId")
    @Mapping(source = "khacPhucLoi.id", target = "khacPhucLoiId")
    GYCLKhacPhucDTO toDto(GYCLKhacPhuc gYCLKhacPhuc);

    @Mapping(source = "proGopYChatLuongId", target = "proGopYChatLuong")
    @Mapping(source = "thuVienLoiId", target = "thuVienLoi")
    @Mapping(source = "khacPhucLoiId", target = "khacPhucLoi")
    GYCLKhacPhuc toEntity(GYCLKhacPhucDTO gYCLKhacPhucDTO);

    default GYCLKhacPhuc fromId(Long id) {
        if (id == null) {
            return null;
        }
        GYCLKhacPhuc gYCLKhacPhuc = new GYCLKhacPhuc();
        gYCLKhacPhuc.setId(id);
        return gYCLKhacPhuc;
    }
}
