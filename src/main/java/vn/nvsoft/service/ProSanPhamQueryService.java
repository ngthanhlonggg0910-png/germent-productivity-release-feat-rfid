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

import vn.nvsoft.domain.ProSanPham;
import vn.nvsoft.domain.*; // for static metamodels
import vn.nvsoft.repository.ProSanPhamRepository;
import vn.nvsoft.service.dto.ProSanPhamCriteria;
import vn.nvsoft.service.dto.ProSanPhamDTO;
import vn.nvsoft.service.mapper.ProSanPhamMapper;

/**
 * Service for executing complex queries for {@link ProSanPham} entities in the database.
 * The main input is a {@link ProSanPhamCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ProSanPhamDTO} or a {@link Page} of {@link ProSanPhamDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ProSanPhamQueryService extends QueryService<ProSanPham> {

    private final Logger log = LoggerFactory.getLogger(ProSanPhamQueryService.class);

    private final ProSanPhamRepository proSanPhamRepository;

    private final ProSanPhamMapper proSanPhamMapper;

    public ProSanPhamQueryService(ProSanPhamRepository proSanPhamRepository, ProSanPhamMapper proSanPhamMapper) {
        this.proSanPhamRepository = proSanPhamRepository;
        this.proSanPhamMapper = proSanPhamMapper;
    }

    /**
     * Return a {@link List} of {@link ProSanPhamDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ProSanPhamDTO> findByCriteria(ProSanPhamCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ProSanPham> specification = createSpecification(criteria);
        return proSanPhamMapper.toDto(proSanPhamRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ProSanPhamDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ProSanPhamDTO> findByCriteria(ProSanPhamCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ProSanPham> specification = createSpecification(criteria);
        return proSanPhamRepository.findAll(specification, page)
            .map(proSanPhamMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ProSanPhamCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ProSanPham> specification = createSpecification(criteria);
        return proSanPhamRepository.count(specification);
    }

    /**
     * Function to convert {@link ProSanPhamCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<ProSanPham> createSpecification(ProSanPhamCriteria criteria) {
        Specification<ProSanPham> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), ProSanPham_.id));
            }
            if (criteria.getMaSanPham() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMaSanPham(), ProSanPham_.maSanPham));
            }
            if (criteria.getMaDonHang() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMaDonHang(), ProSanPham_.maDonHang));
            }
            if (criteria.getPo() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPo(), ProSanPham_.po));
            }
            if (criteria.getSoLuongSanXuat() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSoLuongSanXuat(), ProSanPham_.soLuongSanXuat));
            }
            if (criteria.getThongSoValue1() != null) {
                specification = specification.and(buildStringSpecification(criteria.getThongSoValue1(), ProSanPham_.thongSoValue1));
            }
            if (criteria.getThongSoCaption1() != null) {
                specification = specification.and(buildStringSpecification(criteria.getThongSoCaption1(), ProSanPham_.thongSoCaption1));
            }
            if (criteria.getThongSoCheck1() != null) {
                specification = specification.and(buildSpecification(criteria.getThongSoCheck1(), ProSanPham_.thongSoCheck1));
            }
            if (criteria.getThongSoValue2() != null) {
                specification = specification.and(buildStringSpecification(criteria.getThongSoValue2(), ProSanPham_.thongSoValue2));
            }
            if (criteria.getThongSoCaption2() != null) {
                specification = specification.and(buildStringSpecification(criteria.getThongSoCaption2(), ProSanPham_.thongSoCaption2));
            }
            if (criteria.getThongSoCheck2() != null) {
                specification = specification.and(buildSpecification(criteria.getThongSoCheck2(), ProSanPham_.thongSoCheck2));
            }
            if (criteria.getThongSoValue3() != null) {
                specification = specification.and(buildStringSpecification(criteria.getThongSoValue3(), ProSanPham_.thongSoValue3));
            }
            if (criteria.getThongSoCaption3() != null) {
                specification = specification.and(buildStringSpecification(criteria.getThongSoCaption3(), ProSanPham_.thongSoCaption3));
            }
            if (criteria.getThongSoCheck3() != null) {
                specification = specification.and(buildSpecification(criteria.getThongSoCheck3(), ProSanPham_.thongSoCheck3));
            }
            if (criteria.getThongSoValue4() != null) {
                specification = specification.and(buildStringSpecification(criteria.getThongSoValue4(), ProSanPham_.thongSoValue4));
            }
            if (criteria.getThongSoCaption4() != null) {
                specification = specification.and(buildStringSpecification(criteria.getThongSoCaption4(), ProSanPham_.thongSoCaption4));
            }
            if (criteria.getThongSoCheck4() != null) {
                specification = specification.and(buildSpecification(criteria.getThongSoCheck4(), ProSanPham_.thongSoCheck4));
            }
            if (criteria.getThongSoValue5() != null) {
                specification = specification.and(buildStringSpecification(criteria.getThongSoValue5(), ProSanPham_.thongSoValue5));
            }
            if (criteria.getThongSoCaption5() != null) {
                specification = specification.and(buildStringSpecification(criteria.getThongSoCaption5(), ProSanPham_.thongSoCaption5));
            }
            if (criteria.getThongSoCheck5() != null) {
                specification = specification.and(buildSpecification(criteria.getThongSoCheck5(), ProSanPham_.thongSoCheck5));
            }
            if (criteria.getThongSoValue6() != null) {
                specification = specification.and(buildStringSpecification(criteria.getThongSoValue6(), ProSanPham_.thongSoValue6));
            }
            if (criteria.getThongSoCaption6() != null) {
                specification = specification.and(buildStringSpecification(criteria.getThongSoCaption6(), ProSanPham_.thongSoCaption6));
            }
            if (criteria.getThongSoCheck6() != null) {
                specification = specification.and(buildSpecification(criteria.getThongSoCheck6(), ProSanPham_.thongSoCheck6));
            }
            if (criteria.getThongSoValue7() != null) {
                specification = specification.and(buildStringSpecification(criteria.getThongSoValue7(), ProSanPham_.thongSoValue7));
            }
            if (criteria.getThongSoCaption7() != null) {
                specification = specification.and(buildStringSpecification(criteria.getThongSoCaption7(), ProSanPham_.thongSoCaption7));
            }
            if (criteria.getThongSoCheck7() != null) {
                specification = specification.and(buildSpecification(criteria.getThongSoCheck7(), ProSanPham_.thongSoCheck7));
            }
            if (criteria.getThongSoValue8() != null) {
                specification = specification.and(buildStringSpecification(criteria.getThongSoValue8(), ProSanPham_.thongSoValue8));
            }
            if (criteria.getThongSoCaption8() != null) {
                specification = specification.and(buildStringSpecification(criteria.getThongSoCaption8(), ProSanPham_.thongSoCaption8));
            }
            if (criteria.getThongSoCheck8() != null) {
                specification = specification.and(buildSpecification(criteria.getThongSoCheck8(), ProSanPham_.thongSoCheck8));
            }
            if (criteria.getThongSoValue9() != null) {
                specification = specification.and(buildStringSpecification(criteria.getThongSoValue9(), ProSanPham_.thongSoValue9));
            }
            if (criteria.getThongSoCaption9() != null) {
                specification = specification.and(buildStringSpecification(criteria.getThongSoCaption9(), ProSanPham_.thongSoCaption9));
            }
            if (criteria.getThongSoCheck9() != null) {
                specification = specification.and(buildSpecification(criteria.getThongSoCheck9(), ProSanPham_.thongSoCheck9));
            }
            if (criteria.getThongSoValue10() != null) {
                specification = specification.and(buildStringSpecification(criteria.getThongSoValue10(), ProSanPham_.thongSoValue10));
            }
            if (criteria.getThongSoCaption10() != null) {
                specification = specification.and(buildStringSpecification(criteria.getThongSoCaption10(), ProSanPham_.thongSoCaption10));
            }
            if (criteria.getThongSoCheck10() != null) {
                specification = specification.and(buildSpecification(criteria.getThongSoCheck10(), ProSanPham_.thongSoCheck10));
            }
            if (criteria.getColor() != null) {
                specification = specification.and(buildStringSpecification(criteria.getColor(), ProSanPham_.color));
            }
            if (criteria.getHinhMatTruocURL() != null) {
                specification = specification.and(buildStringSpecification(criteria.getHinhMatTruocURL(), ProSanPham_.hinhMatTruocURL));
            }
            if (criteria.getHinhMatSauURL() != null) {
                specification = specification.and(buildStringSpecification(criteria.getHinhMatSauURL(), ProSanPham_.hinhMatSauURL));
            }
            if (criteria.getProKhachHangId() != null) {
                specification = specification.and(buildSpecification(criteria.getProKhachHangId(),
                    root -> root.join(ProSanPham_.proKhachHang, JoinType.LEFT).get(ProKhachHang_.id)));
            }
            if (criteria.getProChungLoaiSanPhamId() != null) {
                specification = specification.and(buildSpecification(criteria.getProChungLoaiSanPhamId(),
                    root -> root.join(ProSanPham_.proChungLoaiSanPham, JoinType.LEFT).get(ProChungLoaiSanPham_.id)));
            }
            if (criteria.getProTenMuaId() != null) {
                specification = specification.and(buildSpecification(criteria.getProTenMuaId(),
                    root -> root.join(ProSanPham_.proTenMua, JoinType.LEFT).get(ProTenMua_.id)));
            }
        }
        return specification;
    }
}
