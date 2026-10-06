package vn.nvsoft.service.mqtt.factory;

import org.eclipse.paho.mqttv5.common.MqttMessage;

public class MqttMessageUtil {

    public static boolean isJsonObject(MqttMessage mqttMessage) {
        String payload = mqttMessage.toString().trim();
        return !payload.isEmpty() && payload.startsWith("{");
    }
}
