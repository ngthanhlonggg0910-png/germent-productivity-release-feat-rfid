package vn.nvsoft.service.dto;
import java.time.Instant;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link vn.nvsoft.domain.Tracking} entity.
 */
public class TrackingDTO implements Serializable {

    private Long id;

    private Long nvButtonId;

    private Instant state1Time;

    private Instant state2Time;

    private Instant state3Time;

    private Instant state4Time;

    private Instant state5Time;

    private Instant state6Time;


    private Long trackingTypeId;

    private String trackingTypeName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNvButtonId() {
        return nvButtonId;
    }

    public void setNvButtonId(Long nvButtonId) {
        this.nvButtonId = nvButtonId;
    }

    public Instant getState1Time() {
        return state1Time;
    }

    public void setState1Time(Instant state1Time) {
        this.state1Time = state1Time;
    }

    public Instant getState2Time() {
        return state2Time;
    }

    public void setState2Time(Instant state2Time) {
        this.state2Time = state2Time;
    }

    public Instant getState3Time() {
        return state3Time;
    }

    public void setState3Time(Instant state3Time) {
        this.state3Time = state3Time;
    }

    public Instant getState4Time() {
        return state4Time;
    }

    public void setState4Time(Instant state4Time) {
        this.state4Time = state4Time;
    }

    public Instant getState5Time() {
        return state5Time;
    }

    public void setState5Time(Instant state5Time) {
        this.state5Time = state5Time;
    }

    public Instant getState6Time() {
        return state6Time;
    }

    public void setState6Time(Instant state6Time) {
        this.state6Time = state6Time;
    }

    public Long getTrackingTypeId() {
        return trackingTypeId;
    }

    public void setTrackingTypeId(Long nVButtonTrackingTypeId) {
        this.trackingTypeId = nVButtonTrackingTypeId;
    }

    public String getTrackingTypeName() {
        return trackingTypeName;
    }

    public void setTrackingTypeName(String nVButtonTrackingTypeName) {
        this.trackingTypeName = nVButtonTrackingTypeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TrackingDTO trackingDTO = (TrackingDTO) o;
        if (trackingDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), trackingDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TrackingDTO{" +
            "id=" + getId() +
            ", nvButtonId=" + getNvButtonId() +
            ", state1Time='" + getState1Time() + "'" +
            ", state2Time='" + getState2Time() + "'" +
            ", state3Time='" + getState3Time() + "'" +
            ", state4Time='" + getState4Time() + "'" +
            ", state5Time='" + getState5Time() + "'" +
            ", state6Time='" + getState6Time() + "'" +
            ", trackingType=" + getTrackingTypeId() +
            ", trackingType='" + getTrackingTypeName() + "'" +
            "}";
    }
}
