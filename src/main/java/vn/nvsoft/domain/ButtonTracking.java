package vn.nvsoft.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A ButtonTracking.
 */
@Entity
@Table(name = "button_tracking")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ButtonTracking implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "update_date")
    private Instant updateDate;

    @Column(name = "current_alias_state")
    private Integer currentAliasState;

    @Column(name = "tracking_id")
    private Long trackingId;

    @OneToOne(mappedBy = "buttonTracking")
    @JsonIgnore
    private NVButton nVButton;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getUpdateDate() {
        return updateDate;
    }

    public ButtonTracking updateDate(Instant updateDate) {
        this.updateDate = updateDate;
        return this;
    }

    public void setUpdateDate(Instant updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getCurrentAliasState() {
        return currentAliasState;
    }

    public ButtonTracking currentAliasState(Integer currentAliasState) {
        this.currentAliasState = currentAliasState;
        return this;
    }

    public void setCurrentAliasState(Integer currentAliasState) {
        this.currentAliasState = currentAliasState;
    }

    public Long getTrackingId() {
        return trackingId;
    }

    public ButtonTracking trackingId(Long trackingId) {
        this.trackingId = trackingId;
        return this;
    }

    public void setTrackingId(Long trackingId) {
        this.trackingId = trackingId;
    }

    public NVButton getNVButton() {
        return nVButton;
    }

    public ButtonTracking nVButton(NVButton nVButton) {
        this.nVButton = nVButton;
        return this;
    }

    public void setNVButton(NVButton nVButton) {
        this.nVButton = nVButton;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ButtonTracking)) {
            return false;
        }
        return id != null && id.equals(((ButtonTracking) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ButtonTracking{" +
            "id=" + getId() +
            ", updateDate='" + getUpdateDate() + "'" +
            ", currentAliasState=" + getCurrentAliasState() +
            ", trackingId=" + getTrackingId() +
            "}";
    }
}
