package vn.nvsoft.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

import vn.nvsoft.domain.enumeration.NVButtonState;

import vn.nvsoft.domain.enumeration.LWT;

import vn.nvsoft.domain.enumeration.GroupButtonType;

/**
 * A NVButton.
 */
@Entity
@Table(name = "nv_button")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class NVButton implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "discovered_on_topic")
    private String discoveredOnTopic;

    
    @Column(name = "cmnd_topic", unique = true)
    private String cmndTopic;

    @Column(name = "pl_off")
    private String plOff;

    @Column(name = "pl_on")
    private String plOn;

    @Column(name = "uniq_id")
    private String uniqId;

    @Column(name = "dev_ids")
    private String devIds;

    @Column(name = "tele_topic")
    private String teleTopic;

    @Column(name = "stat_topic")
    private String statTopic;

    @Column(name = "avty_topic")
    private String avtyTopic;

    @Column(name = "pl_avail")
    private String plAvail;

    @Column(name = "pl_not_avail")
    private String plNotAvail;

    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    private NVButtonState state;

    @Enumerated(EnumType.STRING)
    @Column(name = "lwt")
    private LWT lwt;

    @Column(name = "last_update_state")
    private Instant lastUpdateState;

    @Enumerated(EnumType.STRING)
    @Column(name = "group_type")
    private GroupButtonType groupType;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(unique = true)
    private ButtonTracking buttonTracking;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("nVButtons")
    private Device device;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties("nVButtons")
    private NVButtonTrackingType trackingType;

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

    public NVButton name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiscoveredOnTopic() {
        return discoveredOnTopic;
    }

    public NVButton discoveredOnTopic(String discoveredOnTopic) {
        this.discoveredOnTopic = discoveredOnTopic;
        return this;
    }

    public void setDiscoveredOnTopic(String discoveredOnTopic) {
        this.discoveredOnTopic = discoveredOnTopic;
    }

    public String getCmndTopic() {
        return cmndTopic;
    }

    public NVButton cmndTopic(String cmndTopic) {
        this.cmndTopic = cmndTopic;
        return this;
    }

    public void setCmndTopic(String cmndTopic) {
        this.cmndTopic = cmndTopic;
    }

    public String getPlOff() {
        return plOff;
    }

    public NVButton plOff(String plOff) {
        this.plOff = plOff;
        return this;
    }

    public void setPlOff(String plOff) {
        this.plOff = plOff;
    }

    public String getPlOn() {
        return plOn;
    }

    public NVButton plOn(String plOn) {
        this.plOn = plOn;
        return this;
    }

    public void setPlOn(String plOn) {
        this.plOn = plOn;
    }

    public String getUniqId() {
        return uniqId;
    }

    public NVButton uniqId(String uniqId) {
        this.uniqId = uniqId;
        return this;
    }

    public void setUniqId(String uniqId) {
        this.uniqId = uniqId;
    }

    public String getDevIds() {
        return devIds;
    }

    public NVButton devIds(String devIds) {
        this.devIds = devIds;
        return this;
    }

    public void setDevIds(String devIds) {
        this.devIds = devIds;
    }

    public String getTeleTopic() {
        return teleTopic;
    }

    public NVButton teleTopic(String teleTopic) {
        this.teleTopic = teleTopic;
        return this;
    }

    public void setTeleTopic(String teleTopic) {
        this.teleTopic = teleTopic;
    }

    public String getStatTopic() {
        return statTopic;
    }

    public NVButton statTopic(String statTopic) {
        this.statTopic = statTopic;
        return this;
    }

    public void setStatTopic(String statTopic) {
        this.statTopic = statTopic;
    }

    public String getAvtyTopic() {
        return avtyTopic;
    }

    public NVButton avtyTopic(String avtyTopic) {
        this.avtyTopic = avtyTopic;
        return this;
    }

    public void setAvtyTopic(String avtyTopic) {
        this.avtyTopic = avtyTopic;
    }

    public String getPlAvail() {
        return plAvail;
    }

    public NVButton plAvail(String plAvail) {
        this.plAvail = plAvail;
        return this;
    }

    public void setPlAvail(String plAvail) {
        this.plAvail = plAvail;
    }

    public String getPlNotAvail() {
        return plNotAvail;
    }

    public NVButton plNotAvail(String plNotAvail) {
        this.plNotAvail = plNotAvail;
        return this;
    }

    public void setPlNotAvail(String plNotAvail) {
        this.plNotAvail = plNotAvail;
    }

    public NVButtonState getState() {
        return state;
    }

    public NVButton state(NVButtonState state) {
        this.state = state;
        return this;
    }

    public void setState(NVButtonState state) {
        this.state = state;
    }

    public LWT getLwt() {
        return lwt;
    }

    public NVButton lwt(LWT lwt) {
        this.lwt = lwt;
        return this;
    }

    public void setLwt(LWT lwt) {
        this.lwt = lwt;
    }

    public Instant getLastUpdateState() {
        return lastUpdateState;
    }

    public NVButton lastUpdateState(Instant lastUpdateState) {
        this.lastUpdateState = lastUpdateState;
        return this;
    }

    public void setLastUpdateState(Instant lastUpdateState) {
        this.lastUpdateState = lastUpdateState;
    }

    public GroupButtonType getGroupType() {
        return groupType;
    }

    public NVButton groupType(GroupButtonType groupType) {
        this.groupType = groupType;
        return this;
    }

    public void setGroupType(GroupButtonType groupType) {
        this.groupType = groupType;
    }

    public ButtonTracking getButtonTracking() {
        return buttonTracking;
    }

    public NVButton buttonTracking(ButtonTracking buttonTracking) {
        this.buttonTracking = buttonTracking;
        return this;
    }

    public void setButtonTracking(ButtonTracking buttonTracking) {
        this.buttonTracking = buttonTracking;
    }

    public Device getDevice() {
        return device;
    }

    public NVButton device(Device device) {
        this.device = device;
        return this;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public NVButtonTrackingType getTrackingType() {
        return trackingType;
    }

    public NVButton trackingType(NVButtonTrackingType nVButtonTrackingType) {
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
        if (!(o instanceof NVButton)) {
            return false;
        }
        return id != null && id.equals(((NVButton) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "NVButton{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", discoveredOnTopic='" + getDiscoveredOnTopic() + "'" +
            ", cmndTopic='" + getCmndTopic() + "'" +
            ", plOff='" + getPlOff() + "'" +
            ", plOn='" + getPlOn() + "'" +
            ", uniqId='" + getUniqId() + "'" +
            ", devIds='" + getDevIds() + "'" +
            ", teleTopic='" + getTeleTopic() + "'" +
            ", statTopic='" + getStatTopic() + "'" +
            ", avtyTopic='" + getAvtyTopic() + "'" +
            ", plAvail='" + getPlAvail() + "'" +
            ", plNotAvail='" + getPlNotAvail() + "'" +
            ", state='" + getState() + "'" +
            ", lwt='" + getLwt() + "'" +
            ", lastUpdateState='" + getLastUpdateState() + "'" +
            ", groupType='" + getGroupType() + "'" +
            "}";
    }
}
