package vn.nvsoft.repository;
import vn.nvsoft.domain.ProTenMua;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ProTenMua entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProTenMuaRepository extends JpaRepository<ProTenMua, Long> {

}
