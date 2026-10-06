package vn.nvsoft.service.dto;
import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import vn.nvsoft.domain.enumeration.NVButtonState;
import vn.nvsoft.domain.enumeration.LWT;
import vn.nvsoft.domain.enumeration.GroupButtonType;

/**
 * A DTO for the {@link vn.nvsoft.domain.NVButton} entity.
 */
public class NVButtonDTO implements Serializable {

    private Long id;

    private String name;

    private String discoveredOnTopic;

    
    private String cmndTopic;

    private String plOff;

    private String plOn;

    private String uniqId;

    private String devIds;

    private String teleTopic;

    private String statTopic;

    private String avtyTopic;

    private String plAvail;

    private String plNotAvail;

    private NVButtonState state;

    private LWT lwt;

    private Instant lastUpdateState;

    private GroupButtonType groupType;


    private Long buttonTrackingId;

    private Long deviceId;

    private String deviceName;

    private Long trackingTypeId;

    private String trackingTypeName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiscoveredOnTopic() {
        return discoveredOnTopic;
    }

    public void setDiscoveredOnTopic(String discoveredOnTopic) {
        this.discoveredOnTopic = discoveredOnTopic;
    }

    public String getCmndTopic() {
        return cmndTopic;
    }

    public void setCmndTopic(String cmndTopic) {
        this.cmndTopic = cmndTopic;
    }

    public String getPlOff() {
        return plOff;
    }

    public void setPlOff(String plOff) {
        this.plOff = plOff;
    }

    public String getPlOn() {
        return plOn;
    }

    public void setPlOn(String plOn) {
        this.plOn = plOn;
    }

    public String getUniqId() {
        return uniqId;
    }

    public void setUniqId(String uniqId) {
        this.uniqId = uniqId;
    }

    public String getDevIds() {
        return devIds;
    }

    public void setDevIds(String devIds) {
        this.devIds = devIds;
    }

    public String getTeleTopic() {
        return teleTopic;
    }

    public void setTeleTopic(String teleTopic) {
        this.teleTopic = teleTopic;
    }

    public String getStatTopic() {
        return statTopic;
    }

    public void setStatTopic(String statTopic) {
        this.statTopic = statTopic;
    }

    public String getAvtyTopic() {
        return avtyTopic;
    }

    public void setAvtyTopic(String avtyTopic) {
        this.avtyTopic = avtyTopic;
    }

    public String getPlAvail() {
        return plAvail;
    }

    public void setPlAvail(String plAvail) {
        this.plAvail = plAvail;
    }

    public String getPlNotAvail() {
        return plNotAvail;
    }

    public void setPlNotAvail(String plNotAvail) {
        this.plNotAvail = plNotAvail;
    }

    public NVButtonState getState() {
        return state;
    }

    public void setState(NVButtonState state) {
        this.state = state;
    }

    public LWT getLwt() {
        return lwt;
    }

    public void setLwt(LWT lwt) {
        this.lwt = lwt;
    }

    public Instant getLastUpdateState() {
        return lastUpdateState;
    }

    public void setLastUpdateState(Instant lastUpdateState) {
        this.lastUpdateState = lastUpdateState;
    }

    public GroupButtonType getGroupType() {
        return groupType;
    }

    public void setGroupType(GroupButtonType groupType) {
        this.groupType = groupType;
    }

    public Long getButtonTrackingId() {
        return buttonTrackingId;
    }

    public void setButtonTrackingId(Long buttonTrackingId) {
        this.buttonTrackingId = buttonTrackingId;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public Long getTrackingTypeId() {
        return trackingTypeId;
    }

    public void setTrackingTypeId(Long nVButtonTrackingTypeId) {
        this.trackingTypeId = nVButtonTrackingTypeId;
    }

    public String getTrackingTypeName() {
        return trackingTypeName;
    }

    public void setTrackingTypeName(String nVButtonTrackingTypeName) {
        this.trackingTypeName = nVButtonTrackingTypeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        NVButtonDTO nVButtonDTO = (NVButtonDTO) o;
        if (nVButtonDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), nVButtonDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "NVButtonDTO{" +
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
            ", buttonTracking=" + getButtonTrackingId() +
            ", device=" + getDeviceId() +
            ", device='" + getDeviceName() + "'" +
            ", trackingType=" + getTrackingTypeId() +
            ", trackingType='" + getTrackingTypeName() + "'" +
            "}";
    }
}
