package vn.nvsoft.service.mapper;

import vn.nvsoft.domain.*;
import vn.nvsoft.service.dto.ThongSoDoSPDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ThongSoDoSP} and its DTO {@link ThongSoDoSPDTO}.
 */
@Mapper(componentModel = "spring", uses = {ThongSoDoMapper.class, DungSaiMapper.class, ProSanPhamMapper.class})
public interface ThongSoDoSPMapper extends EntityMapper<ThongSoDoSPDTO, ThongSoDoSP> {

    @Mapping(source = "thongSoDo.id", target = "thongSoDoId")
    @Mapping(target = "thongSoDoCode", expression = "java(thongSoDoSP.buildCodeInfo())")
    @Mapping(source = "dungSai.id", target = "dungSaiId")
    @Mapping(source = "dungSai.inchLabel", target = "dungSaiInchLabel")
    @Mapping(source = "dungSai.cmLabel", target = "dungSaiCmLabel")
    @Mapping(source = "dungSaiMax.id", target = "dungSaiIdMax")
    @Mapping(source = "dungSaiMax.inchLabel", target = "dungSaiInchLabelMax")
    @Mapping(source = "dungSaiMax.cmLabel", target = "dungSaiCmLabelMax")
    @Mapping(source = "proSanPham.id", target = "proSanPhamId")
    @Mapping(source = "proSanPham.maSanPham", target = "proSanPhamMaSanPham")
    @Mapping(source = "quyCachDo.quyCach", target = "note")
    ThongSoDoSPDTO toDto(ThongSoDoSP thongSoDoSP);

    @Mapping(target = "thongSoDoSPSizes", ignore = true)
    @Mapping(target = "removeThongSoDoSPSize", ignore = true)
    @Mapping(source = "thongSoDoId", target = "thongSoDo")
    @Mapping(source = "dungSaiId", target = "dungSai")
    @Mapping(source = "dungSaiIdMax", target = "dungSaiMax")
    @Mapping(source = "proSanPhamId", target = "proSanPham")
    ThongSoDoSP toEntity(ThongSoDoSPDTO thongSoDoSPDTO);

    default ThongSoDoSP fromId(Long id) {
        if (id == null) {
            return null;
        }
        ThongSoDoSP thongSoDoSP = new ThongSoDoSP();
        thongSoDoSP.setId(id);
        return thongSoDoSP;
    }
}
