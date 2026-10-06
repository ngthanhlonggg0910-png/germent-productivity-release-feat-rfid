package vn.nvsoft.service;

import java.util.List;

import javax.persistence.criteria.JoinType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import vn.nvsoft.domain.ProLoi;
import vn.nvsoft.domain.*; // for static metamodels
import vn.nvsoft.repository.ProLoiRepository;
import vn.nvsoft.service.dto.ProLoiCriteria;
import vn.nvsoft.service.dto.ProLoiDTO;
import vn.nvsoft.service.mapper.ProLoiMapper;

/**
 * Service for executing complex queries for {@link ProLoi} entities in the database.
 * The main input is a {@link ProLoiCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ProLoiDTO} or a {@link Page} of {@link ProLoiDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ProLoiQueryService extends QueryService<ProLoi> {

    private final Logger log = LoggerFactory.getLogger(ProLoiQueryService.class);

    private final ProLoiRepository proLoiRepository;

    private final ProLoiMapper proLoiMapper;

    public ProLoiQueryService(ProLoiRepository proLoiRepository, ProLoiMapper proLoiMapper) {
        this.proLoiRepository = proLoiRepository;
        this.proLoiMapper = proLoiMapper;
    }

    /**
     * Return a {@link List} of {@link ProLoiDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ProLoiDTO> findByCriteria(ProLoiCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ProLoi> specification = createSpecification(criteria);
        return proLoiMapper.toDto(proLoiRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ProLoiDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ProLoiDTO> findByCriteria(ProLoiCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ProLoi> specification = createSpecification(criteria);
        return proLoiRepository.findAll(specification, page)
            .map(proLoiMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ProLoiCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ProLoi> specification = createSpecification(criteria);
        return proLoiRepository.count(specification);
    }

    /**
     * Function to convert {@link ProLoiCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<ProLoi> createSpecification(ProLoiCriteria criteria) {
        Specification<ProLoi> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), ProLoi_.id));
            }
            if (criteria.getMaLoi() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMaLoi(), ProLoi_.maLoi));
            }
            if (criteria.getCusCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCusCode(), ProLoi_.cusCode));
            }
            if (criteria.getTenLoi() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTenLoi(), ProLoi_.tenLoi));
            }
            if (criteria.getTenTiengAnh() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTenTiengAnh(), ProLoi_.tenTiengAnh));
            }
            if (criteria.getLabel() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLabel(), ProLoi_.label));
            }
            if (criteria.getStt() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStt(), ProLoi_.stt));
            }
            if (criteria.getMauSac() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMauSac(), ProLoi_.mauSac));
            }
            if (criteria.getQc() != null) {
                specification = specification.and(buildSpecification(criteria.getQc(), ProLoi_.qc));
            }
            if (criteria.getQa() != null) {
                specification = specification.and(buildSpecification(criteria.getQa(), ProLoi_.qa));
            }
            if (criteria.getChecked() != null) {
                specification = specification.and(buildSpecification(criteria.getChecked(), ProLoi_.checked));
            }
            if (criteria.getGhiChu() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGhiChu(), ProLoi_.ghiChu));
            }
            if (criteria.getProCumLoiId() != null) {
                specification = specification.and(buildSpecification(criteria.getProCumLoiId(),
                    root -> root.join(ProLoi_.proCumLoi, JoinType.LEFT).get(ProCumLoi_.id)));
            }
        }
        return specification;
    }
}
