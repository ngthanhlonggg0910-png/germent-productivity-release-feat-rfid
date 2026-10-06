package vn.nvsoft.service;

import vn.nvsoft.service.dto.ProRefinalCCDTO;
import vn.nvsoft.service.dto.RefinalCCAndChiTietDTO;
import vn.nvsoft.service.dto.RefinalCCCreateInfoDTO;
import vn.nvsoft.service.viewmodel.KeHoachXuatHangDTO;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link vn.nvsoft.domain.ProRefinalCC}.
 */
public interface ProRefinalCCService {

    /**
     * Save a proRefinalCC.
     *
     * @param proRefinalCCDTO the entity to save.
     * @return the persisted entity.
     */
    ProRefinalCCDTO save(ProRefinalCCDTO proRefinalCCDTO);

    /**
     * Get all the proRefinalCCS.
     *
     * @return the list of entities.
     */
    List<ProRefinalCCDTO> findAll();


    /**
     * Get the "id" proRefinalCC.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ProRefinalCCDTO> findOne(Long id);

    /**
     * Delete the "id" proRefinalCC.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    RefinalCCCreateInfoDTO getInfoToCreateRefinalCC(Long khxhId);

    List<KeHoachXuatHangDTO> getKeHoachXuatHang(Date date);

    List<RefinalCCAndChiTietDTO> getAllProRefinalCCAndChiTiet(Long khxhId);

    void createRefinalCC(RefinalCCAndChiTietDTO refinalToCreateDTO);
}
