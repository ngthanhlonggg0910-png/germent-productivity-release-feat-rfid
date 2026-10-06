package vn.nvsoft.web.rest.notification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.nvsoft.domain.NotificationMessage;
import vn.nvsoft.domain.chuyen.NangSuatChuyen;
import vn.nvsoft.domain.chuyen.ThongTinChuyen;
import vn.nvsoft.service.chuyen.EgmfService;
import vn.nvsoft.service.dto.WarningProNSCLChiTietLoiVO;
import vn.nvsoft.service.firebase.NotificationService;

import javax.validation.Valid;
import java.net.URISyntaxException;

/**
 * REST controller for managing {@link vn.nvsoft.domain.DeviceToken}.
 */
@RestController
@RequestMapping("/api/notification")
public class NotificationResource {

    private final Logger log = LoggerFactory.getLogger(NotificationResource.class);

    private static final String ENTITY_NAME = "chuyen";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final NotificationService m_notificationService;

    public NotificationResource(NotificationService notificationService) {
        m_notificationService = notificationService;
    }

    /**
     * {@code POST  /dummy} : send test notification to user.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the void in body.
     */
    @PostMapping("/dummy")
    public void sendDummyNotification(@Valid @RequestBody NotificationMessage notificationMessage) {
        log.debug("REST request to send dummy notification to {}", notificationMessage);
        m_notificationService.dummySendWarningMessageToLeader(notificationMessage);
    }

    /**
     * {@code POST  /dummy-zalo} : send test notification to user.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the void in body.
     */
    @PostMapping("/dummy-zalo")
    public void sendDummyNotificationToZalo(@Valid @RequestBody NotificationMessage notificationMessage) {
        log.debug("REST request to send dummy notification to zalo {}", notificationMessage);
        m_notificationService.dummySendWarningZaloMessageToLeader(notificationMessage);
    }

    /**
     * {@code PUT  /pro-nscl-chi-tiet-lois/warning} : Updates warning of an existing proNSCLChiTietLoi.
     *
     * @param proNSCLChiTietLoiVO the proNSCLChiTietLoiVO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated proNSCLChiTietLoiDTO,
     * or with status {@code 400 (Bad Request)} if the proNSCLChiTietLoiDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the proNSCLChiTietLoiDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/dummy/warning")
    public ResponseEntity<Void> dummyWarningOfProNSCLChiTietLoi(@Valid @RequestBody WarningProNSCLChiTietLoiVO proNSCLChiTietLoiVO) throws URISyntaxException {
        log.debug("REST request to update warning of ProNSCLChiTietLoi : {}", proNSCLChiTietLoiVO);
        m_notificationService.sendWarningMessageToLeader(proNSCLChiTietLoiVO);
        return ResponseEntity.ok().build();
    }

}
