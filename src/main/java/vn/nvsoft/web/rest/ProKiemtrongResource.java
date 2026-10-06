package vn.nvsoft.web.rest;

import java.util.List;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vn.nvsoft.service.ProKiemtrongService;
import vn.nvsoft.service.dto.CreateKiemTrongChiTietLoiRequest;
import vn.nvsoft.service.dto.KiemTrongSessionDTO;
import vn.nvsoft.service.dto.MarkKiemTrongSuaRequest;
import vn.nvsoft.service.dto.ProKiemtrongChiTietLoiDTO;

@RestController
@RequestMapping("/api")
public class ProKiemtrongResource {

    private final Logger log = LoggerFactory.getLogger(ProKiemtrongResource.class);

    private final ProKiemtrongService proKiemtrongService;

    public ProKiemtrongResource(ProKiemtrongService proKiemtrongService) {
        this.proKiemtrongService = proKiemtrongService;
    }

    @GetMapping("/pro-kiem-trong/{checkpointId}/{khsxId}")
    public KiemTrongSessionDTO getSession(
        @PathVariable Long checkpointId,
        @PathVariable Long khsxId) {
        log.debug("REST request to get Kiểm trong session checkpoint={}, khsx={}", checkpointId, khsxId);
        return proKiemtrongService.getSession(checkpointId, khsxId);
    }

    @GetMapping("/pro-kiem-trong/{checkpointId}/{khsxId}/chi-tiet-lois")
    public List<ProKiemtrongChiTietLoiDTO> getChiTietLois(
        @PathVariable Long checkpointId,
        @PathVariable Long khsxId) {
        return proKiemtrongService.getSession(checkpointId, khsxId).getChiTietLois();
    }

    @PostMapping("/pro-kiem-trong/chi-tiet-lois")
    public ResponseEntity<List<ProKiemtrongChiTietLoiDTO>> createChiTietLoi(
        @Valid @RequestBody CreateKiemTrongChiTietLoiRequest request) {
        log.debug("REST request to create Kiểm trong chi tiết lỗi: {}", request);
        List<ProKiemtrongChiTietLoiDTO> result = proKiemtrongService.addChiTietLoi(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @DeleteMapping("/pro-kiem-trong/chi-tiet-lois/{id}")
    public ResponseEntity<Void> deleteChiTietLoi(@PathVariable Long id) {
        log.debug("REST request to delete Kiểm trong chi tiết lỗi: {}", id);
        proKiemtrongService.deleteChiTietLoi(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/pro-kiem-trong/{checkpointId}/{khsxId}/loi-count-by-rfid")
    public ResponseEntity<Integer> countLoiByRfid(
        @PathVariable Long checkpointId,
        @PathVariable Long khsxId,
        @RequestParam String rfidDisplayName) {
        int count = proKiemtrongService.countLoiByRfid(checkpointId, khsxId, rfidDisplayName);
        return ResponseEntity.ok(count);
    }

    @DeleteMapping("/pro-kiem-trong/{checkpointId}/{khsxId}/loi-by-rfid")
    public ResponseEntity<Integer> deleteLoiByRfid(
        @PathVariable Long checkpointId,
        @PathVariable Long khsxId,
        @RequestParam String rfidDisplayName) {
        log.debug("REST request to delete Kiểm trong lỗi by RFID={}, checkpoint={}, khsx={}",
            rfidDisplayName, checkpointId, khsxId);
        int deleted = proKiemtrongService.deleteLoiByRfid(checkpointId, khsxId, rfidDisplayName);
        return ResponseEntity.ok(deleted);
    }

    @PostMapping("/pro-kiem-trong/sua")
    public ResponseEntity<KiemTrongSessionDTO> markSuaByRfid(
        @Valid @RequestBody MarkKiemTrongSuaRequest request) {
        log.debug("REST request to mark Kiểm trong SUA: {}", request.getRfidDisplayName());
        KiemTrongSessionDTO session = proKiemtrongService.markSuaByRfid(request);
        return ResponseEntity.ok(session);
    }
}
