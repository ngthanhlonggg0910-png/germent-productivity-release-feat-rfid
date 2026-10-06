package vn.nvsoft.repository;
import vn.nvsoft.domain.SysPermission;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SysPermission entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SysPermissionRepository extends JpaRepository<SysPermission, Long> {

}
