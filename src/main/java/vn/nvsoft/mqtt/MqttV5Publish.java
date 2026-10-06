package vn.nvsoft.mqtt;

public class MqttV5Publish {
    private byte[] payload;
    private int qos = 0;
    private String topic;
    private boolean retain = false;

    public MqttV5Publish(byte[] payload, int qos, String topic) {
        this.payload = payload;
        this.qos = qos;
        this.topic = topic;
    }

    public MqttV5Publish(String payload, int qos, String topic) {
        this.payload = payload.getBytes();
        this.qos = qos;
        this.topic = topic;
    }

    public MqttV5Publish(MqttOption mqttOption, String topic, String message) {
        this.payload = message.getBytes();
        if (mqttOption.getQos() != null) {
            this.qos = mqttOption.getQos();
        }
        if (mqttOption.getRetain() != null) {
            this.retain = mqttOption.getRetain();
        }
        this.topic = topic;
    }

    public byte[] getPayload() {
        return payload;
    }

    public void setPayload(byte[] payload) {
        this.payload = payload;
    }

    public int getQos() {
        return qos;
    }

    public void setQos(int qos) {
        this.qos = qos;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public boolean isRetain() {
        return retain;
    }

    public void setRetain(boolean retain) {
        this.retain = retain;
    }
}
