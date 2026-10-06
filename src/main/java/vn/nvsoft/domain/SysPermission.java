package vn.nvsoft.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A SysPermission.
 */
@Entity
@Table(name = "sys_permission")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SysPermission implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(max = 100)
    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Size(max = 255)
    @Column(name = "caption", length = 255)
    private String caption;

    @Column(name = "level")
    private Integer level;

    @Column(name = "image_uri")
    private String imageUri;

    @Size(max = 50)
    @Column(name = "event_name", length = 50)
    private String eventName;

    @Size(max = 5)
    @Column(name = "hotkey", length = 5)
    private String hotkey;

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

    public SysPermission name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCaption() {
        return caption;
    }

    public SysPermission caption(String caption) {
        this.caption = caption;
        return this;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public Integer getLevel() {
        return level;
    }

    public SysPermission level(Integer level) {
        this.level = level;
        return this;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getImageUri() {
        return imageUri;
    }

    public SysPermission imageUri(String imageUri) {
        this.imageUri = imageUri;
        return this;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public String getEventName() {
        return eventName;
    }

    public SysPermission eventName(String eventName) {
        this.eventName = eventName;
        return this;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getHotkey() {
        return hotkey;
    }

    public SysPermission hotkey(String hotkey) {
        this.hotkey = hotkey;
        return this;
    }

    public void setHotkey(String hotkey) {
        this.hotkey = hotkey;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SysPermission)) {
            return false;
        }
        return id != null && id.equals(((SysPermission) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "SysPermission{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", caption='" + getCaption() + "'" +
            ", level=" + getLevel() +
            ", imageUri='" + getImageUri() + "'" +
            ", eventName='" + getEventName() + "'" +
            ", hotkey='" + getHotkey() + "'" +
            "}";
    }
}
