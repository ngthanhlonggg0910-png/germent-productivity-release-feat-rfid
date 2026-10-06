package vn.nvsoft.mqtt;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("mqtt")
public class MqttOption {

    private Boolean debug = Boolean.FALSE;
    private Boolean quiet = Boolean.FALSE;
    private String host;
    private String clientId;
    private Integer keepAlive = 60; //seconds
    private String userName;
    private String password;
    private String willPayload;
    private String willTopic;
    private Integer willQos;
    private Boolean willRetain;
    private Boolean cleanSession;
    private Integer maxInflight;
    private Boolean automaticReconnect;
    private Integer qos;
    private Boolean verbose;
    private Boolean retain; //use in publish

    //pool
    private Integer corePoolSize = 10;
    private Integer maximumPoolSize = 20;
    private Integer keepAliveTime = 120;

    public Boolean getDebug() {
        return debug;
    }

    public void setDebug(Boolean debug) {
        this.debug = debug;
    }

    public Boolean getQuiet() {
        return quiet;
    }

    public void setQuiet(Boolean quiet) {
        this.quiet = quiet;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public Integer getKeepAlive() {
        return keepAlive;
    }

    public void setKeepAlive(Integer keepAlive) {
        this.keepAlive = keepAlive;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getWillPayload() {
        return willPayload;
    }

    public void setWillPayload(String willPayload) {
        this.willPayload = willPayload;
    }

    public String getWillTopic() {
        return willTopic;
    }

    public void setWillTopic(String willTopic) {
        this.willTopic = willTopic;
    }

    public Integer getWillQos() {
        return willQos;
    }

    public void setWillQos(Integer willQos) {
        this.willQos = willQos;
    }

    public Boolean getWillRetain() {
        return willRetain;
    }

    public void setWillRetain(Boolean willRetain) {
        this.willRetain = willRetain;
    }

    public Boolean getCleanSession() {
        return cleanSession;
    }

    public void setCleanSession(Boolean cleanSession) {
        this.cleanSession = cleanSession;
    }

    public Integer getMaxInflight() {
        return maxInflight;
    }

    public void setMaxInflight(Integer maxInflight) {
        this.maxInflight = maxInflight;
    }

    public Boolean getAutomaticReconnect() {
        return automaticReconnect;
    }

    public void setAutomaticReconnect(Boolean automaticReconnect) {
        this.automaticReconnect = automaticReconnect;
    }

    public Integer getCorePoolSize() {
        return corePoolSize;
    }

    public void setCorePoolSize(Integer corePoolSize) {
        this.corePoolSize = corePoolSize;
    }

    public Integer getMaximumPoolSize() {
        return maximumPoolSize;
    }

    public void setMaximumPoolSize(Integer maximumPoolSize) {
        this.maximumPoolSize = maximumPoolSize;
    }

    public Integer getKeepAliveTime() {
        return keepAliveTime;
    }

    public void setKeepAliveTime(Integer keepAliveTime) {
        this.keepAliveTime = keepAliveTime;
    }

    public Integer getQos() {
        return qos;
    }

    public void setQos(Integer qos) {
        this.qos = qos;
    }

    public Boolean getVerbose() {
        return verbose;
    }

    public void setVerbose(Boolean verbose) {
        this.verbose = verbose;
    }

    public Boolean getRetain() {
        return retain;
    }

    public void setRetain(Boolean retain) {
        this.retain = retain;
    }
}
