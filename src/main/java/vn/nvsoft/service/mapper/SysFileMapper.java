package vn.nvsoft.service.mapper;

import vn.nvsoft.domain.*;
import vn.nvsoft.service.dto.SysFileDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link SysFile} and its DTO {@link SysFileDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SysFileMapper extends EntityMapper<SysFileDTO, SysFile> {



    default SysFile fromId(Long id) {
        if (id == null) {
            return null;
        }
        SysFile sysFile = new SysFile();
        sysFile.setId(id);
        return sysFile;
    }
}
