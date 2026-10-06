package vn.nvsoft.service.impl;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import vn.nvsoft.domain.OmmPOSize;
import vn.nvsoft.domain.ProKeHoachSanXuat;
import vn.nvsoft.domain.ThongSoDoSP;
import vn.nvsoft.service.ThongSoDoSPSizeService;
import vn.nvsoft.service.ThongSoThucThiService;
import vn.nvsoft.domain.ThongSoThucThi;
import vn.nvsoft.repository.ThongSoThucThiRepository;
import vn.nvsoft.service.UserService;
import vn.nvsoft.service.dto.ThongSoDoSPSizeDTO;
import vn.nvsoft.service.dto.ThongSoThucThiDTO;
import vn.nvsoft.service.dto.UserDTO;
import vn.nvsoft.service.mapper.ThongSoThucThiMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ThongSoThucThi}.
 */
@Service
@Transactional
public class ThongSoThucThiServiceImpl implements ThongSoThucThiService {

    private final Logger log = LoggerFactory.getLogger(ThongSoThucThiServiceImpl.class);

    private final ThongSoThucThiRepository thongSoThucThiRepository;

    private final ThongSoThucThiMapper thongSoThucThiMapper;

    private final ThongSoDoSPSizeService thongSoDoSPSizeService;
    private final UserService m_userService;

    public ThongSoThucThiServiceImpl(ThongSoThucThiRepository thongSoThucThiRepository, ThongSoThucThiMapper thongSoThucThiMapper,
        ThongSoDoSPSizeService thongSoDoSPSizeService, UserService userService) {
        this.thongSoThucThiRepository = thongSoThucThiRepository;
        this.thongSoThucThiMapper = thongSoThucThiMapper;
        this.thongSoDoSPSizeService = thongSoDoSPSizeService;
        this.m_userService = userService;
    }

    /**
     * Save a thongSoThucThi.
     *
     * @param thongSoThucThiDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ThongSoThucThiDTO save(ThongSoThucThiDTO thongSoThucThiDTO) {
        log.debug("Request to save ThongSoThucThi : {}", thongSoThucThiDTO);
        ThongSoThucThi thongSoThucThi = thongSoThucThiMapper.toEntity(thongSoThucThiDTO);
        //check QA or QC
        UserDTO currentUser = m_userService.getUserWithAuthorities().map(UserDTO::new).get();
        //hard code
        if (currentUser.getLogin().startsWith("qc.")) {
            thongSoThucThi.setIsqa(Boolean.FALSE);
        } else {
            thongSoThucThi.setIsqa(Boolean.TRUE);
        }
        thongSoThucThi = thongSoThucThiRepository.save(thongSoThucThi);
        return thongSoThucThiMapper.toDto(thongSoThucThi);
    }

    /**
     * Get all the thongSoThucThis.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ThongSoThucThiDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ThongSoThucThis");
        return thongSoThucThiRepository.findAll(pageable)
            .map(thongSoThucThiMapper::toDto);
    }


    /**
     * Get one thongSoThucThi by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ThongSoThucThiDTO> findOne(Long id) {
        log.debug("Request to get ThongSoThucThi : {}", id);
        return thongSoThucThiRepository.findById(id)
            .map(thongSoThucThiMapper::toDto);
    }

    /**
     * Delete the thongSoThucThi by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ThongSoThucThi : {}", id);
        thongSoThucThiRepository.deleteById(id);
    }

    /**
     * Get all ThongSoThucThi by ThongSoDoSP Id
     * If not found, it will create 10 ThongSoThucThi per ThongSoDoSP per size
     * @param thongSoDoSPId
     * @return
     */
    @Override
    public List<ThongSoThucThiDTO> findAllByThongSoDoSPId(Long thongSoDoSPId, Long keHoachSanXuatId, Long checkPointId,String mausanpham) {
        ThongSoDoSP thongSoDoSP = new ThongSoDoSP();
        thongSoDoSP.setId(thongSoDoSPId);
        ProKeHoachSanXuat keHoachSanXuat = new ProKeHoachSanXuat();
        keHoachSanXuat.setId(keHoachSanXuatId);
        List<ThongSoThucThi> results = thongSoThucThiRepository.findByThongSoDoSPSizeThongSoDoSPAndProKeHoachSanXuatAndCheckPointIdAndMausanpham(thongSoDoSP, keHoachSanXuat, checkPointId,mausanpham);
        if (Iterables.isEmpty(results)) {
            List<ThongSoThucThiDTO> newThongSoThucThis = Lists.newArrayList();
            List<ThongSoDoSPSizeDTO> thongSoDoSPSizeDTOS = getThongSoDoSPSizeByPO(thongSoDoSPId);
            if (!Iterables.isEmpty(thongSoDoSPSizeDTOS)) {
                //create default 10 entity per ThongSoDoSPSizeDTO
                log.info("Create default ThongSoThucThi with Mau San Pham {}",mausanpham);
                thongSoDoSPSizeDTOS.forEach(thongSoDoSPSizeDTO -> {
                    for (int i = 0; i < 10; i++) {
                        ThongSoThucThiDTO thongSoThucThiDTO = new ThongSoThucThiDTO();
                        thongSoThucThiDTO.setSpIndex("SP " + (i + 1));
                        thongSoThucThiDTO.setThongSoDoSPSize(thongSoDoSPSizeDTO);
                        thongSoThucThiDTO.setProKeHoachSanXuatId(keHoachSanXuatId);
                        thongSoThucThiDTO.setCheckPointId(checkPointId);
                        thongSoThucThiDTO.setMausanpham(mausanpham);

                        thongSoThucThiDTO = save(thongSoThucThiDTO);
                        newThongSoThucThis.add(thongSoThucThiDTO);
                    }
                });
                return newThongSoThucThis;
            }
        }
		else
		{
			results.sort(Comparator.comparingInt(thongSo -> getIndex(thongSo.getSpIndex())));
        }

        return results.stream().map(thongSoThucThiMapper::toDto).collect(Collectors.toList());
    }

    private int getIndex(String spIndex) {
        String tmp[] = spIndex.split(" ");
        if (tmp.length >= 2) {
            try {
                return Integer.parseInt(tmp[1]);
            } catch (Exception ignore) {

            }
        }
        return 0;
    }
    private List<ThongSoDoSPSizeDTO> getThongSoDoSPSizeByPO(Long thongSoDoSPId) {
        ThongSoDoSP thongSoDoSP = new ThongSoDoSP();
        thongSoDoSP.setId(thongSoDoSPId);
        List<ThongSoDoSPSizeDTO> thongSoDoSPSizeDTOS = thongSoDoSPSizeService.findAllByThongSoDoSP(thongSoDoSP);
        List<OmmPOSize> poSizes = thongSoThucThiRepository.findSPSizeByThongSoDoSP(thongSoDoSPId);

        List<ThongSoDoSPSizeDTO> results = Lists.newArrayList();
        for (ThongSoDoSPSizeDTO thongSoDoSPSizeDTO : thongSoDoSPSizeDTOS) {
            for (OmmPOSize poSize : poSizes) {
                if (thongSoDoSPSizeDTO.getSize().equalsIgnoreCase(poSize.getTenSize())) {
                    results.add(thongSoDoSPSizeDTO);
                    break;
                }
            }
        }
        return results;
    }
}
