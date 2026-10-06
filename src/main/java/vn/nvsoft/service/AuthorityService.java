package vn.nvsoft.service;

import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.nvsoft.domain.Authority;
import vn.nvsoft.repository.AuthorityRepository;
import vn.nvsoft.service.dto.AuthorityDTO;

/**
 * Service class for managing users.
 */
@Service
@Transactional
public class AuthorityService {

    private final Logger log = LoggerFactory.getLogger(AuthorityService.class);

    private final AuthorityRepository authorityRepository;

    public AuthorityService(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    public AuthorityDTO createAuthority(AuthorityDTO authorityDTO) {
        Authority authority = new Authority();
        authority.setName(authorityDTO.getName());
        authorityRepository.save(authority);
        return authorityDTO;
    }

    public void deleteAuthority(String name) {
        authorityRepository.findById(name).ifPresent(authority -> authorityRepository.delete(authority));
    }

    @Transactional(readOnly = true)
    public Page<AuthorityDTO> getAllAuthorities(Pageable pageable) {
        return authorityRepository.findAll(pageable).map(AuthorityDTO::new);
    }

    /**
     * Gets a list of all the authorities.
     * @return a list of all the authorities.
     */
    public List<String> getAuthorities() {
        return authorityRepository.findAll().stream().map(Authority::getName).collect(Collectors.toList());
    }

}
