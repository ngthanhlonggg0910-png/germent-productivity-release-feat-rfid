package vn.nvsoft.service.vm;

public class SysPermissionVM {
    Long id;
    String caption;
    String eventName;
    String hotkey;
    String imageUri;
    Integer level;
    String name;
//    Set<SysFunctionVM> sysFunctionVMs = new HashSet<>();

    public SysPermissionVM(Long id, String caption, String eventName, String hotkey, String imageUri, Integer level, String name) {
        this.id = id;
        this.caption = caption;
        this.eventName = eventName;
        this.hotkey = hotkey;
        this.imageUri = imageUri;
        this.level = level;
        this.name = name;
    }

    public SysPermissionVM() {
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

//    @JsonIgnoreProperties
//    public void addFunctionVM(SysFunctionVM sysFunctionVM) {
//        sysFunctionVMs.add(sysFunctionVM);
//    }
//
//    public Set<SysFunctionVM> getSysFunctionVMs() {
//        return sysFunctionVMs;
//    }
//
//    public void setSysFunctionVMs(Set<SysFunctionVM> sysFunctionVMs) {
//        this.sysFunctionVMs = sysFunctionVMs;
//    }
}
