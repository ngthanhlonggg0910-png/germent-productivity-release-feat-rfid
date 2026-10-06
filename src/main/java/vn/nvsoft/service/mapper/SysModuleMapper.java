package vn.nvsoft.service.mapper;

import vn.nvsoft.domain.*;
import vn.nvsoft.service.dto.SysModuleDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link SysModule} and its DTO {@link SysModuleDTO}.
 */
@Mapper(componentModel = "spring", uses = {SysGroupModuleMapper.class})
public interface SysModuleMapper extends EntityMapper<SysModuleDTO, SysModule> {

    @Mapping(source = "sysGroupModule.id", target = "sysGroupModuleId")
    SysModuleDTO toDto(SysModule sysModule);

    @Mapping(source = "sysGroupModuleId", target = "sysGroupModule")
    SysModule toEntity(SysModuleDTO sysModuleDTO);

    default SysModule fromId(Long id) {
        if (id == null) {
            return null;
        }
        SysModule sysModule = new SysModule();
        sysModule.setId(id);
        return sysModule;
    }
}
