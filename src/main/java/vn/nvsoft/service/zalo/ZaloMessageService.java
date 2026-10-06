package vn.nvsoft.service.zalo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import vn.nvsoft.service.CauHinhZaloService;
import vn.nvsoft.service.dto.CauHinhZaloDTO;
import vn.nvsoft.zalo.client.CSAClient;
import vn.nvsoft.zalo.client.api.exception.CSAInValidTokenException;
import vn.nvsoft.zalo.client.invoker.ZaloMessageInvoker;
import vn.nvsoft.zalo.client.model.ZaloMessage;
import vn.nvsoft.zalo.client.model.ZaloMessageResponseDO;

import java.util.*;

/**
 * Presents the csa user resource summary management {@code CSAUserResourceManager} to handle
 * for collecting csa user summary and support cache for better performance.
 */
@Service
public class ZaloMessageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ZaloMessageService.class.getName());

    private final CSAClientManager _csaClientManager;
    private final ZaloUserService _csaUserAuthentication;
    private final CauHinhZaloService _cauHinhZaloService;

    public ZaloMessageService(CSAClientManager csaClientManager, ZaloUserService csaUserAuthentication, CauHinhZaloService cauHinhZaloService) {
        _csaClientManager = csaClientManager;
        _csaUserAuthentication = csaUserAuthentication;
        _cauHinhZaloService = cauHinhZaloService;
    }

    public ZaloMessageResponseDO sendZaloMessage(ZaloMessage message)  {
        Optional<CauHinhZaloDTO> cauHinhZaloDTO = _cauHinhZaloService.findAll().stream().findFirst();
        if (!cauHinhZaloDTO.isPresent()) {
            LOGGER.warn("Khong tim thay cau hinh Zalo!!!!!");
            return null;
        }
        return getOrCreateCSAClient(cauHinhZaloDTO.get(), ZaloMessageInvoker.BASE_URL)
                .zaloMessageService()
                .sendMessage(cauHinhZaloDTO.get().getAccessToken(), message)
                .getEntity();
    }

    public List<ZaloMessageResponseDO> sendZaloMessage(List<ZaloMessage> messages)  {
        Optional<CauHinhZaloDTO> cauHinhZaloDTO = _cauHinhZaloService.findAll().stream().findFirst();
        if (!cauHinhZaloDTO.isPresent()) {
            LOGGER.warn("Khong tim thay cau hinh Zalo!!!!!");
            return null;
        }
        List<ZaloMessageResponseDO> results = new ArrayList<>();
        ZaloMessageInvoker zaloService = getOrCreateCSAClient(cauHinhZaloDTO.get(), ZaloMessageInvoker.BASE_URL).zaloMessageService();
        for (ZaloMessage message : messages) {
            results.add(zaloService.sendMessage(cauHinhZaloDTO.get().getAccessToken(), message).getEntity());
        }
        return results;
    }

    public CSAClient getOrCreateCSAClient(CauHinhZaloDTO cauHinhZaloDTO, String host) {
        try {
            return _csaClientManager.createCSAClient(cauHinhZaloDTO, host);
        } catch (Exception ex) {
            LOGGER.error("getOrCreateCSAClient: ", ex);
            throw new CSAInValidTokenException("Unexpected error during refreshing a new access token.");
        }
    }

}
