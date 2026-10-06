package vn.nvsoft.repository;
import java.util.List;
import org.springframework.data.repository.query.Param;
import vn.nvsoft.domain.SysRoleHasFunction;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import vn.nvsoft.service.vm.UserPermissionVM;


/**
 * Spring Data  repository for the SysRoleHasFunction entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SysRoleHasFunctionRepository extends JpaRepository<SysRoleHasFunction, Long> {
    @Query(nativeQuery = true, value ="SELECT DISTINCT srhf.id roleHasFuncId, srhf.activated roleHasFuncActivated, sf.id funcId, sf.name funcName, sf.image_uri funcImageUri, sf.caption funcCaption, sf.level funcLevel, sf.event_name funcEventName, sf.hotkey funcHotkey"
        + ", sp.id permissionId, sp.name permissionName, sp.image_uri permissionImageUri, sp.caption permissionCaption, sp.level permissionLevel, sp.event_name permissionEventName, sp.hotkey permissionHotkey"
        + ", sm.id moduleId, sm.name moduleName, sm.caption moduleCaption, sm.image_uri moduleImageUri, sm.code_pre moduleCodePre"
        + ", sgm.id groupModuleId, sgm.name groupModuleName, sgm.caption groupModuleCaption, sgm.primary_image_uri groupModulePrimaryImageUri, sgm.secondary_image_uri groupModuleSecondaryImageUri"
        + ", jua.authority_name roleName"
        + " FROM sys_role_has_function srhf"
        + " INNER JOIN sys_function sf ON srhf.sys_function_id = sf.id"
        + " INNER JOIN sys_module sm ON sf.sys_module_id = sm.id"
        + " INNER JOIN sys_group_module sgm ON sgm.id = sm.sys_group_module_id"
        + " INNER JOIN sys_permission sp ON sp.id = sf.sys_permission_id"
        + " INNER JOIN jhi_user_authority jua ON jua.authority_name = srhf.authority_name"
        + " INNER JOIN jhi_user ju ON ju.id = jua.user_id"
        + " WHERE ju.login =:userName"
    )
    List<UserPermissionVM> getAllPermissionOfCurrentUsers(@Param("userName") String userName);

    @Query(nativeQuery = true, value ="SELECT DISTINCT srhf.id roleHasFuncId, srhf.activated roleHasFuncActivated, sf.id funcId, sf.name funcName, sf.image_uri "
        + "funcImageUri, sf"
        + ".caption funcCaption, sf.level funcLevel, sf.event_name funcEventName, sf.hotkey funcHotkey"
        + ", sp.id permissionId, sp.name permissionName, sp.image_uri permissionImageUri, sp.caption permissionCaption, sp.level permissionLevel, sp.event_name permissionEventName, sp.hotkey permissionHotkey"
        + ", sm.id moduleId, sm.name moduleName, sm.caption moduleCaption, sm.image_uri moduleImageUri, sm.code_pre moduleCodePre"
        + ", sgm.id groupModuleId, sgm.name groupModuleName, sgm.caption groupModuleCaption, sgm.primary_image_uri groupModulePrimaryImageUri, sgm.secondary_image_uri groupModuleSecondaryImageUri"
        + ", jua.authority_name roleName"
        + " FROM sys_function sf"
        + " INNER JOIN sys_module sm ON sf.sys_module_id = sm.id"
        + " INNER JOIN sys_group_module sgm ON sgm.id = sm.sys_group_module_id"
        + " INNER JOIN sys_permission sp ON sp.id = sf.sys_permission_id"
        + " LEFT JOIN sys_role_has_function srhf ON srhf.sys_function_id = sf.id AND srhf.authority_name = :role"
        + " LEFT JOIN jhi_user_authority jua ON jua.authority_name = srhf.authority_name"
        + " LEFT JOIN jhi_user ju ON ju.id = jua.user_id"
    )
    List<UserPermissionVM> getAllPermissionByRole(@Param("role") String role);

    @Query(nativeQuery = true, value ="SELECT DISTINCT srhf.id roleHasFuncId, srhf.activated roleHasFuncActivated, srhf.authority_name roleName, sf.id funcId, sf.name funcName, sf.image_uri funcImageUri, sf.caption funcCaption, sf.level funcLevel, sf.event_name funcEventName, sf.hotkey funcHotkey"
        + ", sp.id permissionId, sp.name permissionName, sp.image_uri permissionImageUri, sp.caption permissionCaption, sp.level permissionLevel, sp.event_name permissionEventName, sp.hotkey permissionHotkey"
        + ", sm.id moduleId, sm.name moduleName, sm.caption moduleCaption, sm.image_uri moduleImageUri, sm.code_pre moduleCodePre"
        + ", sgm.id groupModuleId, sgm.name groupModuleName, sgm.caption groupModuleCaption, sgm.primary_image_uri groupModulePrimaryImageUri, sgm.secondary_image_uri groupModuleSecondaryImageUri"
        + " FROM sys_function sf"
        + " INNER JOIN sys_module sm ON sf.sys_module_id = sm.id"
        + " INNER JOIN sys_group_module sgm ON sgm.id = sm.sys_group_module_id"
        + " INNER JOIN sys_permission sp ON sp.id = sf.sys_permission_id"
        + " INNER JOIN sys_role_has_function srhf ON sf.id = srhf.sys_function_id"
    )
    List<UserPermissionVM> getAllPermissions();
}
