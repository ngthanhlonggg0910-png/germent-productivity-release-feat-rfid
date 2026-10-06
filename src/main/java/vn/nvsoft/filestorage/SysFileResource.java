package vn.nvsoft.filestorage;

import java.util.Arrays;
import java.util.stream.Collectors;
import org.springframework.web.multipart.MultipartFile;
import vn.nvsoft.service.SysFileService;
import vn.nvsoft.service.dto.SysFileDTO;
import vn.nvsoft.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link vn.nvsoft.domain.SysFile}.
 */
@RestController
@RequestMapping("/api")
public class SysFileResource {

    private final Logger log = LoggerFactory.getLogger(SysFileResource.class);

    private static final String ENTITY_NAME = "sysFile";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SysFileService sysFileService;

    private final FileStorageService fileStorageService;

    private final FileStorageMapper fileMapper;

    public SysFileResource(SysFileService sysFileService, FileStorageService fileStorageService,
        FileStorageMapper fileMapper) {
        this.sysFileService = sysFileService;
        this.fileStorageService = fileStorageService;
        this.fileMapper = fileMapper;
    }

    /**
     * {@code POST  /sys-files} : Create a new sysFile.
     *
     * @param file the sysFileDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new
     * sysFileDTO, or with status {@code 400 (Bad Request)} if the sysFile has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sys-files")
    public ResponseEntity<FileStorageVM> uploadFile(@RequestParam("file") MultipartFile file) {
        log.debug("REST request to save SysFile");
        FileStorageVM uploadedFile =
            fileStorageService.store(file).orElseThrow(() ->
                new FileStorageException("Failed to read stored files"));

        SysFileDTO sysFileDTO = fileMapper.fileVMToSysFileDTO(uploadedFile);
        sysFileService.save(sysFileDTO);
        return ResponseEntity.ok().body(uploadedFile);
    }

    @PostMapping("/sys-files/upload-multiple-files")
    public List<FileStorageVM> uploadMultipleFiles(
        @RequestParam("files") MultipartFile[] files) {
        return Arrays.asList(files)
            .stream()
            .map(file -> uploadFile(file).getBody())
            .collect(Collectors.toList());
    }

    /**
     * {@code PUT  /sys-files} : Updates an existing sysFile.
     *
     * @param sysFileDTO the sysFileDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated
     * sysFileDTO, or with status {@code 400 (Bad Request)} if the sysFileDTO is not valid, or with
     * status {@code 500 (Internal Server Error)} if the sysFileDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    // @PutMapping("/sys-files")
    public ResponseEntity<SysFileDTO> updateSysFile(@Valid @RequestBody SysFileDTO sysFileDTO)
        throws URISyntaxException {
        log.debug("REST request to update SysFile : {}", sysFileDTO);
        if (sysFileDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SysFileDTO result = sysFileService.save(sysFileDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME,
                sysFileDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /sys-files} : get all the sysFiles.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sysFiles in
     * body.
     */
    @GetMapping("/sys-files")
    public ResponseEntity<List<SysFileDTO>> getAllSysFiles(Pageable pageable,
        @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get a page of SysFiles");
        Page<SysFileDTO> page = sysFileService.findAll(pageable);
        HttpHeaders headers = PaginationUtil
            .generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /sys-files/:id} : get the "id" sysFile.
     *
     * @param id the id of the sysFileDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sysFileDTO,
     * or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sys-files/{id}")
    public ResponseEntity<SysFileDTO> getSysFile(@PathVariable Long id) {
        log.debug("REST request to get SysFile : {}", id);
        Optional<SysFileDTO> sysFileDTO = sysFileService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sysFileDTO);
    }

    /**
     * {@code DELETE  /sys-files/:id} : delete the "id" sysFile.
     *
     * @param id the id of the sysFileDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sys-files/{id}")
    public ResponseEntity<Void> deleteSysFile(@PathVariable Long id) {
        log.debug("REST request to delete SysFile : {}", id);
        sysFileService.delete(id);
        return ResponseEntity.noContent().headers(
            HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

}
