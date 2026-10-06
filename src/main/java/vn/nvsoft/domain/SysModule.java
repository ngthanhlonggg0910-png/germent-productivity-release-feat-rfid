package vn.nvsoft.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A SysModule.
 */
@Entity
@Table(name = "sys_module")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SysModule implements Serializable {

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

    @Column(name = "image_uri")
    private String imageUri;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("sysModules")
    private SysGroupModule sysGroupModule;

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

    public SysModule name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCaption() {
        return caption;
    }

    public SysModule caption(String caption) {
        this.caption = caption;
        return this;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getImageUri() {
        return imageUri;
    }

    public SysModule imageUri(String imageUri) {
        this.imageUri = imageUri;
        return this;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public SysGroupModule getSysGroupModule() {
        return sysGroupModule;
    }

    public SysModule sysGroupModule(SysGroupModule sysGroupModule) {
        this.sysGroupModule = sysGroupModule;
        return this;
    }

    public void setSysGroupModule(SysGroupModule sysGroupModule) {
        this.sysGroupModule = sysGroupModule;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SysModule)) {
            return false;
        }
        return id != null && id.equals(((SysModule) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "SysModule{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", caption='" + getCaption() + "'" +
            ", imageUri='" + getImageUri() + "'" +
            "}";
    }
}
