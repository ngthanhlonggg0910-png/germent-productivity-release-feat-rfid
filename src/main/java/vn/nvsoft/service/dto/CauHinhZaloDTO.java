package vn.nvsoft.service.dto;
import vn.nvsoft.zalo.client.api.exception.CSAInValidTokenException;

import java.time.LocalDate;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * A DTO for the {@link vn.nvsoft.domain.CauHinhZalo} entity.
 */
public class CauHinhZaloDTO implements Serializable {

    private Long id;

    private String accessToken;

    private String refreshToken;

    private String zaloOA;

    private String secretKey;

    private Long appId;

    private LocalDateTime expireTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getZaloOA() {
        return zaloOA;
    }

    public void setZaloOA(String zaloOA) {
        this.zaloOA = zaloOA;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CauHinhZaloDTO cauHinhZaloDTO = (CauHinhZaloDTO) o;
        if (cauHinhZaloDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), cauHinhZaloDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CauHinhZaloDTO{" +
            "id=" + getId() +
            ", accessToken='" + getAccessToken() + "'" +
            ", refreshToken='" + getRefreshToken() + "'" +
            ", zaloOA='" + getZaloOA() + "'" +
            ", secretKey='" + getSecretKey() + "'" +
            ", appId=" + getAppId() +
            ", expireTime='" + getExpireTime() + "'" +
            "}";
    }

    public boolean isExpired() {
        if (this.expireTime == null) {
            throw new CSAInValidTokenException("Chua dang nhap Zalo!!!!!!!");
        }
        LocalDateTime current = LocalDateTime.now();
        return current.isAfter(this.expireTime);
    }
}
