package vn.nvsoft.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link vn.nvsoft.domain.DeviceToken} entity.
 */
public class DeviceTokenDTO implements Serializable {

    private Long id;

    @NotNull
    private String user;

    @NotNull
    private String device;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DeviceTokenDTO)) {
            return false;
        }

        return id != null && id.equals(((DeviceTokenDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DeviceTokenDTO{" +
            "id=" + getId() +
            ", user='" + getUser() + "'" +
            ", device='" + getDevice() + "'" +
            "}";
    }
}
