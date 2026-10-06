package vn.nvsoft.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link vn.nvsoft.domain.Video} entity.
 */
public class VideoDTO implements Serializable {

    private Long id;

    private String name;

    private String timePlay;


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

    public String getTimePlay() {
        return timePlay;
    }

    public void setTimePlay(String timePlay) {
        this.timePlay = timePlay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        VideoDTO videoDTO = (VideoDTO) o;
        if (videoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), videoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "VideoDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", timePlay='" + getTimePlay() + "'" +
            "}";
    }
}
