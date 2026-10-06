package vn.nvsoft.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * A CauHinhZalo.
 */
@Entity
@Table(name = "ch_cau_hinh_zalo")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CauHinhZalo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "access_token")
    private String accessToken;

    @Column(name = "refresh_token")
    private String refreshToken;

    @Column(name = "zalo_oa")
    private String zaloOA;

    @Column(name = "secret_key")
    private String secretKey;

    @Column(name = "app_id")
    private Long appId;

    @Column(name = "expire_time")
    private LocalDateTime expireTime;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public CauHinhZalo accessToken(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public CauHinhZalo refreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
        return this;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getZaloOA() {
        return zaloOA;
    }

    public CauHinhZalo zaloOA(String zaloOA) {
        this.zaloOA = zaloOA;
        return this;
    }

    public void setZaloOA(String zaloOA) {
        this.zaloOA = zaloOA;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public CauHinhZalo secretKey(String secretKey) {
        this.secretKey = secretKey;
        return this;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public Long getAppId() {
        return appId;
    }

    public CauHinhZalo appId(Long appId) {
        this.appId = appId;
        return this;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public CauHinhZalo expireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
        return this;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CauHinhZalo)) {
            return false;
        }
        return id != null && id.equals(((CauHinhZalo) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "CauHinhZalo{" +
            "id=" + getId() +
            ", accessToken='" + getAccessToken() + "'" +
            ", refreshToken='" + getRefreshToken() + "'" +
            ", zaloOA='" + getZaloOA() + "'" +
            ", secretKey='" + getSecretKey() + "'" +
            ", appId=" + getAppId() +
            ", expireTime='" + getExpireTime() + "'" +
            "}";
    }
}
