package vn.nvsoft.service.mapper;

import vn.nvsoft.domain.*;
import vn.nvsoft.service.dto.LibSoLuongPoKiemDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link LibSoLuongPoKiem} and its DTO {@link LibSoLuongPoKiemDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface LibSoLuongPoKiemMapper extends EntityMapper<LibSoLuongPoKiemDTO, LibSoLuongPoKiem> {



    default LibSoLuongPoKiem fromId(Long id) {
        if (id == null) {
            return null;
        }
        LibSoLuongPoKiem libSoLuongPoKiem = new LibSoLuongPoKiem();
        libSoLuongPoKiem.setId(id);
        return libSoLuongPoKiem;
    }
}
