package vn.nvsoft.repository;
import vn.nvsoft.domain.SysFunction;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SysFunction entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SysFunctionRepository extends JpaRepository<SysFunction, Long> {

}
