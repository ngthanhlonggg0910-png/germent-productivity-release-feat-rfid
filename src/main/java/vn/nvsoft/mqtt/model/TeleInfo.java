package vn.nvsoft.mqtt.model;

public class TeleInfo {

    private String webServerMode;
    private String hostname;
    private String iPAddress;
    private String receivedOnTopic;

    public TeleInfo() {
    }

    public String getWebServerMode() {
        return webServerMode;
    }

    public void setWebServerMode(String webServerMode) {
        this.webServerMode = webServerMode;
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

    public String getReceivedOnTopic() {
        return receivedOnTopic;
    }

    public void setReceivedOnTopic(String receivedOnTopic) {
        this.receivedOnTopic = receivedOnTopic;
    }
}
