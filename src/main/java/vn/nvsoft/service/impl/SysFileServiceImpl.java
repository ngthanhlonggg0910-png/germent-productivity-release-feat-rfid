package vn.nvsoft.service.impl;

import vn.nvsoft.filestorage.FileStorageService;
import vn.nvsoft.service.SysFileService;
import vn.nvsoft.domain.SysFile;
import vn.nvsoft.repository.SysFileRepository;
import vn.nvsoft.service.dto.SysFileDTO;
import vn.nvsoft.service.mapper.SysFileMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link SysFile}.
 */
@Service
@Transactional
public class SysFileServiceImpl implements SysFileService {

    private final Logger log = LoggerFactory.getLogger(SysFileServiceImpl.class);

    private final SysFileRepository sysFileRepository;

    private final SysFileMapper sysFileMapper;

    private final FileStorageService fileStorageService;

    public SysFileServiceImpl(SysFileRepository sysFileRepository,
        SysFileMapper sysFileMapper,
        FileStorageService fileStorageService) {
        this.sysFileRepository = sysFileRepository;
        this.sysFileMapper = sysFileMapper;
        this.fileStorageService = fileStorageService;
    }

    /**
     * Save a sysFile.
     *
     * @param sysFileDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public SysFileDTO save(SysFileDTO sysFileDTO) {
        log.debug("Request to save SysFile : {}", sysFileDTO);
        SysFile sysFile = sysFileMapper.toEntity(sysFileDTO);
        sysFile = sysFileRepository.save(sysFile);
        SysFileDTO result = sysFileMapper.toDto(sysFile);
        return result;
    }

    /**
     * Get all the sysFiles.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<SysFileDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SysFiles");
        return sysFileRepository.findAll(pageable)
            .map(sysFileMapper::toDto);
    }


    /**
     * Get one sysFile by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SysFileDTO> findOne(Long id) {
        log.debug("Request to get SysFile : {}", id);
        return sysFileRepository.findById(id)
            .map(sysFileMapper::toDto);
    }

    /**
     * Delete the sysFile by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SysFile : {}", id);
        sysFileRepository.findById(id).map((file) -> {
            sysFileRepository.deleteById(id);
            fileStorageService.deleteFile(file.getFileName());
            return file;
        });
    }
}
