package vn.nvsoft.service.mapper;

import vn.nvsoft.domain.*;
import vn.nvsoft.service.dto.ThongSoDoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ThongSoDo} and its DTO {@link ThongSoDoDTO}.
 */
@Mapper(componentModel = "spring", uses = {DungSaiMapper.class})
public interface ThongSoDoMapper extends EntityMapper<ThongSoDoDTO, ThongSoDo> {

    @Mapping(source = "dungSai.id", target = "dungSaiId")
    @Mapping(source = "dungSai.inchLabel", target = "dungSaiInchLabel")
    ThongSoDoDTO toDto(ThongSoDo thongSoDo);

    @Mapping(source = "dungSaiId", target = "dungSai")
    ThongSoDo toEntity(ThongSoDoDTO thongSoDoDTO);

    default ThongSoDo fromId(Long id) {
        if (id == null) {
            return null;
        }
        ThongSoDo thongSoDo = new ThongSoDo();
        thongSoDo.setId(id);
        return thongSoDo;
    }
}
