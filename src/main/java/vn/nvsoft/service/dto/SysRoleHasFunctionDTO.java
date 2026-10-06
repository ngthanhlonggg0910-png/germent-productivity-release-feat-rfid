package vn.nvsoft.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link vn.nvsoft.domain.SysRoleHasFunction} entity.
 */
public class SysRoleHasFunctionDTO implements Serializable {

    private Long id;

    private Boolean activated;

    private Long sysFunctionId;

    private String authorityName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isActivated() {
        return activated;
    }

    public void setActivated(Boolean activated) {
        this.activated = activated;
    }

    public Long getSysFunctionId() {
        return sysFunctionId;
    }

    public void setSysFunctionId(Long sysFunctionId) {
        this.sysFunctionId = sysFunctionId;
    }

    public String getAuthorityName() {
        return authorityName;
    }

    public void setAuthorityName(String authorityName) {
        this.authorityName = authorityName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SysRoleHasFunctionDTO sysRoleHasFunctionDTO = (SysRoleHasFunctionDTO) o;
        if (sysRoleHasFunctionDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sysRoleHasFunctionDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SysRoleHasFunctionDTO{" +
            "id=" + getId() +
            ", activated='" + isActivated() + "'" +
            ", sysFunction=" + getSysFunctionId() +
            ", authority='" + getAuthorityName() + "'" +
            "}";
    }
}
