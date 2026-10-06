package vn.nvsoft.web.rest;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import vn.nvsoft.config.Constants;
import vn.nvsoft.security.AuthoritiesConstants;
import vn.nvsoft.service.AuthorityService;
import vn.nvsoft.service.dto.AuthorityDTO;
import vn.nvsoft.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing authority.
 * <p>
 * This class accesses the {@link vn.nvsoft.domain.Authority} entity, and needs to fetch its collection of authorities.
 * We use a View Model and a DTO for 3 reasons:
 * <ul>
 * <li>We want to keep a lazy association between the user and the authorities, because people will
 * quite often do relationships with the user, and we don't want them to get the authorities all
 * the time for nothing (for performance reasons). This is the #1 goal: we should not impact our users'
 * application because of this use-case.</li>
 * <li> Not having an outer join causes n+1 requests to the database. This is not a real issue as
 * we have by default a second-level cache. This means on the first HTTP call we do the n+1 requests,
 * but then all authorities come from the cache, so in fact it's much better than doing an outer join
 * (which will get lots of data from the database, for each HTTP call).</li>
 * <li> As this manages users, for security reasons, we'd rather have a DTO layer.</li>
 * </ul>
 * <p>
 * Another option would be to have a specific JPA entity graph to handle this case.
 */
@RestController
@RequestMapping("/api")
public class AuthorityResource {

    private final Logger log = LoggerFactory.getLogger(AuthorityResource.class);

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AuthorityService authorityService;

    public AuthorityResource(AuthorityService authorityService) {
        this.authorityService = authorityService;
    }

    /**
     * {@code POST  /authoritys}  : Creates a new Authority.
     * <p>
     * Creates a new authority
     *
     * @param authorityDTO the authority to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new authority, or with status {@code 400 (Bad Request)} if the role is already exist.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     * @throws BadRequestAlertException {@code 400 (Bad Request)} if the role is already exist.
     */
    @PostMapping("/authorities")
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<AuthorityDTO> createAuthority(@Valid @RequestBody AuthorityDTO authorityDTO) throws URISyntaxException {
        log.debug("REST request to save AuthorityDTO : {}", authorityDTO);
        try {
            this.authorityService.createAuthority(authorityDTO);
            return ResponseEntity.created(new URI("/api/authorities/" + authorityDTO.getName()))
                .headers(HeaderUtil.createAlert(applicationName,  "authority.created", authorityDTO.getName()))
                .body(authorityDTO);
        } catch (Exception e) {
            throw new BadRequestAlertException("The role already exist", "Authority", "roleExist");
        }
    }

    /**
     * {@code GET /authorities} : get all authorities.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body all authorities.
     */
    @GetMapping("/authorities")
    public ResponseEntity<List<AuthorityDTO>> getAllAuthorities(Pageable pageable) {
        final Page<AuthorityDTO> page = this.authorityService.getAllAuthorities(pageable);
        HttpHeaders headers = PaginationUtil
            .generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * {@code DELETE /authorities/:name} : delete the "name" Authority.
     *
     * @param name the name of Authority to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/authorities/{name:" + Constants.LOGIN_REGEX + "}")
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<Void> deleteUser(@PathVariable String name) {
        log.debug("REST request to delete Authority: {}", name);
        this.authorityService.deleteAuthority(name);
        return ResponseEntity
            .noContent().headers(HeaderUtil.createAlert(applicationName,  "authority.deleted", name)).build();
    }

}
