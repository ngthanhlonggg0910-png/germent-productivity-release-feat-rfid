package vn.nvsoft.service.mapper;

import vn.nvsoft.domain.*;
import vn.nvsoft.service.dto.SysGroupModuleDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link SysGroupModule} and its DTO {@link SysGroupModuleDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SysGroupModuleMapper extends EntityMapper<SysGroupModuleDTO, SysGroupModule> {

    default SysGroupModule fromId(Long id) {
        if (id == null) {
            return null;
        }
        SysGroupModule sysGroupModule = new SysGroupModule();
        sysGroupModule.setId(id);
        return sysGroupModule;
    }
}
