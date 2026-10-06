package vn.nvsoft.repository;
import vn.nvsoft.domain.GroupTopic;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the GroupTopic entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GroupTopicRepository extends JpaRepository<GroupTopic, Long> {

}
