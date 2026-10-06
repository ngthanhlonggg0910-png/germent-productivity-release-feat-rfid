package vn.nvsoft.zalo.client.invoker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.nvsoft.zalo.client.api.controller.ZaloMessageController;
import vn.nvsoft.zalo.client.configuration.CSAApi;
import vn.nvsoft.zalo.client.model.*;

public class ZaloMessageInvoker extends AbstractServiceInvoker {
    public static final String BASE_URL = "openapi.zalo.me";
    private static final Logger LOGGER = LoggerFactory.getLogger(ZaloMessageInvoker.class.getName());

    private ZaloMessageController _zaloMessageController;
    /**
     * Create invoker service on given client details.
     * @param csaApi : client details.
     */
    public ZaloMessageInvoker(CSAApi csaApi) {
        super(csaApi);
        _zaloMessageController = wrap(ZaloMessageController.class);
    }

    public Result<ZaloMessageResponseDO> sendMessage(String accessToken, ZaloMessage message) {
        Result<ZaloMessageResponseDO> result = callRequestForObject(
                () -> _zaloMessageController.sendMessage(accessToken, message), ZaloMessageResponseDO.class, null);
        if (result.hasError()) {
            LOGGER.error("Send message error: " + result);
        }
        return result;
    }

}
