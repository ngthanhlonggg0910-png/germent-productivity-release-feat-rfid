package vn.nvsoft.mqtt;

public class MqttV5Subscribe {
    private String topic;
    private int qos = 0;
    private boolean verbose = false;

    public MqttV5Subscribe(String topic, int qos) {
        this.topic = topic;
        this.qos = qos;
    }

    public MqttV5Subscribe(String topic, MqttOption mqttOption) {
        this.topic = topic;
        if (mqttOption.getQos() != null) {
            this.qos = mqttOption.getQos();
        }
        if (mqttOption.getVerbose() != null) {
            this.verbose = mqttOption.getVerbose();
        }
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public int getQos() {
        return qos;
    }

    public void setQos(int qos) {
        this.qos = qos;
    }

    public boolean isVerbose() {
        return verbose;
    }

    public void setVerbose(boolean verbose) {
        this.verbose = verbose;
    }
}
