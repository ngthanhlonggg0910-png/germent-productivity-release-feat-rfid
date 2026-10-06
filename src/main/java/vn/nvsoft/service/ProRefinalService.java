package vn.nvsoft.service;

import vn.nvsoft.domain.PrefinalTheoGio;
import vn.nvsoft.service.dto.ProRefinalDTO;
import vn.nvsoft.service.dto.RefinalAndChiTietDTO;
import vn.nvsoft.service.dto.RefinalCreateInfoDTO;
import vn.nvsoft.service.dto.ProRefinalTheoGioDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link vn.nvsoft.domain.ProRefinal}.
 */
public interface ProRefinalService {

    /**
     * Save a proRefinal.
     *
     * @param proRefinalDTO the entity to save.
     * @return the persisted entity.
     */
    ProRefinalDTO save(ProRefinalDTO proRefinalDTO);

    /**
     * Get all the proRefinals.
     *
     * @return the list of entities.
     */
    List<ProRefinalDTO> findAll();


    /**
     * Get the "id" proRefinal.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ProRefinalDTO> findOne(Long id);

    /**
     * Delete the "id" proRefinal.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Get all refinal today
     * @param proKHSXId
     * @return
     */
    public List<RefinalAndChiTietDTO> getRefinalAndChiTiets(Long proKHSXId);

    /**
     * Create refinal and chi tiet refinal
     * @param refinalToCreateDTO
     */
    public void createRefinal(RefinalAndChiTietDTO refinalToCreateDTO);

    /**
     * Get info refinal to create
     * @param checkPointRefinalId
     * @param proKHSXId
     * @return
     */
    public RefinalCreateInfoDTO getInfoToCreateRefinal(Long checkPointRefinalId, Long proKHSXId);
    public List<PrefinalTheoGio> getAllProRefinalTheoGios(Long chuyen);
}
