package vn.nvsoft.zalo.client.invoker;

import vn.nvsoft.zalo.client.model.LogoutDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import vn.nvsoft.zalo.client.api.controller.ZaloLoginController;
import vn.nvsoft.zalo.client.configuration.CSAApi;
import vn.nvsoft.zalo.client.model.LoginDO;

/**
 * Presents the business login to handle user operations.
 */
public class ZaloLoginInvoker extends AbstractServiceInvoker {

    private static final Logger LOGGER = LoggerFactory.getLogger(ZaloLoginInvoker.class.getName());

    private ZaloLoginController _csaLoginController;

    /**
     * Create a CSA user service on given CSA client details and wrapper controller service.
     * @param csaApi CSA Client Details.
     */
    public ZaloLoginInvoker(CSAApi csaApi) {
        super(csaApi);
        _csaLoginController = wrap(ZaloLoginController.class);
    }

    @Deprecated
    public Result<LoginDO> signIn(String username, String password) {
        Result<LoginDO> result = callRequestForObject(
                () -> _csaLoginController.login(username, password, true, true),
                LoginDO.class, null);
        if (result.hasError()) {
            LOGGER.error("Sign In failure " + result);
        }
        return result;
    }

	@Deprecated
    public Result<LogoutDO> logout(String email, String userToken) {
        Result<LogoutDO> result = callRequestForObject(
                () -> _csaLoginController.logout(email, userToken, true, true),
                LogoutDO.class, null);
        if (result.hasError()) {
            LOGGER.error("Sign Out failure " + result);
        }
        return result;
    }

}
