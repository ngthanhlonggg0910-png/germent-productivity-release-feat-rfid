package vn.nvsoft.service.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import vn.nvsoft.service.vm.SysFunctionVM;
import vn.nvsoft.service.vm.SysGroupModuleVM;
import vn.nvsoft.service.vm.SysModuleVM;
import vn.nvsoft.service.vm.SysPermissionVM;
import vn.nvsoft.service.vm.UserPermissionVM;

@Service
public class SysGroupModuleVMMapper {
    public List<SysGroupModuleVM> userPermissionVMToSysGroupModuleVMs(List<UserPermissionVM> userPermissions) {
        List<SysGroupModuleVM> results = new ArrayList<>();
        if (userPermissions != null) {
            Map<Long, SysGroupModuleVM> sysGroupModuleVMs = new HashMap<>();
            Map<Long, SysModuleVM> sysModuleVMs = new HashMap<>();
            Map<Long, SysFunctionVM> sysFunctionVMs = new HashMap<>();
            Map<Long, SysPermissionVM> sysPermissionVMs = new HashMap<>();

            for (UserPermissionVM userPermission : userPermissions) {
                SysGroupModuleVM sysGroupModuleVM = sysGroupModuleVMs
                    .get(userPermission.getGroupModuleId());
                if (sysGroupModuleVM == null) {
                    sysGroupModuleVM = new SysGroupModuleVM(
                        userPermission.getGroupModuleId(),
                        userPermission.getGroupModuleCaption(),
                        userPermission.getGroupModuleName(),
                        userPermission.getGroupModulePrimaryImageUri(),
                        userPermission.getGroupModuleSecondaryImageUri());
                    sysGroupModuleVMs.put(sysGroupModuleVM.getId(), sysGroupModuleVM);
                    results.add(sysGroupModuleVM);
                }

                SysModuleVM sysModuleVM = sysModuleVMs.get(userPermission.getModuleId());
                if (sysModuleVM == null) {
                    sysModuleVM = new SysModuleVM(
                        userPermission.getModuleId(),
                        userPermission.getModuleCaption(),
                        userPermission.getModuleImageUri(),
                        userPermission.getModuleName());
                    sysModuleVMs.put(sysModuleVM.getId(), sysModuleVM);
                }
                sysModuleVM.setCodePre(userPermission.getModuleCodePre());
                sysGroupModuleVM.addModuleVM(sysModuleVM);

                SysFunctionVM sysFunctionVM = sysFunctionVMs.get(userPermission.getFuncId());
                if (sysFunctionVM == null) {
                    sysFunctionVM = new SysFunctionVM(
                        userPermission.getFuncId(),
                        userPermission.getFuncCaption(),
                        userPermission.getFuncEventName(),
                        userPermission.getFuncHotkey(),
                        userPermission.getFuncImageUri(),
                        userPermission.getFuncLevel(),
                        userPermission.getFuncName(),
                        userPermission.getRoleName());
                    sysFunctionVM.setRoleHasFuncId(userPermission.getRoleHasFuncId());
                    sysFunctionVM.setRoleHasFuncActivated(userPermission.getRoleHasFuncActivated());
                    sysFunctionVMs.put(sysFunctionVM.getId(), sysFunctionVM);
                }
                sysModuleVM.addFunction(sysFunctionVM);

                SysPermissionVM sysPermissionVM = sysPermissionVMs
                    .get(userPermission.getPermissionId());
                if (sysPermissionVM == null) {
                    sysPermissionVM = new SysPermissionVM(
                        userPermission.getPermissionId(),
                        userPermission.getPermissionCaption(),
                        userPermission.getPermissionEventName(),
                        userPermission.getPermissionHotkey(),
                        userPermission.getPermissionImageUri(),
                        userPermission.getPermissionLevel(),
                        userPermission.getPermissionName());
                }
                sysFunctionVM.setSysPermissionVM(sysPermissionVM);

            }
        }
        return results;
    }
}
