package vn.nvsoft.service.vm;

public interface UserPermissionVM {
    public Long getRoleHasFuncId();
    public Boolean getRoleHasFuncActivated();
    public Long getFuncId();
    public String getFuncName();
    public String getModuleCodePre();
    public String getFuncImageUri();
    public String getFuncCaption();;
    public Integer getFuncLevel();
    public String getFuncEventName();
    public String getFuncHotkey();
    public Long getPermissionId();
    public String getPermissionName();;
    public String getPermissionImageUri();
    public String getPermissionCaption();
    public Integer getPermissionLevel();
    public String getPermissionEventName();
    public String getPermissionHotkey();
    public Long getModuleId();
    public String getModuleName();;
    public String getModuleCaption();
    public String getModuleImageUri();
    public Long getGroupModuleId();
    public String getGroupModuleName();
    public String getGroupModuleCaption();
    public String getGroupModulePrimaryImageUri();

    public String getGroupModuleSecondaryImageUri();
    public String getRoleName();
}
