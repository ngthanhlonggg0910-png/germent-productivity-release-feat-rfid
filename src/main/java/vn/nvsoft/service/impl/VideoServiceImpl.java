package vn.nvsoft.service.impl;

import vn.nvsoft.service.VideoService;
import vn.nvsoft.domain.Video;
import vn.nvsoft.repository.VideoRepository;
import vn.nvsoft.service.dto.VideoDTO;
import vn.nvsoft.service.mapper.VideoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Video}.
 */
@Service
@Transactional
public class VideoServiceImpl implements VideoService {

    private final Logger log = LoggerFactory.getLogger(VideoServiceImpl.class);

    private final VideoRepository videoRepository;

    private final VideoMapper videoMapper;

    public VideoServiceImpl(VideoRepository videoRepository, VideoMapper videoMapper) {
        this.videoRepository = videoRepository;
        this.videoMapper = videoMapper;
    }

    /**
     * Save a video.
     *
     * @param videoDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public VideoDTO save(VideoDTO videoDTO) {
        log.debug("Request to save Video : {}", videoDTO);
        Video video = videoMapper.toEntity(videoDTO);
        video = videoRepository.save(video);
        return videoMapper.toDto(video);
    }

    /**
     * Get all the videos.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<VideoDTO> findAll() {
        log.debug("Request to get all Videos");
        return videoRepository.findAll().stream()
            .map(videoMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one video by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<VideoDTO> findOne(Long id) {
        log.debug("Request to get Video : {}", id);
        return videoRepository.findById(id)
            .map(videoMapper::toDto);
    }

    /**
     * Delete the video by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Video : {}", id);
        videoRepository.deleteById(id);
    }
}
