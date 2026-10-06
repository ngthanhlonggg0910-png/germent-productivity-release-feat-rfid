package vn.nvsoft.web.rest;

import vn.nvsoft.service.CauHinhZaloService;
import vn.nvsoft.web.rest.errors.BadRequestAlertException;
import vn.nvsoft.service.dto.CauHinhZaloDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link vn.nvsoft.domain.CauHinhZalo}.
 */
@RestController
@RequestMapping("/api")
public class CauHinhZaloResource {

    private final Logger log = LoggerFactory.getLogger(CauHinhZaloResource.class);

    private final CauHinhZaloService cauHinhZaloService;

    public CauHinhZaloResource(CauHinhZaloService cauHinhZaloService) {
        this.cauHinhZaloService = cauHinhZaloService;
    }

    /**
     * {@code GET  /cau-hinh-zalos} : get all the cauHinhZalos.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of cauHinhZalos in body.
     */
    @GetMapping("/cau-hinh-zalos")
    public List<CauHinhZaloDTO> getAllCauHinhZalos() {
        log.debug("REST request to get all CauHinhZalos");
        return cauHinhZaloService.findAll();
    }

    /**
     * {@code GET  /cau-hinh-zalos/:id} : get the "id" cauHinhZalo.
     *
     * @param id the id of the cauHinhZaloDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the cauHinhZaloDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/cau-hinh-zalos/{id}")
    public ResponseEntity<CauHinhZaloDTO> getCauHinhZalo(@PathVariable Long id) {
        log.debug("REST request to get CauHinhZalo : {}", id);
        Optional<CauHinhZaloDTO> cauHinhZaloDTO = cauHinhZaloService.findOne(id);
        return ResponseUtil.wrapOrNotFound(cauHinhZaloDTO);
    }
}
