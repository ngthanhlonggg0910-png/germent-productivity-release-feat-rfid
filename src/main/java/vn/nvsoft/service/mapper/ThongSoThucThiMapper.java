package vn.nvsoft.service.mapper;

import vn.nvsoft.domain.*;
import vn.nvsoft.service.dto.ThongSoThucThiDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ThongSoThucThi} and its DTO {@link ThongSoThucThiDTO}.
 */
@Mapper(componentModel = "spring", uses = {ThongSoDoSPSizeMapper.class, DungSaiMapper.class, ProKeHoachSanXuatMapper.class})
public interface ThongSoThucThiMapper extends EntityMapper<ThongSoThucThiDTO, ThongSoThucThi> {

    @Mapping(source = "thongSoDoSPSize", target = "thongSoDoSPSize")
    @Mapping(source = "dungSai", target = "dungSai")
    @Mapping(source = "proKeHoachSanXuat.id", target = "proKeHoachSanXuatId")
    @Mapping(source = "mausanpham", target = "mausanpham")
    @Mapping(source = "po", target = "po")
    @Mapping(source = "isqa", target = "isqa")
    @Mapping(source = "checkPointId", target = "checkPointId")
    ThongSoThucThiDTO toDto(ThongSoThucThi thongSoThucThi);

    @Mapping(source = "thongSoDoSPSize", target = "thongSoDoSPSize")
    @Mapping(source = "dungSai", target = "dungSai")
    @Mapping(source = "proKeHoachSanXuatId", target = "proKeHoachSanXuat")
    @Mapping(source = "mausanpham", target = "mausanpham")
    @Mapping(source = "po", target = "po")
    @Mapping(source = "isqa", target = "isqa")
    @Mapping(source = "checkPointId", target = "checkPointId")
    ThongSoThucThi toEntity(ThongSoThucThiDTO thongSoThucThiDTO);

    default ThongSoThucThi fromId(Long id) {
        if (id == null) {
            return null;
        }
        ThongSoThucThi thongSoThucThi = new ThongSoThucThi();
        thongSoThucThi.setId(id);
        return thongSoThucThi;
    }
}
