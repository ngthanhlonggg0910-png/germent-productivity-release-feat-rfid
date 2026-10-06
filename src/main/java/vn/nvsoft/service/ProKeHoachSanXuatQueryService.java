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

import vn.nvsoft.domain.ProKeHoachSanXuat;
import vn.nvsoft.domain.*; // for static metamodels
import vn.nvsoft.repository.ProKeHoachSanXuatRepository;
import vn.nvsoft.service.dto.ProKeHoachSanXuatCriteria;
import vn.nvsoft.service.dto.ProKeHoachSanXuatDTO;
import vn.nvsoft.service.mapper.ProKeHoachSanXuatMapper;

/**
 * Service for executing complex queries for {@link ProKeHoachSanXuat} entities in the database.
 * The main input is a {@link ProKeHoachSanXuatCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ProKeHoachSanXuatDTO} or a {@link Page} of {@link ProKeHoachSanXuatDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ProKeHoachSanXuatQueryService extends QueryService<ProKeHoachSanXuat> {

    private final Logger log = LoggerFactory.getLogger(ProKeHoachSanXuatQueryService.class);

    private final ProKeHoachSanXuatRepository proKeHoachSanXuatRepository;

    private final ProKeHoachSanXuatMapper proKeHoachSanXuatMapper;

    public ProKeHoachSanXuatQueryService(ProKeHoachSanXuatRepository proKeHoachSanXuatRepository, ProKeHoachSanXuatMapper proKeHoachSanXuatMapper) {
        this.proKeHoachSanXuatRepository = proKeHoachSanXuatRepository;
        this.proKeHoachSanXuatMapper = proKeHoachSanXuatMapper;
    }

    /**
     * Return a {@link List} of {@link ProKeHoachSanXuatDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ProKeHoachSanXuatDTO> findByCriteria(ProKeHoachSanXuatCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ProKeHoachSanXuat> specification = createSpecification(criteria);
        return proKeHoachSanXuatMapper.toDto(proKeHoachSanXuatRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ProKeHoachSanXuatDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ProKeHoachSanXuatDTO> findByCriteria(ProKeHoachSanXuatCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ProKeHoachSanXuat> specification = createSpecification(criteria);
        return proKeHoachSanXuatRepository.findAll(specification, page)
            .map(proKeHoachSanXuatMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ProKeHoachSanXuatCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ProKeHoachSanXuat> specification = createSpecification(criteria);
        return proKeHoachSanXuatRepository.count(specification);
    }

    /**
     * Function to convert {@link ProKeHoachSanXuatCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<ProKeHoachSanXuat> createSpecification(ProKeHoachSanXuatCriteria criteria) {
        Specification<ProKeHoachSanXuat> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), ProKeHoachSanXuat_.id));
            }
            if (criteria.getNgaySanXuat() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNgaySanXuat(), ProKeHoachSanXuat_.ngaySanXuat));
            }
            if (criteria.getSoLuongYeuCau() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSoLuongYeuCau(), ProKeHoachSanXuat_.soLuongYeuCau));
            }
            if (criteria.getTinhTrang() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTinhTrang(), ProKeHoachSanXuat_.tinhTrang));
            }
            if (criteria.getGhiChu() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGhiChu(), ProKeHoachSanXuat_.ghiChu));
            }
            if (criteria.getSam() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSam(), ProKeHoachSanXuat_.sam));
            }
            if (criteria.getLotNo() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLotNo(), ProKeHoachSanXuat_.lotNo));
            }
            if (criteria.getProChuyenId() != null) {
                specification = specification.and(buildSpecification(criteria.getProChuyenId(),
                    root -> root.join(ProKeHoachSanXuat_.proChuyen, JoinType.LEFT).get(ProChuyen_.id)));
            }
            if (criteria.getProSanPhamId() != null) {
                specification = specification.and(buildSpecification(criteria.getProSanPhamId(),
                    root -> root.join(ProKeHoachSanXuat_.proSanPham, JoinType.LEFT).get(ProSanPham_.id)));
            }
        }
        return specification;
    }
}
