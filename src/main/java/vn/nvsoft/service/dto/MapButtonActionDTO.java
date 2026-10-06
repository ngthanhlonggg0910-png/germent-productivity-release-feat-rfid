package vn.nvsoft.service.dto;
import java.io.Serializable;
import java.util.Objects;
import vn.nvsoft.domain.enumeration.NVButtonState;

/**
 * A DTO for the {@link vn.nvsoft.domain.MapButtonAction} entity.
 */
public class MapButtonActionDTO implements Serializable {

    private Long id;

    private Integer sourceAliasState;

    private NVButtonState targetState;


    private Long trackingTypeId;

    private String trackingTypeName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSourceAliasState() {
        return sourceAliasState;
    }

    public void setSourceAliasState(Integer sourceAliasState) {
        this.sourceAliasState = sourceAliasState;
    }

    public NVButtonState getTargetState() {
        return targetState;
    }

    public void setTargetState(NVButtonState targetState) {
        this.targetState = targetState;
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

        MapButtonActionDTO mapButtonActionDTO = (MapButtonActionDTO) o;
        if (mapButtonActionDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mapButtonActionDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MapButtonActionDTO{" +
            "id=" + getId() +
            ", sourceAliasState=" + getSourceAliasState() +
            ", targetState='" + getTargetState() + "'" +
            ", trackingType=" + getTrackingTypeId() +
            ", trackingType='" + getTrackingTypeName() + "'" +
            "}";
    }
}
