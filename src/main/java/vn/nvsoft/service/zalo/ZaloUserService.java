package vn.nvsoft.service.zalo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.nvsoft.service.CauHinhZaloService;
import vn.nvsoft.service.dto.CauHinhZaloDTO;
import vn.nvsoft.zalo.client.CSAClient;
import vn.nvsoft.zalo.client.api.exception.CSAInValidTokenException;
import vn.nvsoft.zalo.client.invoker.ZaloTokenInvoker;
import vn.nvsoft.zalo.client.invoker.Result;
import vn.nvsoft.zalo.client.model.OAuth2TokenDO;
import vn.nvsoft.zalo.client.model.RefreshTokenRequestDO;

import java.time.LocalDateTime;


/**
 * Presents the business service to perform CSA Authentication operations.
 */
@Service
public class ZaloUserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ZaloUserService.class.getName());

    public static final String OAUTH2_ACCESS_TOKEN_GRANT_TYPE = "password";
    public static final String OAUTH2_ACCESS_SCOPE = "user:login";
    public static final String OAUTH2_REFRESH_TOKEN_GRANT_TYPE = "refresh_token";
    public static final String OAUTH2_INVALID_CREDENTIALS = "Invalid Credentials";

    @Autowired
    private CauHinhZaloService _cauHinhZaloService;
    @Autowired
    private CSAClientManager _csaClientManager;

//    public ZaloUserService(CauHinhZaloService cauHinhZaloService, CSAClientManager csaClientManager) {
//        _cauHinhZaloService = cauHinhZaloService;
//        _csaClientManager = csaClientManager;
//    }

//    public CSATokenVO signIn(User user, CSASignatureParam csaSignature, Organization currentOrg) {
//        CSAClient csaClient = _csaClientManager.createCSAClient(csaSignature.getServerAddress());
//        Result<OAuth2TokenDO> result = csaClient.csaTokenService().token(newOAuth2TokenRequestDO(csaSignature));
//        if ( result.hasEntityBody() && result.getEntity().isValidTokens()) {
//            return createNewUserCSAToken(user, csaSignature, result.getEntity(), currentOrg);
//        }
//        return createUnSignedTokenVO(result, csaSignature.getServerAddress());
//    }

//    public CSATokenVO signOut(User user, Organization currentOrg) {
//        UserCSAToken userCSAToken = user.getUserCSAToken();
//        if (userCSAToken != null) {
//            callSignOutCSA(userCSAToken);
//            user.setUserCSAToken(null);
//            _userService.createOrUpdate(user);
//            computeSignedCSAOfOrganization(currentOrg);
//        }
//        return createUnSignedTokenVO(CSAServerStatus.SIGNED_OUT, null);
//    }


}
