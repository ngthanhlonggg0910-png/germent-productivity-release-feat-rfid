package vn.nvsoft.repository;
import vn.nvsoft.domain.GopYChatLuong;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the GopYChatLuong entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GopYChatLuongRepository extends JpaRepository<GopYChatLuong, Long> {

}
