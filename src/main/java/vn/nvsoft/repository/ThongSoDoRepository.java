package vn.nvsoft.repository;
import vn.nvsoft.domain.ThongSoDo;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ThongSoDo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ThongSoDoRepository extends JpaRepository<ThongSoDo, Long> {

}
