package vn.nvsoft.service.dto;
import java.time.Instant;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link vn.nvsoft.domain.ButtonTracking} entity.
 */
public class ButtonTrackingDTO implements Serializable {

    private Long id;

    private Instant updateDate;

    private Integer currentAliasState;

    private Long trackingId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Instant updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getCurrentAliasState() {
        return currentAliasState;
    }

    public void setCurrentAliasState(Integer currentAliasState) {
        this.currentAliasState = currentAliasState;
    }

    public Long getTrackingId() {
        return trackingId;
    }

    public void setTrackingId(Long trackingId) {
        this.trackingId = trackingId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ButtonTrackingDTO buttonTrackingDTO = (ButtonTrackingDTO) o;
        if (buttonTrackingDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), buttonTrackingDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ButtonTrackingDTO{" +
            "id=" + getId() +
            ", updateDate='" + getUpdateDate() + "'" +
            ", currentAliasState=" + getCurrentAliasState() +
            ", trackingId=" + getTrackingId() +
            "}";
    }
}
