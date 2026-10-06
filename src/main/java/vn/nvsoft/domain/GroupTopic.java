package vn.nvsoft.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

import vn.nvsoft.domain.enumeration.TopicType;

import vn.nvsoft.domain.enumeration.GroupButtonType;

/**
 * A GroupTopic.
 */
@Entity
@Table(name = "group_topic")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class GroupTopic implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "topic_type")
    private TopicType topicType;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private GroupButtonType type;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public GroupTopic name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TopicType getTopicType() {
        return topicType;
    }

    public GroupTopic topicType(TopicType topicType) {
        this.topicType = topicType;
        return this;
    }

    public void setTopicType(TopicType topicType) {
        this.topicType = topicType;
    }

    public GroupButtonType getType() {
        return type;
    }

    public GroupTopic type(GroupButtonType type) {
        this.type = type;
        return this;
    }

    public void setType(GroupButtonType type) {
        this.type = type;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GroupTopic)) {
            return false;
        }
        return id != null && id.equals(((GroupTopic) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "GroupTopic{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", topicType='" + getTopicType() + "'" +
            ", type='" + getType() + "'" +
            "}";
    }
}
