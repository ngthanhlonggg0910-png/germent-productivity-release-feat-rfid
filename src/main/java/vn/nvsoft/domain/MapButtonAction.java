package vn.nvsoft.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

import vn.nvsoft.domain.enumeration.NVButtonState;

/**
 * A MapButtonAction.
 */
@Entity
@Table(name = "map_button_action")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MapButtonAction implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "source_alias_state")
    private Integer sourceAliasState;

    @Enumerated(EnumType.STRING)
    @Column(name = "target_state")
    private NVButtonState targetState;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("mapButtonActions")
    private NVButtonTrackingType trackingType;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSourceAliasState() {
        return sourceAliasState;
    }

    public MapButtonAction sourceAliasState(Integer sourceAliasState) {
        this.sourceAliasState = sourceAliasState;
        return this;
    }

    public void setSourceAliasState(Integer sourceAliasState) {
        this.sourceAliasState = sourceAliasState;
    }

    public NVButtonState getTargetState() {
        return targetState;
    }

    public MapButtonAction targetState(NVButtonState targetState) {
        this.targetState = targetState;
        return this;
    }

    public void setTargetState(NVButtonState targetState) {
        this.targetState = targetState;
    }

    public NVButtonTrackingType getTrackingType() {
        return trackingType;
    }

    public MapButtonAction trackingType(NVButtonTrackingType nVButtonTrackingType) {
        this.trackingType = nVButtonTrackingType;
        return this;
    }

    public void setTrackingType(NVButtonTrackingType nVButtonTrackingType) {
        this.trackingType = nVButtonTrackingType;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MapButtonAction)) {
            return false;
        }
        return id != null && id.equals(((MapButtonAction) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MapButtonAction{" +
            "id=" + getId() +
            ", sourceAliasState=" + getSourceAliasState() +
            ", targetState='" + getTargetState() + "'" +
            "}";
    }
}
