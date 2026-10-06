package vn.nvsoft.web.rest;

import vn.nvsoft.service.GroupTopicService;
import vn.nvsoft.web.rest.errors.BadRequestAlertException;
import vn.nvsoft.service.dto.GroupTopicDTO;

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
 * REST controller for managing {@link vn.nvsoft.domain.GroupTopic}.
 */
@RestController
@RequestMapping("/api")
public class GroupTopicResource {

    private final Logger log = LoggerFactory.getLogger(GroupTopicResource.class);

    private static final String ENTITY_NAME = "groupTopic";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final GroupTopicService groupTopicService;

    public GroupTopicResource(GroupTopicService groupTopicService) {
        this.groupTopicService = groupTopicService;
    }

    /**
     * {@code POST  /group-topics} : Create a new groupTopic.
     *
     * @param groupTopicDTO the groupTopicDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new groupTopicDTO, or with status {@code 400 (Bad Request)} if the groupTopic has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/group-topics")
    public ResponseEntity<GroupTopicDTO> createGroupTopic(@RequestBody GroupTopicDTO groupTopicDTO) throws URISyntaxException {
        log.debug("REST request to save GroupTopic : {}", groupTopicDTO);
        if (groupTopicDTO.getId() != null) {
            throw new BadRequestAlertException("A new groupTopic cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GroupTopicDTO result = groupTopicService.save(groupTopicDTO);
        return ResponseEntity.created(new URI("/api/group-topics/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /group-topics} : Updates an existing groupTopic.
     *
     * @param groupTopicDTO the groupTopicDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated groupTopicDTO,
     * or with status {@code 400 (Bad Request)} if the groupTopicDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the groupTopicDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/group-topics")
    public ResponseEntity<GroupTopicDTO> updateGroupTopic(@RequestBody GroupTopicDTO groupTopicDTO) throws URISyntaxException {
        log.debug("REST request to update GroupTopic : {}", groupTopicDTO);
        if (groupTopicDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        GroupTopicDTO result = groupTopicService.save(groupTopicDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, groupTopicDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /group-topics} : get all the groupTopics.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of groupTopics in body.
     */
    @GetMapping("/group-topics")
    public ResponseEntity<List<GroupTopicDTO>> getAllGroupTopics(Pageable pageable) {
        log.debug("REST request to get a page of GroupTopics");
        Page<GroupTopicDTO> page = groupTopicService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /group-topics/:id} : get the "id" groupTopic.
     *
     * @param id the id of the groupTopicDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the groupTopicDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/group-topics/{id}")
    public ResponseEntity<GroupTopicDTO> getGroupTopic(@PathVariable Long id) {
        log.debug("REST request to get GroupTopic : {}", id);
        Optional<GroupTopicDTO> groupTopicDTO = groupTopicService.findOne(id);
        return ResponseUtil.wrapOrNotFound(groupTopicDTO);
    }

    /**
     * {@code DELETE  /group-topics/:id} : delete the "id" groupTopic.
     *
     * @param id the id of the groupTopicDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/group-topics/{id}")
    public ResponseEntity<Void> deleteGroupTopic(@PathVariable Long id) {
        log.debug("REST request to delete GroupTopic : {}", id);
        groupTopicService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
