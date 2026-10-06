package vn.nvsoft.service;

import vn.nvsoft.service.dto.ProKeHoachSanXuatDTO;
import vn.nvsoft.service.dto.ProKeHoachSanXuatActivationStatusDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.Optional;

/**
 * Service Interface for managing {@link vn.nvsoft.domain.ProKeHoachSanXuat}.
 */
public interface ProKeHoachSanXuatService {

    /**
     * Save a proKeHoachSanXuat.
     *
     * @param proKeHoachSanXuatDTO the entity to save.
     * @return the persisted entity.
     */
    ProKeHoachSanXuatDTO save(ProKeHoachSanXuatDTO proKeHoachSanXuatDTO);

    /**
     * Get all the proKeHoachSanXuats.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ProKeHoachSanXuatDTO> findAll(Pageable pageable);


    /**
     * Get the "id" proKeHoachSanXuat.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ProKeHoachSanXuatDTO> findOne(Long id);

    /**
     * Lấy trạng thái kích hoạt ({@code isActived} / {@code active_time}) theo id kế hoạch.
     *
     * @param khsxId id bản ghi {@code pro_ke_hoach_san_xuat}
     * @return DTO chứa {@code isActived} (JSON) và thời điểm active nếu có bản ghi
     */
    Optional<ProKeHoachSanXuatActivationStatusDTO> getActivationStatus(Long khsxId);

    /**
     * Delete the "id" proKeHoachSanXuat.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Đặt {@code isActived = true} cho kế hoạch {@code id}; mọi kế hoạch khác cùng chuyền và cùng ngày sản xuất (theo lịch ngày múi {@code Asia/Ho_Chi_Minh}) được đặt {@code isActived = false}.
     *
     * @param id id bản ghi {@code pro_ke_hoach_san_xuat} cần kích hoạt
     * @return bản ghi sau cập nhật, rỗng nếu không tồn tại
     */
    Optional<ProKeHoachSanXuatDTO> activateExclusiveForChuyenDay(Long id);

    /**
     * Đặt {@code isActived = true} cho kế hoạch {@code proKhsxId}; mọi kế hoạch khác cùng {@code chuyenId} và cùng ngày {@code ngaySanXuat} (lịch {@code Asia/Ho_Chi_Minh}) {@code isActived = false}.
     * Chỉ thực hiện nếu bản ghi tồn tại, thuộc đúng chuyền và đúng ngày truyền lên.
     *
     * @param chuyenId   id chuyền ({@code pro_chuyen_id})
     * @param ngaySanXuat ngày sản xuất (theo lịch, không theo giờ trong ngày)
     * @param proKhsxId   id bản ghi {@code pro_ke_hoach_san_xuat} cần kích hoạt
     * @return bản ghi sau cập nhật, rỗng nếu không tồn tại
     */
    Optional<ProKeHoachSanXuatDTO> activateExclusiveForChuyenDay(Long chuyenId, LocalDate ngaySanXuat, Long proKhsxId);
}
