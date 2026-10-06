package vn.nvsoft.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A Tracking.
 */
@Entity
@Table(name = "tracking")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Tracking implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "nv_button_id")
    private Long nvButtonId;

    @Column(name = "state_1_time")
    private Instant state1Time;

    @Column(name = "state_2_time")
    private Instant state2Time;

    @Column(name = "state_3_time")
    private Instant state3Time;

    @Column(name = "state_4_time")
    private Instant state4Time;

    @Column(name = "state_5_time")
    private Instant state5Time;

    @Column(name = "state_6_time")
    private Instant state6Time;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("trackings")
    private NVButtonTrackingType trackingType;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNvButtonId() {
        return nvButtonId;
    }

    public Tracking nvButtonId(Long nvButtonId) {
        this.nvButtonId = nvButtonId;
        return this;
    }

    public void setNvButtonId(Long nvButtonId) {
        this.nvButtonId = nvButtonId;
    }

    public Instant getState1Time() {
        return state1Time;
    }

    public Tracking state1Time(Instant state1Time) {
        this.state1Time = state1Time;
        return this;
    }

    public void setState1Time(Instant state1Time) {
        this.state1Time = state1Time;
    }

    public Instant getState2Time() {
        return state2Time;
    }

    public Tracking state2Time(Instant state2Time) {
        this.state2Time = state2Time;
        return this;
    }

    public void setState2Time(Instant state2Time) {
        this.state2Time = state2Time;
    }

    public Instant getState3Time() {
        return state3Time;
    }

    public Tracking state3Time(Instant state3Time) {
        this.state3Time = state3Time;
        return this;
    }

    public void setState3Time(Instant state3Time) {
        this.state3Time = state3Time;
    }

    public Instant getState4Time() {
        return state4Time;
    }

    public Tracking state4Time(Instant state4Time) {
        this.state4Time = state4Time;
        return this;
    }

    public void setState4Time(Instant state4Time) {
        this.state4Time = state4Time;
    }

    public Instant getState5Time() {
        return state5Time;
    }

    public Tracking state5Time(Instant state5Time) {
        this.state5Time = state5Time;
        return this;
    }

    public void setState5Time(Instant state5Time) {
        this.state5Time = state5Time;
    }

    public Instant getState6Time() {
        return state6Time;
    }

    public Tracking state6Time(Instant state6Time) {
        this.state6Time = state6Time;
        return this;
    }

    public void setState6Time(Instant state6Time) {
        this.state6Time = state6Time;
    }

    public NVButtonTrackingType getTrackingType() {
        return trackingType;
    }

    public Tracking trackingType(NVButtonTrackingType nVButtonTrackingType) {
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
        if (!(o instanceof Tracking)) {
            return false;
        }
        return id != null && id.equals(((Tracking) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Tracking{" +
            "id=" + getId() +
            ", nvButtonId=" + getNvButtonId() +
            ", state1Time='" + getState1Time() + "'" +
            ", state2Time='" + getState2Time() + "'" +
            ", state3Time='" + getState3Time() + "'" +
            ", state4Time='" + getState4Time() + "'" +
            ", state5Time='" + getState5Time() + "'" +
            ", state6Time='" + getState6Time() + "'" +
            "}";
    }
}
