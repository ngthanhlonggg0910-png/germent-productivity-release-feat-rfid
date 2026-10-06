package vn.nvsoft.service;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import vn.nvsoft.service.dto.GroupTopicDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link vn.nvsoft.domain.GroupTopic}.
 */
public interface GroupTopicService {

    /**
     * Save a groupTopic.
     *
     * @param groupTopicDTO the entity to save.
     * @return the persisted entity.
     */
    GroupTopicDTO save(GroupTopicDTO groupTopicDTO);

    /**
     * Get all the groupTopics.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<GroupTopicDTO> findAll(Pageable pageable);


    @Transactional(readOnly = true)
    List<GroupTopicDTO> findAll();

    /**
     * Get the "id" groupTopic.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<GroupTopicDTO> findOne(Long id);

    /**
     * Delete the "id" groupTopic.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
