package vn.nvsoft.service.impl;

import vn.nvsoft.service.GopYChatLuongService;
import vn.nvsoft.domain.GopYChatLuong;
import vn.nvsoft.repository.GopYChatLuongRepository;
import vn.nvsoft.service.dto.GopYChatLuongDTO;
import vn.nvsoft.service.mapper.GopYChatLuongMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link GopYChatLuong}.
 */
@Service
@Transactional
public class GopYChatLuongServiceImpl implements GopYChatLuongService {

    private final Logger log = LoggerFactory.getLogger(GopYChatLuongServiceImpl.class);

    private final GopYChatLuongRepository gopYChatLuongRepository;

    private final GopYChatLuongMapper gopYChatLuongMapper;

    public GopYChatLuongServiceImpl(GopYChatLuongRepository gopYChatLuongRepository, GopYChatLuongMapper gopYChatLuongMapper) {
        this.gopYChatLuongRepository = gopYChatLuongRepository;
        this.gopYChatLuongMapper = gopYChatLuongMapper;
    }

    /**
     * Save a gopYChatLuong.
     *
     * @param gopYChatLuongDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public GopYChatLuongDTO save(GopYChatLuongDTO gopYChatLuongDTO) {
        log.debug("Request to save GopYChatLuong : {}", gopYChatLuongDTO);
        GopYChatLuong gopYChatLuong = gopYChatLuongMapper.toEntity(gopYChatLuongDTO);
        gopYChatLuong = gopYChatLuongRepository.save(gopYChatLuong);
        return gopYChatLuongMapper.toDto(gopYChatLuong);
    }

    /**
     * Get all the gopYChatLuongs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<GopYChatLuongDTO> findAll(Pageable pageable) {
        log.debug("Request to get all GopYChatLuongs");
        return gopYChatLuongRepository.findAll(pageable)
            .map(gopYChatLuongMapper::toDto);
    }


    /**
     * Get one gopYChatLuong by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<GopYChatLuongDTO> findOne(Long id) {
        log.debug("Request to get GopYChatLuong : {}", id);
        return gopYChatLuongRepository.findById(id)
            .map(gopYChatLuongMapper::toDto);
    }

    /**
     * Delete the gopYChatLuong by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete GopYChatLuong : {}", id);
        gopYChatLuongRepository.deleteById(id);
    }
}
