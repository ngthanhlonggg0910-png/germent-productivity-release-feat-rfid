package vn.nvsoft.service.vm;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.HashSet;
import java.util.Set;

public class SysGroupModuleVM {
    Long id;
    String caption;
    String name;
    String primaryImageUri;
    String secondaryImageUri;
    Set<SysModuleVM> sysModuleVMs = new HashSet<>();

    public SysGroupModuleVM() {
    }

    public SysGroupModuleVM(Long id, String caption, String name, String primaryImageUri, String secondaryImageUri) {
        this.id = id;
        this.caption = caption;
        this.name = name;
        this.primaryImageUri = primaryImageUri;
        this.secondaryImageUri = secondaryImageUri;
    }

    public Set<SysModuleVM> getSysModuleVMs() {
        return sysModuleVMs;
    }

    public void setSysModuleVMs(Set<SysModuleVM> sysModuleVMs) {
        this.sysModuleVMs = sysModuleVMs;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrimaryImageUri() {
        return primaryImageUri;
    }

    public void setPrimaryImageUri(String primaryImageUri) {
        this.primaryImageUri = primaryImageUri;
    }

    public String getSecondaryImageUri() {
        return secondaryImageUri;
    }

    public void setSecondaryImageUri(String secondaryImageUri) {
        this.secondaryImageUri = secondaryImageUri;
    }

    @JsonIgnoreProperties
    public void addModuleVM(SysModuleVM sysModuleVM) {
        this.sysModuleVMs.add(sysModuleVM);
    }
}
