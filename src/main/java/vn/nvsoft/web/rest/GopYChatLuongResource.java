package vn.nvsoft.web.rest;

import vn.nvsoft.service.GopYChatLuongService;
import vn.nvsoft.web.rest.errors.BadRequestAlertException;
import vn.nvsoft.service.dto.GopYChatLuongDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link vn.nvsoft.domain.GopYChatLuong}.
 */
@RestController
@RequestMapping("/api")
public class GopYChatLuongResource {

    private final Logger log = LoggerFactory.getLogger(GopYChatLuongResource.class);

    private static final String ENTITY_NAME = "gopYChatLuong";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final GopYChatLuongService gopYChatLuongService;

    public GopYChatLuongResource(GopYChatLuongService gopYChatLuongService) {
        this.gopYChatLuongService = gopYChatLuongService;
    }

    /**
     * {@code POST  /gop-y-chat-luongs} : Create a new gopYChatLuong.
     *
     * @param gopYChatLuongDTO the gopYChatLuongDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new gopYChatLuongDTO, or with status {@code 400 (Bad Request)} if the gopYChatLuong has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/gop-y-chat-luongs")
    public ResponseEntity<GopYChatLuongDTO> createGopYChatLuong(@RequestBody GopYChatLuongDTO gopYChatLuongDTO) throws URISyntaxException {
        log.debug("REST request to save GopYChatLuong : {}", gopYChatLuongDTO);
        if (gopYChatLuongDTO.getId() != null) {
            throw new BadRequestAlertException("A new gopYChatLuong cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GopYChatLuongDTO result = gopYChatLuongService.save(gopYChatLuongDTO);
        return ResponseEntity.created(new URI("/api/gop-y-chat-luongs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /gop-y-chat-luongs} : Updates an existing gopYChatLuong.
     *
     * @param gopYChatLuongDTO the gopYChatLuongDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated gopYChatLuongDTO,
     * or with status {@code 400 (Bad Request)} if the gopYChatLuongDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the gopYChatLuongDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/gop-y-chat-luongs")
    public ResponseEntity<GopYChatLuongDTO> updateGopYChatLuong(@RequestBody GopYChatLuongDTO gopYChatLuongDTO) throws URISyntaxException {
        log.debug("REST request to update GopYChatLuong : {}", gopYChatLuongDTO);
        if (gopYChatLuongDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        GopYChatLuongDTO result = gopYChatLuongService.save(gopYChatLuongDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, gopYChatLuongDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /gop-y-chat-luongs} : get all the gopYChatLuongs.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of gopYChatLuongs in body.
     */
    @GetMapping("/gop-y-chat-luongs")
    public ResponseEntity<List<GopYChatLuongDTO>> getAllGopYChatLuongs(Pageable pageable) {
        log.debug("REST request to get a page of GopYChatLuongs");
        Page<GopYChatLuongDTO> page = gopYChatLuongService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /gop-y-chat-luongs/:id} : get the "id" gopYChatLuong.
     *
     * @param id the id of the gopYChatLuongDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the gopYChatLuongDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/gop-y-chat-luongs/{id}")
    public ResponseEntity<GopYChatLuongDTO> getGopYChatLuong(@PathVariable Long id) {
        log.debug("REST request to get GopYChatLuong : {}", id);
        Optional<GopYChatLuongDTO> gopYChatLuongDTO = gopYChatLuongService.findOne(id);
        return ResponseUtil.wrapOrNotFound(gopYChatLuongDTO);
    }

    /**
     * {@code DELETE  /gop-y-chat-luongs/:id} : delete the "id" gopYChatLuong.
     *
     * @param id the id of the gopYChatLuongDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/gop-y-chat-luongs/{id}")
    public ResponseEntity<Void> deleteGopYChatLuong(@PathVariable Long id) {
        log.debug("REST request to delete GopYChatLuong : {}", id);
        gopYChatLuongService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
