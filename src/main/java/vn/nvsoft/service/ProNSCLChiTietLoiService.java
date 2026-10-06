package vn.nvsoft.service;

import java.util.List;
import vn.nvsoft.domain.enumeration.GioLam;
import vn.nvsoft.service.dto.ProNSCLChiTietLoiDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.nvsoft.service.dto.WarningProNSCLChiTietLoiVO;

import java.util.Optional;

/**
 * Service Interface for managing {@link vn.nvsoft.domain.ProNSCLChiTietLoi}.
 */
public interface ProNSCLChiTietLoiService {

    /**
     * Save a proNSCLChiTietLoi.
     *
     * @param proNSCLChiTietLoiDTO the entity to save.
     * @return the persisted entity.
     */
    ProNSCLChiTietLoiDTO save(ProNSCLChiTietLoiDTO proNSCLChiTietLoiDTO);

    /**
     * Get all the proNSCLChiTietLois.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ProNSCLChiTietLoiDTO> findAll(Pageable pageable);


    /**
     * Get the "id" proNSCLChiTietLoi.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ProNSCLChiTietLoiDTO> findOne(Long id);

    /**
     * Delete the "id" proNSCLChiTietLoi.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    List<ProNSCLChiTietLoiDTO> getByNSCLId(Long nsclId);

    List<ProNSCLChiTietLoiDTO> getNSCLChiTietTheoGio(Long nsclId, GioLam gioLam);

    void updateWarningOfProNSCLChiTietLoi(WarningProNSCLChiTietLoiVO warningProNSCLChiTietLoiVO);
}
