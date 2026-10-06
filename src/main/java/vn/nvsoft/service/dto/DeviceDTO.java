package vn.nvsoft.service.dto;
import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import vn.nvsoft.domain.enumeration.LWT;

/**
 * A DTO for the {@link vn.nvsoft.domain.Device} entity.
 */
public class DeviceDTO implements Serializable {

    private Long id;

    private String name;

    private String devIds;

    
    private String stateTopic;

    
    private String lwtTopic;

    private String sw;

    private String module;

    private String restartReason;

    private Instant time;

    private String uptime;

    private String hostName;

    private String ipAddress;

    private LWT lwt;


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

    public String getDevIds() {
        return devIds;
    }

    public void setDevIds(String devIds) {
        this.devIds = devIds;
    }

    public String getStateTopic() {
        return stateTopic;
    }

    public void setStateTopic(String stateTopic) {
        this.stateTopic = stateTopic;
    }

    public String getLwtTopic() {
        return lwtTopic;
    }

    public void setLwtTopic(String lwtTopic) {
        this.lwtTopic = lwtTopic;
    }

    public String getSw() {
        return sw;
    }

    public void setSw(String sw) {
        this.sw = sw;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getRestartReason() {
        return restartReason;
    }

    public void setRestartReason(String restartReason) {
        this.restartReason = restartReason;
    }

    public Instant getTime() {
        return time;
    }

    public void setTime(Instant time) {
        this.time = time;
    }

    public String getUptime() {
        return uptime;
    }

    public void setUptime(String uptime) {
        this.uptime = uptime;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public LWT getLwt() {
        return lwt;
    }

    public void setLwt(LWT lwt) {
        this.lwt = lwt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DeviceDTO deviceDTO = (DeviceDTO) o;
        if (deviceDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), deviceDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DeviceDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", devIds='" + getDevIds() + "'" +
            ", stateTopic='" + getStateTopic() + "'" +
            ", lwtTopic='" + getLwtTopic() + "'" +
            ", sw='" + getSw() + "'" +
            ", module='" + getModule() + "'" +
            ", restartReason='" + getRestartReason() + "'" +
            ", time='" + getTime() + "'" +
            ", uptime='" + getUptime() + "'" +
            ", hostName='" + getHostName() + "'" +
            ", ipAddress='" + getIpAddress() + "'" +
            ", lwt='" + getLwt() + "'" +
            "}";
    }
}
