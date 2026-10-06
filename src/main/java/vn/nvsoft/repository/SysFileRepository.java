package vn.nvsoft.repository;
import vn.nvsoft.domain.SysFile;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SysFile entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SysFileRepository extends JpaRepository<SysFile, Long> {

}
