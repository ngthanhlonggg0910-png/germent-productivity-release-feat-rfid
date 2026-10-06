package vn.nvsoft.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import vn.nvsoft.service.GroupTopicService;
import vn.nvsoft.domain.GroupTopic;
import vn.nvsoft.repository.GroupTopicRepository;
import vn.nvsoft.service.dto.GroupTopicDTO;
import vn.nvsoft.service.mapper.GroupTopicMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link GroupTopic}.
 */
@Service
@Transactional
public class GroupTopicServiceImpl implements GroupTopicService {

    private final Logger log = LoggerFactory.getLogger(GroupTopicServiceImpl.class);

    private final GroupTopicRepository groupTopicRepository;

    private final GroupTopicMapper groupTopicMapper;

    public GroupTopicServiceImpl(GroupTopicRepository groupTopicRepository, GroupTopicMapper groupTopicMapper) {
        this.groupTopicRepository = groupTopicRepository;
        this.groupTopicMapper = groupTopicMapper;
    }

    /**
     * Save a groupTopic.
     *
     * @param groupTopicDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public GroupTopicDTO save(GroupTopicDTO groupTopicDTO) {
        log.debug("Request to save GroupTopic : {}", groupTopicDTO);
        GroupTopic groupTopic = groupTopicMapper.toEntity(groupTopicDTO);
        groupTopic = groupTopicRepository.save(groupTopic);
        return groupTopicMapper.toDto(groupTopic);
    }

    /**
     * Get all the groupTopics.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<GroupTopicDTO> findAll(Pageable pageable) {
        log.debug("Request to get all GroupTopics");
        return groupTopicRepository.findAll(pageable)
            .map(groupTopicMapper::toDto);
    }

    /**
     * Get all the groupTopics.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<GroupTopicDTO> findAll() {
        log.debug("Request to get all GroupTopics");
        return groupTopicRepository.findAll().stream().map(groupTopicMapper::toDto).collect(Collectors.toList());
    }


    /**
     * Get one groupTopic by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<GroupTopicDTO> findOne(Long id) {
        log.debug("Request to get GroupTopic : {}", id);
        return groupTopicRepository.findById(id)
            .map(groupTopicMapper::toDto);
    }

    /**
     * Delete the groupTopic by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete GroupTopic : {}", id);
        groupTopicRepository.deleteById(id);
    }
}
