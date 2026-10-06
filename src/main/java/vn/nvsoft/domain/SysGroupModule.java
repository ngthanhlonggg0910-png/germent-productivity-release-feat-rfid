package vn.nvsoft.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A SysGroupModule.
 */
@Entity
@Table(name = "sys_group_module")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SysGroupModule implements Serializable {

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

    @Column(name = "primary_image_uri")
    private String primaryImageUri;

    @Column(name = "secondary_image_uri")
    private String secondaryImageUri;

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

    public SysGroupModule name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCaption() {
        return caption;
    }

    public SysGroupModule caption(String caption) {
        this.caption = caption;
        return this;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getPrimaryImageUri() {
        return primaryImageUri;
    }

    public SysGroupModule primaryImageUri(String primaryImageUri) {
        this.primaryImageUri = primaryImageUri;
        return this;
    }

    public void setPrimaryImageUri(String primaryImageUri) {
        this.primaryImageUri = primaryImageUri;
    }

    public String getSecondaryImageUri() {
        return secondaryImageUri;
    }

    public SysGroupModule secondaryImageUri(String secondaryImageUri) {
        this.secondaryImageUri = secondaryImageUri;
        return this;
    }

    public void setSecondaryImageUri(String secondaryImageUri) {
        this.secondaryImageUri = secondaryImageUri;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SysGroupModule)) {
            return false;
        }
        return id != null && id.equals(((SysGroupModule) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "SysGroupModule{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", caption='" + getCaption() + "'" +
            ", primaryImageUri='" + getPrimaryImageUri() + "'" +
            ", secondaryImageUri='" + getSecondaryImageUri() + "'" +
            "}";
    }
}
