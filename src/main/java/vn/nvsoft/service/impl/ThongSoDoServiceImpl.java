package vn.nvsoft.service.impl;

import vn.nvsoft.service.ThongSoDoService;
import vn.nvsoft.domain.ThongSoDo;
import vn.nvsoft.repository.ThongSoDoRepository;
import vn.nvsoft.service.dto.ThongSoDoDTO;
import vn.nvsoft.service.mapper.ThongSoDoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ThongSoDo}.
 */
@Service
@Transactional
public class ThongSoDoServiceImpl implements ThongSoDoService {

    private final Logger log = LoggerFactory.getLogger(ThongSoDoServiceImpl.class);

    private final ThongSoDoRepository thongSoDoRepository;

    private final ThongSoDoMapper thongSoDoMapper;

    public ThongSoDoServiceImpl(ThongSoDoRepository thongSoDoRepository, ThongSoDoMapper thongSoDoMapper) {
        this.thongSoDoRepository = thongSoDoRepository;
        this.thongSoDoMapper = thongSoDoMapper;
    }

    /**
     * Save a thongSoDo.
     *
     * @param thongSoDoDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ThongSoDoDTO save(ThongSoDoDTO thongSoDoDTO) {
        log.debug("Request to save ThongSoDo : {}", thongSoDoDTO);
        ThongSoDo thongSoDo = thongSoDoMapper.toEntity(thongSoDoDTO);
        thongSoDo = thongSoDoRepository.save(thongSoDo);
        return thongSoDoMapper.toDto(thongSoDo);
    }

    /**
     * Get all the thongSoDos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ThongSoDoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ThongSoDos");
        return thongSoDoRepository.findAll(pageable)
            .map(thongSoDoMapper::toDto);
    }


    /**
     * Get one thongSoDo by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ThongSoDoDTO> findOne(Long id) {
        log.debug("Request to get ThongSoDo : {}", id);
        return thongSoDoRepository.findById(id)
            .map(thongSoDoMapper::toDto);
    }

    /**
     * Delete the thongSoDo by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ThongSoDo : {}", id);
        thongSoDoRepository.deleteById(id);
    }
}
