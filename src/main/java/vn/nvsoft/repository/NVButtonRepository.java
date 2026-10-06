package vn.nvsoft.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.nvsoft.domain.NVButton;


/**
 * Spring Data  repository for the NVButton entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NVButtonRepository extends JpaRepository<NVButton, Long> {

    List<NVButton> findAllByCmndTopic(String cmndTopic);

    Optional<NVButton> findOneByStatTopic(String topic);

    List<NVButton> findByAvtyTopic(String avtyTopic);
}
