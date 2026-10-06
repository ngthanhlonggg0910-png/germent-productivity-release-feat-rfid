package vn.nvsoft.config;

import java.time.Duration;

import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;

import org.hibernate.cache.jcache.ConfigSettings;
import io.github.jhipster.config.JHipsterProperties;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
public class CacheConfiguration {

    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        JHipsterProperties.Cache.Ehcache ehcache = jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                .build());
    }

    @Bean
    public HibernatePropertiesCustomizer hibernatePropertiesCustomizer(javax.cache.CacheManager cacheManager) {
        return hibernateProperties -> hibernateProperties.put(ConfigSettings.CACHE_MANAGER, cacheManager);
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            createCache(cm, vn.nvsoft.repository.UserRepository.USERS_BY_LOGIN_CACHE);
            createCache(cm, vn.nvsoft.repository.UserRepository.USERS_BY_EMAIL_CACHE);
            createCache(cm, vn.nvsoft.domain.User.class.getName());
            createCache(cm, vn.nvsoft.domain.Authority.class.getName());
            createCache(cm, vn.nvsoft.domain.User.class.getName() + ".authorities");
            createCache(cm, vn.nvsoft.domain.SysRoleHasFunction.class.getName());
            createCache(cm, vn.nvsoft.domain.SysFunction.class.getName());
            createCache(cm, vn.nvsoft.domain.SysModule.class.getName());
            createCache(cm, vn.nvsoft.domain.SysPermission.class.getName());
            createCache(cm, vn.nvsoft.domain.SysGroupModule.class.getName());
            createCache(cm, vn.nvsoft.domain.KhacPhucLoi.class.getName());
            createCache(cm, vn.nvsoft.domain.ThuVienLoi.class.getName());
            createCache(cm, vn.nvsoft.domain.QuyCachNPL.class.getName());
            createCache(cm, vn.nvsoft.domain.GopYChatLuong.class.getName());
            createCache(cm, vn.nvsoft.domain.GYCLQuyCachNPL.class.getName());
            createCache(cm, vn.nvsoft.domain.GYCLKhacPhuc.class.getName());
            createCache(cm, vn.nvsoft.domain.ProKhachHang.class.getName());
            createCache(cm, vn.nvsoft.domain.ProSanPham.class.getName());
            createCache(cm, vn.nvsoft.domain.ProChungLoaiSanPham.class.getName());
            createCache(cm, vn.nvsoft.domain.ProTenMua.class.getName());
            createCache(cm, vn.nvsoft.domain.ProKeHoachSanXuat.class.getName());
            createCache(cm, vn.nvsoft.domain.ProLoaiChuyen.class.getName());
            createCache(cm, vn.nvsoft.domain.ProChuyen.class.getName());
            createCache(cm, vn.nvsoft.domain.ProNhaMay.class.getName());
            createCache(cm, vn.nvsoft.domain.ProCongTy.class.getName());
            createCache(cm, vn.nvsoft.domain.ProCheckPoint.class.getName());
            createCache(cm, vn.nvsoft.domain.ProTaiKhoanCheckPoint.class.getName());
            createCache(cm, vn.nvsoft.domain.ProCongDoan.class.getName());
            createCache(cm, vn.nvsoft.domain.ProNhomCongDoan.class.getName());
            createCache(cm, vn.nvsoft.domain.ProCumLoi.class.getName());
            createCache(cm, vn.nvsoft.domain.ProLoi.class.getName());
            createCache(cm, vn.nvsoft.domain.ProNSCL.class.getName());
            createCache(cm, vn.nvsoft.domain.ProNSCLChiTietLoi.class.getName());
            createCache(cm, vn.nvsoft.domain.ProGioLamViec.class.getName());
            createCache(cm, vn.nvsoft.domain.DungSai.class.getName());
            createCache(cm, vn.nvsoft.domain.DungSai.class.getName() + ".thongSoThucThis");
            createCache(cm, vn.nvsoft.domain.ThongSoDo.class.getName());
            createCache(cm, vn.nvsoft.domain.ThongSoDoSP.class.getName());
            createCache(cm, vn.nvsoft.domain.ThongSoDoSP.class.getName() + ".thongSoDoSPSizes");
            createCache(cm, vn.nvsoft.domain.ThongSoDoSPSize.class.getName());
            createCache(cm, vn.nvsoft.domain.ThongSoDoSPSize.class.getName() + ".thongSoThucThis");
            createCache(cm, vn.nvsoft.domain.ThongSoThucThi.class.getName());
            createCache(cm, vn.nvsoft.domain.SysFile.class.getName());
            createCache(cm, vn.nvsoft.domain.GroupTopic.class.getName());
            createCache(cm, vn.nvsoft.domain.NVButtonTrackingType.class.getName());
            createCache(cm, vn.nvsoft.domain.NVButton.class.getName());
            createCache(cm, vn.nvsoft.domain.Device.class.getName());
            createCache(cm, vn.nvsoft.domain.ButtonTracking.class.getName());
            createCache(cm, vn.nvsoft.domain.Tracking.class.getName());
            createCache(cm, vn.nvsoft.domain.MapControlButton.class.getName());
            createCache(cm, vn.nvsoft.domain.MapButtonAction.class.getName());
            createCache(cm, vn.nvsoft.domain.ProRefinal.class.getName());
            createCache(cm, vn.nvsoft.domain.ProRefinalChiTietLoi.class.getName());
            createCache(cm, vn.nvsoft.domain.SoLuongHangKiem.class.getName());
            createCache(cm, vn.nvsoft.domain.ProNsclLog.class.getName());
            createCache(cm, vn.nvsoft.domain.DoAm.class.getName());
            createCache(cm, vn.nvsoft.domain.Video.class.getName());
            createCache(cm, vn.nvsoft.domain.ProRefinalCC.class.getName());
            createCache(cm, vn.nvsoft.domain.ProRefinalCCChiTietLoi.class.getName());
            createCache(cm, vn.nvsoft.domain.ProFinal.class.getName());
            createCache(cm, vn.nvsoft.domain.ProFinalChiTietLoi.class.getName());
            createCache(cm, vn.nvsoft.domain.ProAql.class.getName());
            createCache(cm, vn.nvsoft.domain.DoKimMd.class.getName());
            createCache(cm, vn.nvsoft.domain.LibSoLuongPoKiem.class.getName());
            createCache(cm, vn.nvsoft.domain.ProIssueCumLoi.class.getName());
            createCache(cm, vn.nvsoft.domain.ProIssueLoi.class.getName());
            createCache(cm, vn.nvsoft.domain.ProIssue.class.getName());
            createCache(cm, vn.nvsoft.domain.CauHinhZalo.class.getName());
            // jhipster-needle-ehcache-add-entry
        };
    }

    private void createCache(javax.cache.CacheManager cm, String cacheName) {
        javax.cache.Cache<Object, Object> cache = cm.getCache(cacheName);
        if (cache != null) {
            cm.destroyCache(cacheName);
        }
        cm.createCache(cacheName, jcacheConfiguration);
    }

}
