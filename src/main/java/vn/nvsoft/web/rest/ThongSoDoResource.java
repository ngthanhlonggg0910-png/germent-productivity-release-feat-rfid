package vn.nvsoft.web.rest;

import vn.nvsoft.service.ThongSoDoService;
import vn.nvsoft.web.rest.errors.BadRequestAlertException;
import vn.nvsoft.service.dto.ThongSoDoDTO;

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

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link vn.nvsoft.domain.ThongSoDo}.
 */
@RestController
@RequestMapping("/api")
public class ThongSoDoResource {

    private final Logger log = LoggerFactory.getLogger(ThongSoDoResource.class);

    private static final String ENTITY_NAME = "thongSoDo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ThongSoDoService thongSoDoService;

    public ThongSoDoResource(ThongSoDoService thongSoDoService) {
        this.thongSoDoService = thongSoDoService;
    }

    /**
     * {@code POST  /thong-so-dos} : Create a new thongSoDo.
     *
     * @param thongSoDoDTO the thongSoDoDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new thongSoDoDTO, or with status {@code 400 (Bad Request)} if the thongSoDo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/thong-so-dos")
    public ResponseEntity<ThongSoDoDTO> createThongSoDo(@Valid @RequestBody ThongSoDoDTO thongSoDoDTO) throws URISyntaxException {
        log.debug("REST request to save ThongSoDo : {}", thongSoDoDTO);
        if (thongSoDoDTO.getId() != null) {
            throw new BadRequestAlertException("A new thongSoDo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ThongSoDoDTO result = thongSoDoService.save(thongSoDoDTO);
        return ResponseEntity.created(new URI("/api/thong-so-dos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /thong-so-dos} : Updates an existing thongSoDo.
     *
     * @param thongSoDoDTO the thongSoDoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated thongSoDoDTO,
     * or with status {@code 400 (Bad Request)} if the thongSoDoDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the thongSoDoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/thong-so-dos")
    public ResponseEntity<ThongSoDoDTO> updateThongSoDo(@Valid @RequestBody ThongSoDoDTO thongSoDoDTO) throws URISyntaxException {
        log.debug("REST request to update ThongSoDo : {}", thongSoDoDTO);
        if (thongSoDoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ThongSoDoDTO result = thongSoDoService.save(thongSoDoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, thongSoDoDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /thong-so-dos} : get all the thongSoDos.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of thongSoDos in body.
     */
    @GetMapping("/thong-so-dos")
    public ResponseEntity<List<ThongSoDoDTO>> getAllThongSoDos(Pageable pageable) {
        log.debug("REST request to get a page of ThongSoDos");
        Page<ThongSoDoDTO> page = thongSoDoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /thong-so-dos/:id} : get the "id" thongSoDo.
     *
     * @param id the id of the thongSoDoDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the thongSoDoDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/thong-so-dos/{id}")
    public ResponseEntity<ThongSoDoDTO> getThongSoDo(@PathVariable Long id) {
        log.debug("REST request to get ThongSoDo : {}", id);
        Optional<ThongSoDoDTO> thongSoDoDTO = thongSoDoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(thongSoDoDTO);
    }

    /**
     * {@code DELETE  /thong-so-dos/:id} : delete the "id" thongSoDo.
     *
     * @param id the id of the thongSoDoDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/thong-so-dos/{id}")
    public ResponseEntity<Void> deleteThongSoDo(@PathVariable Long id) {
        log.debug("REST request to delete ThongSoDo : {}", id);
        thongSoDoService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
