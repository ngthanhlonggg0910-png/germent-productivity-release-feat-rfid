package vn.nvsoft.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

import vn.nvsoft.domain.enumeration.LWT;

/**
 * A Device.
 */
@Entity
@Table(name = "device")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Device implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "dev_ids")
    private String devIds;

    
    @Column(name = "state_topic", unique = true)
    private String stateTopic;

    
    @Column(name = "lwt_topic", unique = true)
    private String lwtTopic;

    @Column(name = "sw")
    private String sw;

    @Column(name = "module")
    private String module;

    @Column(name = "restart_reason")
    private String restartReason;

    @Column(name = "time")
    private Instant time;

    @Column(name = "uptime")
    private String uptime;

    @Column(name = "host_name")
    private String hostName;

    @Column(name = "ip_address")
    private String ipAddress;

    @Enumerated(EnumType.STRING)
    @Column(name = "lwt")
    private LWT lwt;

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

    public Device name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDevIds() {
        return devIds;
    }

    public Device devIds(String devIds) {
        this.devIds = devIds;
        return this;
    }

    public void setDevIds(String devIds) {
        this.devIds = devIds;
    }

    public String getStateTopic() {
        return stateTopic;
    }

    public Device stateTopic(String stateTopic) {
        this.stateTopic = stateTopic;
        return this;
    }

    public void setStateTopic(String stateTopic) {
        this.stateTopic = stateTopic;
    }

    public String getLwtTopic() {
        return lwtTopic;
    }

    public Device lwtTopic(String lwtTopic) {
        this.lwtTopic = lwtTopic;
        return this;
    }

    public void setLwtTopic(String lwtTopic) {
        this.lwtTopic = lwtTopic;
    }

    public String getSw() {
        return sw;
    }

    public Device sw(String sw) {
        this.sw = sw;
        return this;
    }

    public void setSw(String sw) {
        this.sw = sw;
    }

    public String getModule() {
        return module;
    }

    public Device module(String module) {
        this.module = module;
        return this;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getRestartReason() {
        return restartReason;
    }

    public Device restartReason(String restartReason) {
        this.restartReason = restartReason;
        return this;
    }

    public void setRestartReason(String restartReason) {
        this.restartReason = restartReason;
    }

    public Instant getTime() {
        return time;
    }

    public Device time(Instant time) {
        this.time = time;
        return this;
    }

    public void setTime(Instant time) {
        this.time = time;
    }

    public String getUptime() {
        return uptime;
    }

    public Device uptime(String uptime) {
        this.uptime = uptime;
        return this;
    }

    public void setUptime(String uptime) {
        this.uptime = uptime;
    }

    public String getHostName() {
        return hostName;
    }

    public Device hostName(String hostName) {
        this.hostName = hostName;
        return this;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public Device ipAddress(String ipAddress) {
        this.ipAddress = ipAddress;
        return this;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public LWT getLwt() {
        return lwt;
    }

    public Device lwt(LWT lwt) {
        this.lwt = lwt;
        return this;
    }

    public void setLwt(LWT lwt) {
        this.lwt = lwt;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Device)) {
            return false;
        }
        return id != null && id.equals(((Device) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Device{" +
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
