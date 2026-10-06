package vn.nvsoft.mqtt.model;

/*
    	"Version": "8.4.0(tasmota)",
            "BuildDateTime": "2020-07-29T12:05:28",
            "Module or Template": "Sonoff Basic",
            "RestartReason": "External System",
            "Uptime": "0T00:10:02",
            "Hostname": "light_E07DEE-7662",
            "IPAddress": "192.168.3.5",
            "RSSI": "82",
            "Signal (dBm)": "-59",
            "WiFi LinkCount": 1,
            "WiFi Downtime": "0T00:01:44",
            "MqttCount": 1,
            "LoadAvg": 19
 */
public class SensorHassState {
    private String version;
    private String buildDateTime;
    private String moduleOrTemplate;
    private String restartReason;
    private String uptime;
    private String hostname;
    private String iPAddress;
    private String rSSI;
    private String wiFiLinkCount;
    private String wiFiDowntime;
    private Integer mqttCount;
    private Integer loadAvg;

    public SensorHassState() {
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getBuildDateTime() {
        return buildDateTime;
    }

    public void setBuildDateTime(String buildDateTime) {
        this.buildDateTime = buildDateTime;
    }

    public String getModuleOrTemplate() {
        return moduleOrTemplate;
    }

    public void setModuleOrTemplate(String moduleOrTemplate) {
        this.moduleOrTemplate = moduleOrTemplate;
    }

    public String getRestartReason() {
        return restartReason;
    }

    public void setRestartReason(String restartReason) {
        this.restartReason = restartReason;
    }

    public String getUptime() {
        return uptime;
    }

    public void setUptime(String uptime) {
        this.uptime = uptime;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getiPAddress() {
        return iPAddress;
    }

    public void setiPAddress(String iPAddress) {
        this.iPAddress = iPAddress;
    }

    public String getrSSI() {
        return rSSI;
    }

    public void setrSSI(String rSSI) {
        this.rSSI = rSSI;
    }

    public String getWiFiLinkCount() {
        return wiFiLinkCount;
    }

    public void setWiFiLinkCount(String wiFiLinkCount) {
        this.wiFiLinkCount = wiFiLinkCount;
    }

    public String getWiFiDowntime() {
        return wiFiDowntime;
    }

    public void setWiFiDowntime(String wiFiDowntime) {
        this.wiFiDowntime = wiFiDowntime;
    }

    public Integer getMqttCount() {
        return mqttCount;
    }

    public void setMqttCount(Integer mqttCount) {
        this.mqttCount = mqttCount;
    }

    public Integer getLoadAvg() {
        return loadAvg;
    }

    public void setLoadAvg(Integer loadAvg) {
        this.loadAvg = loadAvg;
    }

    @Override
    public String toString() {
        return "SensorHassState{" +
            "version='" + version + '\'' +
            ", buildDateTime='" + buildDateTime + '\'' +
            ", moduleOrTemplate='" + moduleOrTemplate + '\'' +
            ", restartReason='" + restartReason + '\'' +
            ", uptime='" + uptime + '\'' +
            ", hostname='" + hostname + '\'' +
            ", iPAddress='" + iPAddress + '\'' +
            ", rSSI='" + rSSI + '\'' +
            ", wiFiLinkCount='" + wiFiLinkCount + '\'' +
            ", wiFiDowntime='" + wiFiDowntime + '\'' +
            ", mqttCount=" + mqttCount +
            ", loadAvg=" + loadAvg +
            '}';
    }
}
