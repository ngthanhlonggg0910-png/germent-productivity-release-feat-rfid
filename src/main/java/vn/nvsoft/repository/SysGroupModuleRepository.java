package vn.nvsoft.repository;
import vn.nvsoft.domain.SysGroupModule;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SysGroupModule entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SysGroupModuleRepository extends JpaRepository<SysGroupModule, Long> {

}
