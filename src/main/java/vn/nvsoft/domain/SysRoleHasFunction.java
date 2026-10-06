package vn.nvsoft.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A SysRoleHasFunction.
 */
@Entity
@Table(name = "sys_role_has_function")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SysRoleHasFunction implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "activated")
    private Boolean activated;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("sysRoleHasFunctions")
    private SysFunction sysFunction;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="authority_name")
    @JsonIgnoreProperties("sysRoleHasFunctions")
    private Authority authority;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isActivated() {
        return activated;
    }

    public SysRoleHasFunction activated(Boolean activated) {
        this.activated = activated;
        return this;
    }

    public void setActivated(Boolean activated) {
        this.activated = activated;
    }

    public SysFunction getSysFunction() {
        return sysFunction;
    }

    public SysRoleHasFunction sysFunction(SysFunction sysFunction) {
        this.sysFunction = sysFunction;
        return this;
    }

    public void setSysFunction(SysFunction sysFunction) {
        this.sysFunction = sysFunction;
    }

    public Authority getAuthority() {
        return authority;
    }

    public SysRoleHasFunction authority(Authority authority) {
        this.authority = authority;
        return this;
    }

    public void setAuthority(Authority authority) {
        this.authority = authority;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SysRoleHasFunction)) {
            return false;
        }
        return id != null && id.equals(((SysRoleHasFunction) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "SysRoleHasFunction{" +
            "id=" + getId() +
            ", activated='" + isActivated() + "'" +
            "}";
    }
}
