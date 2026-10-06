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

import vn.nvsoft.domain.ProKhachHang;
import vn.nvsoft.domain.*; // for static metamodels
import vn.nvsoft.repository.ProKhachHangRepository;
import vn.nvsoft.service.dto.ProKhachHangCriteria;
import vn.nvsoft.service.dto.ProKhachHangDTO;
import vn.nvsoft.service.mapper.ProKhachHangMapper;

/**
 * Service for executing complex queries for {@link ProKhachHang} entities in the database.
 * The main input is a {@link ProKhachHangCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ProKhachHangDTO} or a {@link Page} of {@link ProKhachHangDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ProKhachHangQueryService extends QueryService<ProKhachHang> {

    private final Logger log = LoggerFactory.getLogger(ProKhachHangQueryService.class);

    private final ProKhachHangRepository proKhachHangRepository;

    private final ProKhachHangMapper proKhachHangMapper;

    public ProKhachHangQueryService(ProKhachHangRepository proKhachHangRepository, ProKhachHangMapper proKhachHangMapper) {
        this.proKhachHangRepository = proKhachHangRepository;
        this.proKhachHangMapper = proKhachHangMapper;
    }

    /**
     * Return a {@link List} of {@link ProKhachHangDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ProKhachHangDTO> findByCriteria(ProKhachHangCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ProKhachHang> specification = createSpecification(criteria);
        return proKhachHangMapper.toDto(proKhachHangRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ProKhachHangDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ProKhachHangDTO> findByCriteria(ProKhachHangCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ProKhachHang> specification = createSpecification(criteria);
        return proKhachHangRepository.findAll(specification, page)
            .map(proKhachHangMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ProKhachHangCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ProKhachHang> specification = createSpecification(criteria);
        return proKhachHangRepository.count(specification);
    }

    /**
     * Function to convert {@link ProKhachHangCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<ProKhachHang> createSpecification(ProKhachHangCriteria criteria) {
        Specification<ProKhachHang> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), ProKhachHang_.id));
            }
            if (criteria.getTen() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTen(), ProKhachHang_.ten));
            }
            if (criteria.getNhom() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNhom(), ProKhachHang_.nhom));
            }
            if (criteria.getDiaChi() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDiaChi(), ProKhachHang_.diaChi));
            }
        }
        return specification;
    }
}
