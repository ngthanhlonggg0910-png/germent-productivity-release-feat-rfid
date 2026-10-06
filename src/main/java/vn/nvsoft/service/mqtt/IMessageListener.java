package vn.nvsoft.service.mqtt;


import org.eclipse.paho.mqttv5.common.MqttMessage;

public interface IMessageListener {

    boolean isHandleMessage(String topic, MqttMessage mqttMessage);

    void handleMessage(String topic, MqttMessage mqttMessage);

    void connectionClosed();
}
