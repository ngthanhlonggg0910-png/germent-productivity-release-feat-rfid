package vn.nvsoft.service.vm;

public class SysFunctionVM {
    Long id;
    String caption;
    String eventName;
    String hotkey;
    String imageUri;
    Integer level;
    String name;
    String role;
    Long roleHasFuncId;
    Boolean roleHasFuncActivated;
//    private SysModuleVM sysModuleVM;
    private SysPermissionVM sysPermissionVM;

    public SysFunctionVM(Long id, String caption, String eventName, String hotkey, String imageUri, Integer level, String name, String role) {
        this.id = id;
        this.caption = caption;
        this.eventName = eventName;
        this.hotkey = hotkey;
        this.imageUri = imageUri;
        this.level = level;
        this.name = name;
        this.role = role;
    }

    public SysFunctionVM() {
    }

    public Long getRoleHasFuncId() {
        return roleHasFuncId;
    }

    public void setRoleHasFuncId(Long roleHasFuncId) {
        this.roleHasFuncId = roleHasFuncId;
    }

    public Boolean getRoleHasFuncActivated() {
        return roleHasFuncActivated;
    }

    public void setRoleHasFuncActivated(Boolean roleHasFuncActivated) {
        this.roleHasFuncActivated = roleHasFuncActivated;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getHotkey() {
        return hotkey;
    }

    public void setHotkey(String hotkey) {
        this.hotkey = hotkey;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

//    public void setSysModuleVM(SysModuleVM sysModuleVM) {
//        this.sysModuleVM = sysModuleVM;
//    }
//
//    public SysModuleVM getSysModuleVM() {
//        return sysModuleVM;
//    }
//
    public void setSysPermissionVM(SysPermissionVM sysPermissionVM) {
        this.sysPermissionVM = sysPermissionVM;
    }

    public SysPermissionVM getSysPermissionVM() {
        return sysPermissionVM;
    }

}
