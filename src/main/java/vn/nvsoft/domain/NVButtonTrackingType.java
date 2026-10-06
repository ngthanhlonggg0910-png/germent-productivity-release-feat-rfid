package vn.nvsoft.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A NVButtonTrackingType.
 */
@Entity
@Table(name = "nv_button_tracking_type")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class NVButtonTrackingType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    
    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "number_of_state")
    private Integer numberOfState;

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

    public NVButtonTrackingType name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumberOfState() {
        return numberOfState;
    }

    public NVButtonTrackingType numberOfState(Integer numberOfState) {
        this.numberOfState = numberOfState;
        return this;
    }

    public void setNumberOfState(Integer numberOfState) {
        this.numberOfState = numberOfState;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NVButtonTrackingType)) {
            return false;
        }
        return id != null && id.equals(((NVButtonTrackingType) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "NVButtonTrackingType{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", numberOfState=" + getNumberOfState() +
            "}";
    }
}
