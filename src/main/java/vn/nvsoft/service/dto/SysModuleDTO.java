package vn.nvsoft.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link vn.nvsoft.domain.SysModule} entity.
 */
public class SysModuleDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 100)
    private String name;

    @Size(max = 255)
    private String caption;

    private String imageUri;


    private Long sysGroupModuleId;

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

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public Long getSysGroupModuleId() {
        return sysGroupModuleId;
    }

    public void setSysGroupModuleId(Long sysGroupModuleId) {
        this.sysGroupModuleId = sysGroupModuleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SysModuleDTO sysModuleDTO = (SysModuleDTO) o;
        if (sysModuleDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sysModuleDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SysModuleDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", caption='" + getCaption() + "'" +
            ", imageUri='" + getImageUri() + "'" +
            ", sysGroupModule=" + getSysGroupModuleId() +
            "}";
    }
}
