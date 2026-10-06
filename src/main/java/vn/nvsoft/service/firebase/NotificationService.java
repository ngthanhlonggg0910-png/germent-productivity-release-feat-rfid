package vn.nvsoft.service.firebase;

import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import vn.nvsoft.domain.NotificationMessage;
import vn.nvsoft.domain.chuyen.ThongTinToTruong;
import vn.nvsoft.repository.chuyen.EgmfRepository;
import vn.nvsoft.service.DeviceTokenService;
import vn.nvsoft.service.chuyen.EgmfService;
import vn.nvsoft.service.dto.WarningProNSCLChiTietLoiVO;
import vn.nvsoft.service.zalo.ZaloMessageService;
import vn.nvsoft.zalo.client.model.MessageDTO;
import vn.nvsoft.zalo.client.model.Recipient;
import vn.nvsoft.zalo.client.model.ZaloMessage;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class NotificationService {
    private final Logger log = LoggerFactory.getLogger(NotificationService.class);

    public static final String TITLE_KEY = "title";
    public static final String MSG_KEY = "message";
    private EgmfService m_egmfService;
    private DeviceTokenService m_deviceTokenService;
    private FireBaseAdminService m_fireBaseAdminService;
    private ZaloMessageService m_zaloMessageService;

    public NotificationService(EgmfService egmfService, DeviceTokenService deviceTokenService, FireBaseAdminService fireBaseAdminService,
                               ZaloMessageService zaloMessageService) {
        m_egmfService = egmfService;
        m_deviceTokenService = deviceTokenService;
        m_fireBaseAdminService = fireBaseAdminService;
        m_zaloMessageService = zaloMessageService;
    }

    /**
     * Send notify to device
     * @param warningProNSCLChiTietLoiVO
     */
    public void sendWarningMessageToLeader(WarningProNSCLChiTietLoiVO warningProNSCLChiTietLoiVO) {
        //get user leader
        log.debug("Lay thong tin to truong cua nscl id {}", warningProNSCLChiTietLoiVO.getProNSCLId());
        List<ThongTinToTruong> toTruongs = m_egmfService.getToTruongByNSCLId(warningProNSCLChiTietLoiVO.getProNSCLId());
        List<String> userNames = toTruongs.stream().map(ThongTinToTruong::getUsername).collect(Collectors.toList());
        if (userNames.isEmpty()) {
            log.warn("Khong tim thay user cua chuyen truong cho NSCL id {}", warningProNSCLChiTietLoiVO.getProNSCLId());
        } else {
            log.debug("Lay device token cua chuyen truong: {}", userNames);
            //get device token
            List<String> deviceTokens = m_deviceTokenService.findDeviceTokenByUserName(userNames);
            m_fireBaseAdminService.sendNotificationToDevices(buildWarningMessage(warningProNSCLChiTietLoiVO), deviceTokens);

            List<String> zaloIds = toTruongs.stream().map(ThongTinToTruong::getZaloid).filter(Objects::nonNull).collect(Collectors.toList());
            m_zaloMessageService.sendZaloMessage(buildWarningZaloMessage(warningProNSCLChiTietLoiVO, zaloIds));
        }
    }

    public void dummySendWarningMessageToLeader(NotificationMessage notificationMessage) {
        //get device token
        List<String> deviceTokens = m_deviceTokenService.findDeviceTokenByUserName(notificationMessage.getUsers());
        Map<String, String> results = new HashMap<>();
        results.put(TITLE_KEY, notificationMessage.getTitle());
        results.put(MSG_KEY, notificationMessage.getMessage());
        m_fireBaseAdminService.sendNotificationToDevices(results, deviceTokens);
    }

    public void dummySendWarningZaloMessageToLeader(NotificationMessage notificationMessage) {
        m_zaloMessageService.sendZaloMessage(buildWarningZaloMessage(notificationMessage, notificationMessage.getUsers().get(0)));
    }

    private Map<String, String> buildWarningMessage(WarningProNSCLChiTietLoiVO warningProNSCLChiTietLoiVO) {
        Map<String, String> results = new HashMap<>();
        results.put(TITLE_KEY, "Cảnh báo về lỗi!");
        results.put(MSG_KEY, "Lỗi " + warningProNSCLChiTietLoiVO.getCodeName() + " lặp lại " + warningProNSCLChiTietLoiVO.getProNSCLChiTietLoiIds().size() + " lần.");
        return results;
    }

    private ZaloMessage buildWarningZaloMessage(WarningProNSCLChiTietLoiVO warningProNSCLChiTietLoiVO, String userZaloId) {
        ZaloMessage zaloMessage = new ZaloMessage();
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setText("Cảnh báo về lỗi! \n " + "Lỗi " + warningProNSCLChiTietLoiVO.getCodeName() + " lặp lại " + warningProNSCLChiTietLoiVO.getProNSCLChiTietLoiIds().size() + " lần.");
        zaloMessage.setMessage(messageDTO);
        Recipient recipient = new Recipient();
        recipient.setUser_id(userZaloId);
        zaloMessage.setRecipient(recipient);
        return zaloMessage;
    }

    private List<ZaloMessage> buildWarningZaloMessage(WarningProNSCLChiTietLoiVO warningProNSCLChiTietLoiVO, List<String> userZaloId) {
        List<ZaloMessage> results = new ArrayList<>();
        for (String s : userZaloId) {
            results.add(buildWarningZaloMessage(warningProNSCLChiTietLoiVO, s));
        }
        return results;
    }

    private ZaloMessage buildWarningZaloMessage(NotificationMessage notificationMessage, String userZaloId) {
        ZaloMessage zaloMessage = new ZaloMessage();
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setText(notificationMessage.getTitle() + "\n " + notificationMessage.getMessage());
        zaloMessage.setMessage(messageDTO);
        Recipient recipient = new Recipient();
        recipient.setUser_id(userZaloId);
        zaloMessage.setRecipient(recipient);
        return zaloMessage;
    }
}
