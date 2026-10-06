package vn.nvsoft.zalo.client.invoker;

import vn.nvsoft.zalo.client.api.controller.ZaloTokenController;
import vn.nvsoft.zalo.client.configuration.CSAApi;
import vn.nvsoft.zalo.client.model.OAuth2TokenDO;
import vn.nvsoft.zalo.client.model.RefreshTokenRequestDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;

public class ZaloTokenInvoker extends AbstractServiceInvoker {
    public static final String BASE_URL = "oauth.zaloapp.com";
    private static final Logger LOGGER = LoggerFactory.getLogger(ZaloTokenInvoker.class.getName());

    private ZaloTokenController _csaTokenController;
    /**
     * Create invoker service on given client details.
     * @param csaApi : client details.
     */
    public ZaloTokenInvoker(CSAApi csaApi) {
        super(csaApi);
        _csaTokenController = wrap(ZaloTokenController.class);
    }

    /**
     * Request an access token to CSA via OAuth2 call.
     *
     * @param oAuth2TokenRequestDO the the OAuth2 token request DO, which contains client_id
     * client_secret, username, password, grant_type and scope.
     * Grant type must be set to "password".
     * @return OAuth2TokenDO, which contains access_token and refresh_token.
     */
    public Result<OAuth2TokenDO> accessToken(RefreshTokenRequestDO oAuth2TokenRequestDO) {
        Form form = new Form();
        form.param("refresh_token", oAuth2TokenRequestDO.getRefresh_token())
            .param("app_id", oAuth2TokenRequestDO.getApp_id())
            .param("grant_type", oAuth2TokenRequestDO.getGrant_type());
        Entity<Form> entity = Entity.form(form);
//        Result<OAuth2TokenDO> result = callRequestForObject(
//                () -> _csaTokenController.accessToken(oAuth2TokenRequestDO.getSecret_key(), entity), OAuth2TokenDO.class, null);
        Result<OAuth2TokenDO> result = callRequestForObject(
            () -> _csaTokenController.accessToken(oAuth2TokenRequestDO.getSecret_key(), oAuth2TokenRequestDO.getRefresh_token(), oAuth2TokenRequestDO.getApp_id(), oAuth2TokenRequestDO.getGrant_type()), OAuth2TokenDO.class, null);
        if (result.hasError()) {
            LOGGER.error("Calling Access Token CSA failure : " + result);
        }
        return result;
    }

}
