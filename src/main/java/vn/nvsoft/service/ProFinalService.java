package vn.nvsoft.service;

import vn.nvsoft.service.dto.FinalAndChiTietDTO;
import vn.nvsoft.service.dto.ProFinalDTO;
import vn.nvsoft.service.viewmodel.KeHoachXuatHangDTO;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link vn.nvsoft.domain.ProFinal}.
 */
public interface ProFinalService {

    /**
     * Save a proFinal.
     *
     * @param proFinalDTO the entity to save.
     * @return the persisted entity.
     */
    ProFinalDTO save(ProFinalDTO proFinalDTO);

    /**
     * Get all the proFinals.
     *
     * @return the list of entities.
     */
    List<ProFinalDTO> findAll();


    /**
     * Get the "id" proFinal.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ProFinalDTO> findOne(Long id);

    /**
     * Delete the "id" proFinal.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    List<FinalAndChiTietDTO> getAllProFinalAndChiTiet(Long khxhId);

    public void createFinal(FinalAndChiTietDTO refinalToCreateDTO);

    List<KeHoachXuatHangDTO> getKeHoachXuatHang(Date date);
}
