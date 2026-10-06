package vn.nvsoft.service.dto;
import java.io.Serializable;
import java.util.Objects;
import vn.nvsoft.domain.enumeration.TopicType;
import vn.nvsoft.domain.enumeration.GroupButtonType;

/**
 * A DTO for the {@link vn.nvsoft.domain.GroupTopic} entity.
 */
public class GroupTopicDTO implements Serializable {

    private Long id;

    private String name;

    private TopicType topicType;

    private GroupButtonType type;


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

    public TopicType getTopicType() {
        return topicType;
    }

    public void setTopicType(TopicType topicType) {
        this.topicType = topicType;
    }

    public GroupButtonType getType() {
        return type;
    }

    public void setType(GroupButtonType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        GroupTopicDTO groupTopicDTO = (GroupTopicDTO) o;
        if (groupTopicDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), groupTopicDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "GroupTopicDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", topicType='" + getTopicType() + "'" +
            ", type='" + getType() + "'" +
            "}";
    }
}
