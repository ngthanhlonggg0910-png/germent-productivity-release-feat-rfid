package vn.nvsoft.service.mapper;


import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import vn.nvsoft.domain.ProNSCL;
import vn.nvsoft.domain.enumeration.GioLam;
import vn.nvsoft.service.dto.NSCLTheoGioDTO;
import vn.nvsoft.service.dto.ProGioLamViecDTO;
import vn.nvsoft.service.dto.ProNSCLChiTietLoiDTO;
import vn.nvsoft.service.dto.ProNSCLDTO;

/**
 * Mapper for the entity {@link ProNSCL} and its DTO {@link ProNSCLDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface NSCLTheoGioMapper {

    default void computeQtyAndErrorRate(NSCLTheoGioDTO nsclTheoGioDTO, Integer qty, Integer error) {
        int sum = 0;
        if (qty != null) {
            sum += qty;
        } else {
            qty = 0;
        }
        if (error != null) {
            sum += error;
        } else {
            error = 0;
        }
        if (sum != 0) {
            nsclTheoGioDTO.setErrorRate((float)error/(float)sum * 100);
            nsclTheoGioDTO.setQualifiedRate((float)qty/(float)sum * 100);
        } else {
            nsclTheoGioDTO.setErrorRate(0f);
            nsclTheoGioDTO.setQualifiedRate(0f);
        }

    }
    default NSCLTheoGioDTO toDto(ProNSCL proNSCL, ProGioLamViecDTO gioLam, List<ProNSCLChiTietLoiDTO> nsclChiTietLoiDtos) {
        NSCLTheoGioDTO nsclTheoGioDTO = new NSCLTheoGioDTO();
        nsclTheoGioDTO.setId(proNSCL.getId());

        switch (gioLam.getGioLam()) {
            case C_1H:
                nsclTheoGioDTO.setQty(proNSCL.getQty1h());
                nsclTheoGioDTO.setFix(proNSCL.getFix1h());
                nsclTheoGioDTO.setMan(proNSCL.getMan1h());
                nsclTheoGioDTO.setReject(proNSCL.getReject1h());
                break;
            case C_2H:
                nsclTheoGioDTO.setQty(proNSCL.getQty2h());
                nsclTheoGioDTO.setFix(proNSCL.getFix2h());
                nsclTheoGioDTO.setMan(proNSCL.getMan2h());
                nsclTheoGioDTO.setReject(proNSCL.getReject2h());
                break;
            case C_3H:
                nsclTheoGioDTO.setQty(proNSCL.getQty3h());
                nsclTheoGioDTO.setFix(proNSCL.getFix3h());
                nsclTheoGioDTO.setMan(proNSCL.getMan3h());
                nsclTheoGioDTO.setReject(proNSCL.getReject3h());
                break;
            case C_4H:
                nsclTheoGioDTO.setQty(proNSCL.getQty4h());
                nsclTheoGioDTO.setFix(proNSCL.getFix4h());
                nsclTheoGioDTO.setMan(proNSCL.getMan4h());
                nsclTheoGioDTO.setReject(proNSCL.getReject4h());
                break;
            case C_5H:
                nsclTheoGioDTO.setQty(proNSCL.getQty5h());
                nsclTheoGioDTO.setFix(proNSCL.getFix5h());
                nsclTheoGioDTO.setMan(proNSCL.getMan5h());
                nsclTheoGioDTO.setReject(proNSCL.getReject5h());
                break;
            case C_6H:
                nsclTheoGioDTO.setQty(proNSCL.getQty6h());
                nsclTheoGioDTO.setFix(proNSCL.getFix6h());
                nsclTheoGioDTO.setMan(proNSCL.getMan6h());
                nsclTheoGioDTO.setReject(proNSCL.getReject6h());
                break;
            case C_7H:
                nsclTheoGioDTO.setQty(proNSCL.getQty7h());
                nsclTheoGioDTO.setFix(proNSCL.getFix7h());
                nsclTheoGioDTO.setMan(proNSCL.getMan7h());
                nsclTheoGioDTO.setReject(proNSCL.getReject7h());
                break;
            case C_8H:
                nsclTheoGioDTO.setQty(proNSCL.getQty8h());
                nsclTheoGioDTO.setFix(proNSCL.getFix8h());
                nsclTheoGioDTO.setMan(proNSCL.getMan8h());
                nsclTheoGioDTO.setReject(proNSCL.getReject8h());
                break;
            case C_9H:
                nsclTheoGioDTO.setQty(proNSCL.getQty9h());
                nsclTheoGioDTO.setFix(proNSCL.getFix9h());
                nsclTheoGioDTO.setMan(proNSCL.getMan9h());
                nsclTheoGioDTO.setReject(proNSCL.getReject9h());
                break;
            case C_10H:
                nsclTheoGioDTO.setQty(proNSCL.getQty10h());
                nsclTheoGioDTO.setFix(proNSCL.getFix10h());
                nsclTheoGioDTO.setMan(proNSCL.getMan10h());
                nsclTheoGioDTO.setReject(proNSCL.getReject10h());
                break;
            case C_11H:
                nsclTheoGioDTO.setQty(proNSCL.getQty11h());
                nsclTheoGioDTO.setFix(proNSCL.getFix11h());
                nsclTheoGioDTO.setMan(proNSCL.getMan11h());
                nsclTheoGioDTO.setReject(proNSCL.getReject11h());
                break;
            case C_12H:
                nsclTheoGioDTO.setQty(proNSCL.getQty12h());
                nsclTheoGioDTO.setFix(proNSCL.getFix12h());
                nsclTheoGioDTO.setMan(proNSCL.getMan12h());
                nsclTheoGioDTO.setReject(proNSCL.getReject12h());
                break;
            case C_13H:
                nsclTheoGioDTO.setQty(proNSCL.getQty13h());
                nsclTheoGioDTO.setFix(proNSCL.getFix13h());
                nsclTheoGioDTO.setMan(proNSCL.getMan13h());
                nsclTheoGioDTO.setReject(proNSCL.getReject13h());
                break;
            case C_14H:
                nsclTheoGioDTO.setQty(proNSCL.getQty14h());
                nsclTheoGioDTO.setFix(proNSCL.getFix14h());
                nsclTheoGioDTO.setMan(proNSCL.getMan14h());
                nsclTheoGioDTO.setReject(proNSCL.getReject14h());
                break;
        }
        nsclTheoGioDTO.setGioLamViecDTO(gioLam);
        computeQtyAndErrorRate(nsclTheoGioDTO, nsclTheoGioDTO.getQty(), nsclTheoGioDTO.getReject());
        nsclTheoGioDTO.setChiTietLoiDTOS(nsclChiTietLoiDtos);
        return nsclTheoGioDTO;
    }

    /**
     * Convert ProNSCL to NSCLTheoGioDTO với tổng lũy kế từ tất cả các khung giờ
     * Tính tổng: qty = qty1h + qty2h + ... + qty14h
     *           man = man1h + man2h + ... + man14h
     *           fix = fix1h + fix2h + ... + fix14h
     *           reject = reject1h + reject2h + ... + reject14h
     */
    default NSCLTheoGioDTO toDtoLuyKe(ProNSCL proNSCL, List<ProNSCLChiTietLoiDTO> nsclChiTietLoiDtos, ProGioLamViecDTO gioLamViecDTO) {
        NSCLTheoGioDTO nsclTheoGioDTO = new NSCLTheoGioDTO();
        nsclTheoGioDTO.setId(proNSCL.getId());

        // Tính tổng lũy kế cho tất cả các khung giờ (1h đến 14h)
        Integer totalQty = sumNullable(proNSCL.getQty1h(), proNSCL.getQty2h(), proNSCL.getQty3h(), proNSCL.getQty4h(),
                proNSCL.getQty5h(), proNSCL.getQty6h(), proNSCL.getQty7h(), proNSCL.getQty8h(),
                proNSCL.getQty9h(), proNSCL.getQty10h(), proNSCL.getQty11h(), proNSCL.getQty12h(),
                proNSCL.getQty13h(), proNSCL.getQty14h());

        Integer totalMan = sumNullable(proNSCL.getMan1h(), proNSCL.getMan2h(), proNSCL.getMan3h(), proNSCL.getMan4h(),
                proNSCL.getMan5h(), proNSCL.getMan6h(), proNSCL.getMan7h(), proNSCL.getMan8h(),
                proNSCL.getMan9h(), proNSCL.getMan10h(), proNSCL.getMan11h(), proNSCL.getMan12h(),
                proNSCL.getMan13h(), proNSCL.getMan14h());

        Integer totalFix = sumNullable(proNSCL.getFix1h(), proNSCL.getFix2h(), proNSCL.getFix3h(), proNSCL.getFix4h(),
                proNSCL.getFix5h(), proNSCL.getFix6h(), proNSCL.getFix7h(), proNSCL.getFix8h(),
                proNSCL.getFix9h(), proNSCL.getFix10h(), proNSCL.getFix11h(), proNSCL.getFix12h(),
                proNSCL.getFix13h(), proNSCL.getFix14h());

        Integer totalReject = sumNullable(proNSCL.getReject1h(), proNSCL.getReject2h(), proNSCL.getReject3h(), proNSCL.getReject4h(),
                proNSCL.getReject5h(), proNSCL.getReject6h(), proNSCL.getReject7h(), proNSCL.getReject8h(),
                proNSCL.getReject9h(), proNSCL.getReject10h(), proNSCL.getReject11h(), proNSCL.getReject12h(),
                proNSCL.getReject13h(), proNSCL.getReject14h());

        nsclTheoGioDTO.setQty(totalQty);
        nsclTheoGioDTO.setMan(totalMan);
        nsclTheoGioDTO.setFix(totalFix);
        nsclTheoGioDTO.setReject(totalReject);

        // Tính errorRate và qualifiedRate dựa trên tổng lũy kế
        // Tổng số lượng đã kiểm tra = man + fix + reject
        int totalManVal = totalMan != null ? totalMan : 0;
        int totalFixVal = totalFix != null ? totalFix : 0;
        int totalRejectVal = totalReject != null ? totalReject : 0;
        int totalChecked = totalManVal + totalFixVal + totalRejectVal;

        if (totalChecked > 0) {
            nsclTheoGioDTO.setErrorRate((float) totalRejectVal / (float) totalChecked * 100);
            nsclTheoGioDTO.setQualifiedRate((float) totalManVal / (float) totalChecked * 100);
        } else {
            nsclTheoGioDTO.setErrorRate(0f);
            nsclTheoGioDTO.setQualifiedRate(0f);
        }

        nsclTheoGioDTO.setChiTietLoiDTOS(nsclChiTietLoiDtos);
        // Set giờ làm việc hiện tại để frontend biết đang ở khung giờ nào
        nsclTheoGioDTO.setGioLamViecDTO(gioLamViecDTO);

        return nsclTheoGioDTO;
    }

    /**
     * Helper method để tính tổng các giá trị Integer (null-safe)
     */
    default Integer sumNullable(Integer... values) {
        int sum = 0;
        for (Integer value : values) {
            if (value != null) {
                sum += value;
            }
        }
        return sum;
    }

    default ProNSCL fromId(Long id) {
        if (id == null) {
            return null;
        }
        ProNSCL proNSCL = new ProNSCL();
        proNSCL.setId(id);
        return proNSCL;
    }
}
