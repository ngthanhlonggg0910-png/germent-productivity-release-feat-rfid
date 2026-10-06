package vn.nvsoft.service.zalo;

import javax.annotation.PreDestroy;
import javax.validation.constraints.NotNull;

import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import vn.nvsoft.service.CauHinhZaloService;
import vn.nvsoft.service.dto.CauHinhZaloDTO;
import vn.nvsoft.zalo.client.CSAClient;
import vn.nvsoft.zalo.client.CSAClientFactory;
import vn.nvsoft.zalo.client.api.exception.CSAInValidTokenException;
import vn.nvsoft.zalo.client.invoker.Result;
import vn.nvsoft.zalo.client.invoker.ZaloTokenInvoker;
import vn.nvsoft.zalo.client.model.OAuth2TokenDO;
import vn.nvsoft.zalo.client.model.RefreshTokenRequestDO;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class CSAClientManager {
    public static final String OAUTH2_REFRESH_TOKEN_GRANT_TYPE = "refresh_token";

    private static final Logger LOGGER = LoggerFactory.getLogger(CSAClientManager.class.getName());

    private CauHinhZaloService _cauHinhZaloService;

    private Map<String, CSAClient> _connections = Maps.newConcurrentMap();

    public CSAClientManager(CauHinhZaloService cauHinhZaloService) {
        _cauHinhZaloService = cauHinhZaloService;
    }

    /**
     * Factory method to create new csa client {@code CSAClient} on given host.
     * @param host : CSA Address server.
     * @return new instance {@link CSAClient}.
     */
    public CSAClient createCSAClient(@NotNull String host) {
        if (_connections.get(host) == null) {
            _connections.putIfAbsent(host, new CSAClientFactory(host, null));
        }
        return _connections.get(host);
    }

    /**
     * Factory method to create new csa client {@code CSAClient} on given host and required new access token if expiration.
     * The factory should be used to call request need to check access token before.
     * @return new instance {@link CSAClient}.
     */
    public CSAClient createCSAClient(CauHinhZaloDTO cauHinhZaloDTO, String host) {
        if (cauHinhZaloDTO == null) {
            throw new CSAInValidTokenException("User doesn't have a CSA Token. Please try to sign in first.");
        }
        if (cauHinhZaloDTO.isExpired()) {
            OAuth2TokenDO oAuth2TokenDO = refreshCSAToken(cauHinhZaloDTO);
            if (oAuth2TokenDO == null) {
                throw new CSAInValidTokenException("Unexpected error during refreshing a new access token.");
            }
        }

        return createCSAClient(host);
    }

    @PreDestroy
    public void close() {
        _connections.forEach((key, value) -> value.close());
    }


    /**
     * Get CSA access token on given user.
     * @return CSA token DTO.
     */
    public OAuth2TokenDO refreshCSAToken(CauHinhZaloDTO cauHinhZaloDTO) {
        Result<OAuth2TokenDO> oAuth2TokenDOResult = refreshNewCSAToken(cauHinhZaloDTO);
        LOGGER.info("Refresh token: {}", oAuth2TokenDOResult);
        if (oAuth2TokenDOResult.hasNoError() && oAuth2TokenDOResult.getEntity() != null && oAuth2TokenDOResult.getEntity().isValidTokens()) {
            updateCSAUserToken(oAuth2TokenDOResult.getEntity(), cauHinhZaloDTO);
            return oAuth2TokenDOResult.getEntity();
        }
        return null;
    }

    private Result<OAuth2TokenDO> refreshNewCSAToken(CauHinhZaloDTO cauHinhZaloDTO) {
        CSAClient csaClient = createCSAClient(ZaloTokenInvoker.BASE_URL);
        RefreshTokenRequestDO refreshTokenRequestDO = newRefreshTokenRequestDO(cauHinhZaloDTO);
        return csaClient.csaTokenService().accessToken(refreshTokenRequestDO);
    }

    private void updateCSAUserToken(OAuth2TokenDO oAuth2TokenDO, CauHinhZaloDTO cauHinhZaloDTO) {
        cauHinhZaloDTO.setAccessToken(oAuth2TokenDO.getAccess_token());
        cauHinhZaloDTO.setRefreshToken(oAuth2TokenDO.getRefresh_token());
        LocalDateTime now = LocalDateTime.now().plusSeconds(Long.parseLong(oAuth2TokenDO.getExpires_in()));
        cauHinhZaloDTO.setExpireTime(now);
        _cauHinhZaloService.save(cauHinhZaloDTO);
    }


    private RefreshTokenRequestDO newRefreshTokenRequestDO(CauHinhZaloDTO cauHinhZaloDTO) {
        if (cauHinhZaloDTO.getSecretKey() == null || cauHinhZaloDTO.getAppId() == null || cauHinhZaloDTO.getRefreshToken() == null) {
            throw new CSAInValidTokenException("Chua dang nhap Zalo!!!!");
        }
        RefreshTokenRequestDO refreshTokenRequestDO = new RefreshTokenRequestDO();
        refreshTokenRequestDO.setSecret_key(cauHinhZaloDTO.getSecretKey());
        refreshTokenRequestDO.setApp_id(cauHinhZaloDTO.getAppId().toString());
        refreshTokenRequestDO.setGrant_type(OAUTH2_REFRESH_TOKEN_GRANT_TYPE);
        refreshTokenRequestDO.setRefresh_token(cauHinhZaloDTO.getRefreshToken().trim());
        return refreshTokenRequestDO;
    }
}
