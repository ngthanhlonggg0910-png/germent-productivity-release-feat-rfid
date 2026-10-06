package vn.nvsoft.repository;

import org.springframework.data.repository.query.Param;
import vn.nvsoft.domain.DeviceToken;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the DeviceToken entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DeviceTokenRepository extends JpaRepository<DeviceToken, Long> {

    @Query("select d from DeviceToken d where d.user in (:userNames)")
    List<DeviceToken> findDeviceTokenByUserName(@Param("userNames") List<String> userNames);

    Optional<DeviceToken> findOneByUser(String user);
}
