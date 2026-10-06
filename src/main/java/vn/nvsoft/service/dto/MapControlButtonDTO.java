package vn.nvsoft.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link vn.nvsoft.domain.MapControlButton} entity.
 */
public class MapControlButtonDTO implements Serializable {

    private Long id;


    private Long sourceButtonId;

    private String sourceButtonCmndTopic;

    private Long targetButtonId;

    private String targetButtonCmndTopic;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSourceButtonId() {
        return sourceButtonId;
    }

    public void setSourceButtonId(Long nVButtonId) {
        this.sourceButtonId = nVButtonId;
    }

    public String getSourceButtonCmndTopic() {
        return sourceButtonCmndTopic;
    }

    public void setSourceButtonCmndTopic(String nVButtonCmndTopic) {
        this.sourceButtonCmndTopic = nVButtonCmndTopic;
    }

    public Long getTargetButtonId() {
        return targetButtonId;
    }

    public void setTargetButtonId(Long nVButtonId) {
        this.targetButtonId = nVButtonId;
    }

    public String getTargetButtonCmndTopic() {
        return targetButtonCmndTopic;
    }

    public void setTargetButtonCmndTopic(String nVButtonCmndTopic) {
        this.targetButtonCmndTopic = nVButtonCmndTopic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MapControlButtonDTO mapControlButtonDTO = (MapControlButtonDTO) o;
        if (mapControlButtonDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mapControlButtonDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MapControlButtonDTO{" +
            "id=" + getId() +
            ", sourceButton=" + getSourceButtonId() +
            ", sourceButton='" + getSourceButtonCmndTopic() + "'" +
            ", targetButton=" + getTargetButtonId() +
            ", targetButton='" + getTargetButtonCmndTopic() + "'" +
            "}";
    }
}
