package vn.nvsoft.service.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class FireBaseAdminService {
    private final Logger log = LoggerFactory.getLogger(FireBaseAdminService.class);

    private String SERVICE_ACCOUNT_KEY_PATH="nv-soft-firebase-adminsdk-4jcj8-ee488d809c.json";
    private String DATABASE_URL = "https://nv-soft.firebaseio.com";
    @PostConstruct
    public void init() {
        try {
            InputStream serviceAccount = getClass().getClassLoader().getResourceAsStream(SERVICE_ACCOUNT_KEY_PATH);
            FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl(DATABASE_URL)
                .build();
            FirebaseApp.initializeApp(options);
        } catch (IOException e) {
            log.error("Can't init Firebase", e);
        }
    }

    /**
     * Send message to list device base on device tocken
     * The messages contain key and value
     * @param messages
     * @param deviceTokens
     */
    public void sendNotificationToDevices(Map<String, String> messages, List<String> deviceTokens) {
        if (deviceTokens != null && !deviceTokens.isEmpty() && !messages.isEmpty()) {
            log.info("Send notification {} to device tocken {}", messages, deviceTokens);
            AndroidNotification androidNotification = AndroidNotification.builder()
                .setSound("default")
                .build();
            AndroidConfig androidConfig = AndroidConfig.builder()
                .setNotification(androidNotification)
                .build();
            MulticastMessage multicastMessage = MulticastMessage.builder()
                .setNotification(buildNotification(messages))
                .setAndroidConfig(androidConfig)
                .addAllTokens(deviceTokens).build();

            try {
                BatchResponse response = FirebaseMessaging.getInstance().sendMulticast(multicastMessage);
                if (response.getFailureCount() > 0) {
                    List<SendResponse> responses = response.getResponses();
                    List<String> failedTokens = new ArrayList<>();
                    for (int i = 0; i < responses.size(); i++) {
                        if (!responses.get(i).isSuccessful()) {
                            // The order of responses corresponds to the order of the registration tokens.
                            failedTokens.add(deviceTokens.get(i));
                        }
                    }

                    log.warn("List of tokens that caused failures: " + failedTokens);
                }
            } catch (FirebaseMessagingException e) {
                log.error("Can't send notification: ", e);
            }

        } else {
            log.debug("Does not send notify to empty device tocken list");
        }
    }

    private Notification buildNotification(Map<String, String> messages) {
        Notification notification = Notification.builder()
            .setTitle(messages.getOrDefault(NotificationService.TITLE_KEY, "Default Title"))
            .setBody(messages.getOrDefault(NotificationService.MSG_KEY, "Default message"))
            .build();
        return notification;
    }

    /**
     * Send message to list device base on device token
     * Firebase notification type Data
     * The messages contain key and value
     * @param messages
     * @param deviceTokens
     */
    public void sendNotificationMessageTypeDataToDevices(Map<String, String> messages, List<String> deviceTokens) {
        if (deviceTokens != null && !deviceTokens.isEmpty() && !messages.isEmpty()) {
            log.info("Send notification {} to device tocken {}", messages, deviceTokens);
            MulticastMessage multicastMessage = MulticastMessage.builder()
                .putAllData(messages).addAllTokens(deviceTokens).build();
            try {
                BatchResponse response = FirebaseMessaging.getInstance().sendMulticast(multicastMessage);
                if (response.getFailureCount() > 0) {
                    List<SendResponse> responses = response.getResponses();
                    List<String> failedTokens = new ArrayList<>();
                    for (int i = 0; i < responses.size(); i++) {
                        if (!responses.get(i).isSuccessful()) {
                            // The order of responses corresponds to the order of the registration tokens.
                            failedTokens.add(deviceTokens.get(i));
                        }
                    }

                    log.warn("List of tokens that caused failures: " + failedTokens);
                }
            } catch (FirebaseMessagingException e) {
                log.error("Can't send notification: ", e);
            }

        } else {
            log.debug("Does not send notify to empty device tocken list");
        }
    }
}
