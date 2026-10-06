package vn.nvsoft.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link vn.nvsoft.domain.SysFunction} entity.
 */
public class SysFunctionDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 100)
    private String name;

    @Size(max = 255)
    private String caption;

    private Integer level;

    private String imageUri;

    @Size(max = 50)
    private String eventName;

    @Size(max = 5)
    private String hotkey;


    private Long sysPermissionId;

    private Long sysModuleId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
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

    public Long getSysPermissionId() {
        return sysPermissionId;
    }

    public void setSysPermissionId(Long sysPermissionId) {
        this.sysPermissionId = sysPermissionId;
    }

    public Long getSysModuleId() {
        return sysModuleId;
    }

    public void setSysModuleId(Long sysModuleId) {
        this.sysModuleId = sysModuleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SysFunctionDTO sysFunctionDTO = (SysFunctionDTO) o;
        if (sysFunctionDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sysFunctionDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SysFunctionDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", caption='" + getCaption() + "'" +
            ", level=" + getLevel() +
            ", imageUri='" + getImageUri() + "'" +
            ", eventName='" + getEventName() + "'" +
            ", hotkey='" + getHotkey() + "'" +
            ", sysPermission=" + getSysPermissionId() +
            ", sysModule=" + getSysModuleId() +
            "}";
    }
}
