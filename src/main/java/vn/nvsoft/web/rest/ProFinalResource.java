package vn.nvsoft.web.rest;

import org.springframework.format.annotation.DateTimeFormat;
import vn.nvsoft.service.ProFinalService;
import vn.nvsoft.service.dto.FinalAndChiTietDTO;
import vn.nvsoft.service.viewmodel.KeHoachXuatHangDTO;
import vn.nvsoft.web.rest.errors.BadRequestAlertException;
import vn.nvsoft.service.dto.ProFinalDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link vn.nvsoft.domain.ProFinal}.
 */
@RestController
@RequestMapping("/api")
public class ProFinalResource {

    private final Logger log = LoggerFactory.getLogger(ProFinalResource.class);

    private static final String ENTITY_NAME = "proFinal";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProFinalService proFinalService;

    public ProFinalResource(ProFinalService proFinalService) {
        this.proFinalService = proFinalService;
    }

    /**
     * {@code POST  /pro-finals} : Create a new proFinal.
     *
     * @param proFinalDTO the proFinalDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new proFinalDTO, or with status {@code 400 (Bad Request)} if the proFinal has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pro-finals")
    public ResponseEntity<ProFinalDTO> createProFinal(@RequestBody ProFinalDTO proFinalDTO) throws URISyntaxException {
        log.debug("REST request to save ProFinal : {}", proFinalDTO);
        if (proFinalDTO.getId() != null) {
            throw new BadRequestAlertException("A new proFinal cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProFinalDTO result = proFinalService.save(proFinalDTO);
        return ResponseEntity.created(new URI("/api/pro-finals/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /pro-finals} : Updates an existing proFinal.
     *
     * @param proFinalDTO the proFinalDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated proFinalDTO,
     * or with status {@code 400 (Bad Request)} if the proFinalDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the proFinalDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pro-finals")
    public ResponseEntity<ProFinalDTO> updateProFinal(@RequestBody ProFinalDTO proFinalDTO) throws URISyntaxException {
        log.debug("REST request to update ProFinal : {}", proFinalDTO);
        if (proFinalDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProFinalDTO result = proFinalService.save(proFinalDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, proFinalDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /pro-finals} : get all the proFinals.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of proFinals in body.
     */
    @GetMapping("/pro-finals")
    public List<ProFinalDTO> getAllProFinals() {
        log.debug("REST request to get all ProFinals");
        return proFinalService.findAll();
    }

    /**
     * {@code GET  /pro-finals/:id} : get the "id" proFinal.
     *
     * @param id the id of the proFinalDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the proFinalDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pro-finals/{id}")
    public ResponseEntity<ProFinalDTO> getProFinal(@PathVariable Long id) {
        log.debug("REST request to get ProFinal : {}", id);
        Optional<ProFinalDTO> proFinalDTO = proFinalService.findOne(id);
        return ResponseUtil.wrapOrNotFound(proFinalDTO);
    }

    /**
     * {@code DELETE  /pro-finals/:id} : delete the "id" proFinal.
     *
     * @param id the id of the proFinalDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/pro-finals/{id}")
    public ResponseEntity<Void> deleteProFinal(@PathVariable Long id) {
        log.debug("REST request to delete ProFinal : {}", id);
        proFinalService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code GET  /pro-finals/final-chi-tiet/{khxh-id}} : get all the FinalAndChiTietDTO cua ke hoach san xuat id
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of proRefinalCCS in body.
     */
    @GetMapping("/pro-finals/final-chi-tiet/{khxh-id}")
    public List<FinalAndChiTietDTO> getAllProFinalAndChiTiet(@PathVariable("khxh-id") Long khxhId) {
        log.debug("REST request to get all getAllProFinalAndChiTiet cua khxh {}", khxhId);
        return proFinalService.getAllProFinalAndChiTiet(khxhId);
    }

    /**
     * {@code POST /pro-finals/create} : Create a new proFinal and chi tiet.
     *
     * @param refinalToCreateDTO the refinalToCreateDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new proRefinalDTO, or with status {@code 400 (Bad Request)} if the proRefinal has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pro-finals/create")
    public ResponseEntity<Void> createProFinalAndDetail(@RequestBody FinalAndChiTietDTO refinalToCreateDTO) throws URISyntaxException {
        log.debug("REST request to save ProRefinalCC and chi tiet: {}", refinalToCreateDTO);

        proFinalService.createFinal(refinalToCreateDTO);
        return ResponseEntity.ok().build();
    }

    /**
     * {@code GET  /pro-finals/khxh/khxh} : get ke hoach xuat hang theo ngay hien tai va chuyen.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of proRefinals in body.
     */
    @GetMapping("/pro-finals/khxh/{date}")
    public List<KeHoachXuatHangDTO> getKeHoachXuatHang(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date) {
        log.debug("REST request to get ke hoach xuat hang of date: {}", date);
        return proFinalService.getKeHoachXuatHang(date);
    }
}
