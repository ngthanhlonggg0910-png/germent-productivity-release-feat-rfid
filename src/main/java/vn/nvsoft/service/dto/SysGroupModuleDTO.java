package vn.nvsoft.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link vn.nvsoft.domain.SysGroupModule} entity.
 */
public class SysGroupModuleDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 100)
    private String name;

    @Size(max = 255)
    private String caption;

    private String primaryImageUri;

    private String secondaryImageUri;


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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SysGroupModuleDTO sysGroupModuleDTO = (SysGroupModuleDTO) o;
        if (sysGroupModuleDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sysGroupModuleDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SysGroupModuleDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", caption='" + getCaption() + "'" +
            ", primaryImageUri='" + getPrimaryImageUri() + "'" +
            ", secondaryImageUri='" + getSecondaryImageUri() + "'" +
            "}";
    }
}
