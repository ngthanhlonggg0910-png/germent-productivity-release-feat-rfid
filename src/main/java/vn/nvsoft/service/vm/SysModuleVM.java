package vn.nvsoft.service.vm;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.HashSet;
import java.util.Set;

public class SysModuleVM {
    Long id;
    String caption;
    String imageUri;
    String name;
    Set<SysFunctionVM> sysFunctionVMs = new HashSet<>();
//    private SysGroupModuleVM sysGroupModuleVM;
    String codePre;

    public SysModuleVM(Long id, String caption, String imageUri, String name) {
        this.id = id;
        this.caption = caption;
        this.imageUri = imageUri;
        this.name = name;
        this.codePre = "";
    }

    public SysModuleVM() {
    }

    public String getCodePre() {
        return codePre;
    }

    public void setCodePre(String codePre) {
        this.codePre = codePre;
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

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonIgnoreProperties
    public void addFunction(SysFunctionVM sysFunctionVM) {
        sysFunctionVMs.add(sysFunctionVM);
    }

    public Set<SysFunctionVM> getSysFunctionVMs() {
        return sysFunctionVMs;
    }

    public void setSysFunctionVMs(Set<SysFunctionVM> sysFunctionVMs) {
        this.sysFunctionVMs = sysFunctionVMs;
    }

//    public SysGroupModuleVM getSysGroupModuleVM() {
//        return sysGroupModuleVM;
//    }
//
//    public void setSysGroupModuleVM(SysGroupModuleVM sysGroupModuleVM) {
//        this.sysGroupModuleVM = sysGroupModuleVM;
//    }
}
