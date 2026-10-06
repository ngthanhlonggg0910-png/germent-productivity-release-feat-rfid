# RFID Mapping TongSLThucNhan Implementation Plan

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** Replace RFID mapping’s KHSX BTP ceiling (`Summary_PO_QuetNhan.Quantity`) with per-tree `TongSLThucNhan` from eGMF phiếu cấp BTP, while keeping `SoLopTrai` and leaving NSCL Summary checks unchanged.

**Architecture:** Add a native repository query that joins HiPro KHSX → eGMF `CUTTING_PhieuCapBTP*` + `CUTTING_TemBarcode_TachCay` to compute `SUM(SoLuongDaNhan)` for `(code, khsxId)`. Soft-checks in `TemBarcodeTachCayService` and the atomic `insertMappingIfAvailable` SQL both use that limit against `COUNT(map by Code)`.

**Tech Stack:** Java 8+, Spring Data JPA native queries, JUnit 5 + Mockito + AssertJ, SQL Server (eGMF + HiPro).

## Global Constraints

- Scope = RFID mapping path only (`TemBarcodeTachCayRepository` / `Service` / `Resource`); do not change `ProNSCLServiceImpl.validateQuantityLimit` or `getQuantityLimitFromSummaryPO`.
- Decisions: C1 (replace Summary with TongSL), S1 (filter by Code; compare mapped-by-Code), P1 (RFID only).
- `TenPhanXuong` = `'XN' + RIGHT(pro_nha_may.ten, 1)`.
- Reuse error codes `btp_chua_quet_hoac_quantity_0` and `vuot_gioi_han_summary_po`; update user-facing messages to say nhận tem BTP / mã cây.
- Keep can-map JSON keys `quantityLimitBtp`, `soLuongDaMapKhsx`, `soLuongConLaiBtp` for mobile; values now reflect TongSL vs mapped-by-Code.
- Do not commit unless the user explicitly asks (workspace rule overrides plan commit steps — treat commit steps as optional).

---

## File structure

| File | Responsibility |
|------|----------------|
| `TemBarcodeTachCaySqlFragments.java` | Shared SQL fragment for TongSL subquery (optional DRY for insert + select) |
| `TemBarcodeTachCayRepository.java` | `getTongSLThucNhanByCodeAndKhsx`; rewrite `insertMappingIfAvailable` BTP gate |
| `TemBarcodeTachCayService.java` | Soft-check + error classification + `getQuantityLimitBtp(code, khsxId)`; drop `ProNSCLRepository` dependency |
| `TemBarcodeTachCayResource.java` | can-map / map error messages + limit fields from TongSL |
| `TemBarcodeTachCayServiceTest.java` | Unit tests for can-map / resolve error with mocked repo |

---

### Task 1: Repository — `getTongSLThucNhanByCodeAndKhsx`

**Files:**
- Modify: `src/main/java/vn/nvsoft/repository/egmf/TemBarcodeTachCaySqlFragments.java`
- Modify: `src/main/java/vn/nvsoft/repository/egmf/TemBarcodeTachCayRepository.java`
- Test: manual SQL against sample (step below); automated coverage in Task 3 via mock

**Interfaces:**
- Consumes: none new
- Produces: `Integer getTongSLThucNhanByCodeAndKhsx(String code, Long khsxId)`

- [ ] **Step 1: Add SQL fragment constant (optional but preferred)**

In `TemBarcodeTachCaySqlFragments.java`, add a documented constant describing the TongSL expression body used inside larger queries. Keep the full `@Query` on the repository readable even if fragment is only a comment + shared join snippet.

Preferred full select for the repository method:

```java
@Query(nativeQuery = true, value =
    "SELECT SUM(ISNULL(bcct.SoLuongDaNhan, 0)) " +
    "FROM eGMF.dbo.CUTTING_PhieuCapBTP_BarcodeChiTiet bcct " +
    "INNER JOIN eGMF.dbo.CUTTING_PhieuCapBTP cap ON bcct.SoPhieuCapBTP = cap.SoPhieuCapBTP " +
    "INNER JOIN pro_ke_hoach_san_xuat khsx ON khsx.id = :khsxId " +
    "INNER JOIN pro_chuyen chuyen ON khsx.pro_chuyen_id = chuyen.id " +
    "INNER JOIN pro_nha_may nhamay ON chuyen.pro_nha_may_id = nhamay.id " +
    "INNER JOIN pro_san_pham sp ON khsx.pro_san_pham_id = sp.id " +
    "WHERE ISNULL(bcct.TraBTP, 0) = 0 " +
    "  AND cap.ProductCode = sp.ma_san_pham " +
    "  AND cap.TenCum = chuyen.ten " +
    "  AND bcct.PO = sp.po " +
    "  AND cap.TenPhanXuong = 'XN' + RIGHT(nhamay.ten, 1) " +
    "  AND EXISTS ( " +
    "      SELECT 1 FROM eGMF.dbo.CUTTING_TemBarcode_TachCay tc " +
    "      WHERE tc.Barcode = bcct.TemBarcodeBTP " +
    "        AND tc.Code = :code " +
    "  )")
Integer getTongSLThucNhanByCodeAndKhsx(@Param("code") String code, @Param("khsxId") Long khsxId);
```

Place this method near `countMappedRfidByBarcode` / before `insertMappingIfAvailable`.

- [ ] **Step 2: Verify SQL against sample DB**

Run (credentials as provided by user / env):

```bash
sqlcmd -S 10.8.0.72 -d HiPro -U netviet1 -P "netviet@123" -Q "
SELECT SUM(ISNULL(bcct.SoLuongDaNhan, 0)) AS TongSLThucNhan
FROM eGMF.dbo.CUTTING_PhieuCapBTP_BarcodeChiTiet bcct
INNER JOIN eGMF.dbo.CUTTING_PhieuCapBTP cap ON bcct.SoPhieuCapBTP = cap.SoPhieuCapBTP
INNER JOIN pro_ke_hoach_san_xuat khsx ON khsx.id = 275115
INNER JOIN pro_chuyen chuyen ON khsx.pro_chuyen_id = chuyen.id
INNER JOIN pro_nha_may nhamay ON chuyen.pro_nha_may_id = nhamay.id
INNER JOIN pro_san_pham sp ON khsx.pro_san_pham_id = sp.id
WHERE ISNULL(bcct.TraBTP, 0) = 0
  AND cap.ProductCode = sp.ma_san_pham
  AND cap.TenCum = chuyen.ten
  AND bcct.PO = sp.po
  AND cap.TenPhanXuong = 'XN' + RIGHT(nhamay.ten, 1)
  AND EXISTS (
    SELECT 1 FROM eGMF.dbo.CUTTING_TemBarcode_TachCay tc
    WHERE tc.Barcode = bcct.TemBarcodeBTP AND tc.Code = 'BTP2608051515-6'
  );
"
```

Expected: `TongSLThucNhan = 85` (same as hardcoded ProductCode/TenCum/PO/XN1 query).

If a listed `khsxId` does not match that product/PO/chuyền, pick any of `273373,273609,274188,274667,275115` that joins to `357784` / `4525042186` / `15`.

- [ ] **Step 3: Optional commit** (only if user asked)

```bash
git add src/main/java/vn/nvsoft/repository/egmf/TemBarcodeTachCayRepository.java src/main/java/vn/nvsoft/repository/egmf/TemBarcodeTachCaySqlFragments.java
git commit -m "feat(rfid): add TongSLThucNhan query by code and khsx"
```

---

### Task 2: Atomic insert — replace Summary_PO gate with TongSL

**Files:**
- Modify: `src/main/java/vn/nvsoft/repository/egmf/TemBarcodeTachCayRepository.java` (`insertMappingIfAvailable`)

**Interfaces:**
- Consumes: TongSL filter logic from Task 1 (inline same joins; params already include `:barcodeTachCay`, `:khsxId`)
- Produces: updated `insertMappingIfAvailable` that enforces SoLopTrai + TongSL (no Summary_PO)

- [ ] **Step 1: Replace the two Summary_PO blocks in `insertMappingIfAvailable`**

Keep: exists tree, not duplicate RFID, mapped-by-Code `< SoLopTrai`.

Remove the block that does:

```sql
(SELECT COUNT(*) ... WHERE m3.khsx_id = :khsxId)
  < (SELECT DISTINCT TOP 1 qn.Quantity ... Summary_PO_QuetNhan ...)
AND (SELECT DISTINCT TOP 1 qn.Quantity ... ) > 0
```

Add instead (same TongSL subquery twice — once for `<`, once for `> 0` — mirroring previous Summary pattern):

```java
"    AND ( " +
"        SELECT COUNT(*) FROM eGMF.dbo.CUTTING_TemBarcode_TachCay_RFID_Mapping m3 WITH (UPDLOCK, HOLDLOCK) " +
"        WHERE m3.BarcodeTachCay = :barcodeTachCay " +
"    ) < ( " +
"        SELECT ISNULL(SUM(ISNULL(bcct.SoLuongDaNhan, 0)), 0) " +
"        FROM eGMF.dbo.CUTTING_PhieuCapBTP_BarcodeChiTiet bcct " +
"        INNER JOIN eGMF.dbo.CUTTING_PhieuCapBTP cap ON bcct.SoPhieuCapBTP = cap.SoPhieuCapBTP " +
"        INNER JOIN pro_ke_hoach_san_xuat khsx ON khsx.id = :khsxId " +
"        INNER JOIN pro_chuyen chuyen ON khsx.pro_chuyen_id = chuyen.id " +
"        INNER JOIN pro_nha_may nhamay ON chuyen.pro_nha_may_id = nhamay.id " +
"        INNER JOIN pro_san_pham sp ON khsx.pro_san_pham_id = sp.id " +
"        WHERE ISNULL(bcct.TraBTP, 0) = 0 " +
"          AND cap.ProductCode = sp.ma_san_pham " +
"          AND cap.TenCum = chuyen.ten " +
"          AND bcct.PO = sp.po " +
"          AND cap.TenPhanXuong = 'XN' + RIGHT(nhamay.ten, 1) " +
"          AND EXISTS ( " +
"              SELECT 1 FROM eGMF.dbo.CUTTING_TemBarcode_TachCay tc3 " +
"              WHERE tc3.Barcode = bcct.TemBarcodeBTP AND tc3.Code = :barcodeTachCay " +
"          ) " +
"    ) " +
"    AND ( " +
"        SELECT ISNULL(SUM(ISNULL(bcct2.SoLuongDaNhan, 0)), 0) " +
"        FROM eGMF.dbo.CUTTING_PhieuCapBTP_BarcodeChiTiet bcct2 " +
"        INNER JOIN eGMF.dbo.CUTTING_PhieuCapBTP cap2 ON bcct2.SoPhieuCapBTP = cap2.SoPhieuCapBTP " +
"        INNER JOIN pro_ke_hoach_san_xuat khsx2 ON khsx2.id = :khsxId " +
"        INNER JOIN pro_chuyen chuyen2 ON khsx2.pro_chuyen_id = chuyen2.id " +
"        INNER JOIN pro_nha_may nhamay2 ON chuyen2.pro_nha_may_id = nhamay2.id " +
"        INNER JOIN pro_san_pham sp2 ON khsx2.pro_san_pham_id = sp2.id " +
"        WHERE ISNULL(bcct2.TraBTP, 0) = 0 " +
"          AND cap2.ProductCode = sp2.ma_san_pham " +
"          AND cap2.TenCum = chuyen2.ten " +
"          AND bcct2.PO = sp2.po " +
"          AND cap2.TenPhanXuong = 'XN' + RIGHT(nhamay2.ten, 1) " +
"          AND EXISTS ( " +
"              SELECT 1 FROM eGMF.dbo.CUTTING_TemBarcode_TachCay tc4 " +
"              WHERE tc4.Barcode = bcct2.TemBarcodeBTP AND tc4.Code = :barcodeTachCay " +
"          ) " +
"    ) > 0"
```

Update the JavaDoc of `insertMappingIfAvailable` to say trần BTP = `TongSLThucNhan` theo mã cây, not Summary_PO.

- [ ] **Step 2: Compile-check repository**

Run:

```bash
cd d:/Work/NVSOFT/germent-productivity
./mvnw -DskipTests compile -q
```

Expected: BUILD SUCCESS (or at least no errors in `TemBarcodeTachCayRepository`).

- [ ] **Step 3: Optional commit** (only if user asked)

```bash
git add src/main/java/vn/nvsoft/repository/egmf/TemBarcodeTachCayRepository.java
git commit -m "feat(rfid): enforce TongSLThucNhan in atomic RFID insert"
```

---

### Task 3: Service soft-checks + unit tests

**Files:**
- Modify: `src/main/java/vn/nvsoft/service/egmf/TemBarcodeTachCayService.java`
- Create: `src/test/java/vn/nvsoft/service/egmf/TemBarcodeTachCayServiceTest.java`

**Interfaces:**
- Consumes: `TemBarcodeTachCayRepository.getTongSLThucNhanByCodeAndKhsx`, `countMappedRfidByBarcode`, `getByCode` path
- Produces:
  - `boolean canMapMoreRfid(String code, Long khsxId)` — uses TongSL vs mapped-by-Code
  - `String resolveCannotMapErrorCode(String code, Long khsxId)`
  - `Integer getQuantityLimitBtp(String code, Long khsxId)`
  - Remove `ProNSCLRepository` field/ctor dependency from this service

- [ ] **Step 1: Write failing unit tests**

Create `TemBarcodeTachCayServiceTest.java`:

```java
package vn.nvsoft.service.egmf;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import vn.nvsoft.repository.egmf.TemBarcodeTachCayRepository;
import vn.nvsoft.service.viewmodel.TemBarcodeTachCayViewModel;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TemBarcodeTachCayServiceTest {

    @Mock
    private TemBarcodeTachCayRepository repository;

    private TemBarcodeTachCayService service;

    @BeforeEach
    void setUp() {
        service = new TemBarcodeTachCayService(repository);
    }

    private void stubTree(String code, int soLopTrai, int soLuongDaMap) {
        TemBarcodeTachCayViewModel vm = org.mockito.Mockito.mock(TemBarcodeTachCayViewModel.class);
        when(vm.getCode()).thenReturn(code);
        when(vm.getSoLopTrai()).thenReturn(soLopTrai);
        when(vm.getSoLuongDaMap()).thenReturn(soLuongDaMap);
        when(repository.findByCode(code)).thenReturn(Collections.singletonList(vm));
    }

    @Test
    void canMapMoreRfid_false_whenTongSlZero() {
        stubTree("BTP1-1", 15, 0);
        when(repository.getTongSLThucNhanByCodeAndKhsx("BTP1-1", 1L)).thenReturn(0);
        assertThat(service.canMapMoreRfid("BTP1-1", 1L)).isFalse();
        assertThat(service.resolveCannotMapErrorCode("BTP1-1", 1L))
            .isEqualTo(TemBarcodeTachCayService.ERROR_BTP_CHUA_QUET);
    }

    @Test
    void canMapMoreRfid_false_whenMappedReachesTongSl() {
        stubTree("BTP1-1", 100, 85);
        when(repository.getTongSLThucNhanByCodeAndKhsx("BTP1-1", 1L)).thenReturn(85);
        when(repository.countMappedRfidByBarcode("BTP1-1")).thenReturn(85);
        assertThat(service.canMapMoreRfid("BTP1-1", 1L)).isFalse();
        assertThat(service.resolveCannotMapErrorCode("BTP1-1", 1L))
            .isEqualTo(TemBarcodeTachCayService.ERROR_VUOT_SUMMARY_PO);
    }

    @Test
    void canMapMoreRfid_true_whenUnderBothCeilings() {
        stubTree("BTP1-1", 15, 5);
        when(repository.getTongSLThucNhanByCodeAndKhsx("BTP1-1", 1L)).thenReturn(85);
        when(repository.countMappedRfidByBarcode("BTP1-1")).thenReturn(5);
        assertThat(service.canMapMoreRfid("BTP1-1", 1L)).isTrue();
    }

    @Test
    void getQuantityLimitBtp_returnsTongSl() {
        when(repository.getTongSLThucNhanByCodeAndKhsx("BTP1-1", 1L)).thenReturn(85);
        assertThat(service.getQuantityLimitBtp("BTP1-1", 1L)).isEqualTo(85);
    }
}
```

Adjust ViewModel stubbing if `convertToDTO` needs more getters (set lenient defaults for unused fields).

- [ ] **Step 2: Run tests — expect fail (method signatures / ProNSCL still present)**

```bash
./mvnw -Dtest=TemBarcodeTachCayServiceTest test
```

Expected: FAIL (ctor still requires `ProNSCLRepository`, or `getQuantityLimitBtp(String, Long)` missing).

- [ ] **Step 3: Implement service changes**

Replace Summary usage:

```java
public boolean canMapMoreRfid(String code, Long khsxId) {
    if (khsxId == null || !canMapMoreRfid(code)) {
        return false;
    }
    Integer limit = temBarcodeTachCayRepository.getTongSLThucNhanByCodeAndKhsx(code, khsxId);
    if (limit == null || limit <= 0) {
        return false;
    }
    Integer mapped = temBarcodeTachCayRepository.countMappedRfidByBarcode(code);
    int mappedCount = mapped != null ? mapped : 0;
    return mappedCount < limit;
}

public String resolveCannotMapErrorCode(String code, Long khsxId) {
    if (khsxId == null) {
        return ERROR_KHSX_REQUIRED;
    }
    if (!canMapMoreRfid(code)) {
        return ERROR_SLOT_MACAY_FULL;
    }
    Integer limit = temBarcodeTachCayRepository.getTongSLThucNhanByCodeAndKhsx(code, khsxId);
    if (limit == null || limit <= 0) {
        return ERROR_BTP_CHUA_QUET;
    }
    Integer mapped = temBarcodeTachCayRepository.countMappedRfidByBarcode(code);
    if ((mapped != null ? mapped : 0) >= limit) {
        return ERROR_VUOT_SUMMARY_PO;
    }
    return ERROR_MAPPING_BLOCKED;
}

public Integer getQuantityLimitBtp(String code, Long khsxId) {
    if (code == null || khsxId == null) {
        return null;
    }
    return temBarcodeTachCayRepository.getTongSLThucNhanByCodeAndKhsx(code, khsxId);
}
```

In `mapRfidToBarcode` failure classification, replace:

```java
Integer limit = proNSCLRepository.getQuantityLimitFromSummaryPO(khsxId);
...
Integer mapped = temBarcodeTachCayRepository.countMappedRfidByKhsxId(khsxId);
```

with TongSL + `countMappedRfidByBarcode(barcodeTachCay)`. Update exception messages:

- TongSL null/≤0: keep “BTP chưa được quét nhận…”
- mapped ≥ TongSL: `"Số lượng mapping RFID vượt quá số lượng nhận tem BTP của mã cây"`

Remove `ProNSCLRepository` import, field, and constructor parameter.

Delete old `getQuantityLimitBtp(Long khsxId)` or keep as deprecated wrapper that returns null / throws — prefer delete and fix Resource call sites in Task 4.

- [ ] **Step 4: Run tests — expect pass**

```bash
./mvnw -Dtest=TemBarcodeTachCayServiceTest,RfidGs1ValidatorTest test
```

Expected: PASS.

- [ ] **Step 5: Optional commit** (only if user asked)

```bash
git add src/main/java/vn/nvsoft/service/egmf/TemBarcodeTachCayService.java src/test/java/vn/nvsoft/service/egmf/TemBarcodeTachCayServiceTest.java
git commit -m "feat(rfid): soft-check TongSLThucNhan instead of Summary_PO"
```

---

### Task 4: Resource can-map / map messages + response fields

**Files:**
- Modify: `src/main/java/vn/nvsoft/web/rest/egmf/TemBarcodeTachCayResource.java`

**Interfaces:**
- Consumes: `getQuantityLimitBtp(code, khsxId)`, `countMappedRfid(code)` / service helpers
- Produces: can-map JSON with TongSL semantics; updated exceed messages

- [ ] **Step 1: Update `canMapMoreRfid` endpoint**

When `khsxId != null`:

```java
Integer quantityLimitBtp = temBarcodeTachCayService.getQuantityLimitBtp(code, khsxId);
Integer soLuongDaMapMaCay = data.getSoLuongDaMap() != null ? data.getSoLuongDaMap() : 0;
response.put("khsxId", khsxId);
response.put("quantityLimitBtp", quantityLimitBtp);
response.put("tongSlThucNhan", quantityLimitBtp);
response.put("soLuongDaMapKhsx", soLuongDaMapMaCay); // legacy key; now mapped-by-Code
response.put("soLuongDaMapMaCay", soLuongDaMapMaCay);
if (quantityLimitBtp != null && quantityLimitBtp > 0) {
    response.put("soLuongConLaiBtp", Math.max(0, quantityLimitBtp - soLuongDaMapMaCay));
} else {
    response.put("soLuongConLaiBtp", 0);
}
```

Update javadoc: trần BTP = TongSLThucNhan theo mã cây, not Summary_PO.

Change `ERROR_VUOT_SUMMARY_PO` message to:

`"Số lượng mapping RFID vượt quá số lượng nhận tem BTP của mã cây"`

Apply the same message string wherever this resource maps that errorCode (can-map + map endpoints ~lines 229–231 and ~382–445).

- [ ] **Step 2: Compile full module**

```bash
./mvnw -DskipTests compile -q
```

Expected: SUCCESS; no remaining calls to `getQuantityLimitBtp(Long)` or `TemBarcodeTachCayService(..., ProNSCLRepository)`.

- [ ] **Step 3: Optional commit** (only if user asked)

```bash
git add src/main/java/vn/nvsoft/web/rest/egmf/TemBarcodeTachCayResource.java
git commit -m "feat(rfid): expose TongSLThucNhan on can-map API"
```

---

### Task 5: Manual verification + regression note

**Files:** none (verification only)

- [ ] **Step 1: Confirm NSCL Summary path untouched**

```bash
rg "getQuantityLimitFromSummaryPO" src/main/java/vn/nvsoft
```

Expected: still present in `ProNSCLRepository` / `ProNSCLServiceImpl` / `ProNSCLResource`; **absent** from `TemBarcodeTachCayService`.

- [ ] **Step 2: Smoke can-map against running API (if server available)**

`GET /api/egmf/tem-barcode-tach-cay/BTP2608051515-6/can-map?khsxId=275115`

Expect roughly:

- `quantityLimitBtp` / `tongSlThucNhan` = 85
- `soLuongDaMap` / `soLuongDaMapMaCay` = 15
- `canMap` = false (SoLopTrai full) with `errorCode` = `SLOT_MACAY_FULL` (or equivalent constant value)

- [ ] **Step 3: Mark plan tasks done in this file** when implementation finishes

---

## Spec coverage (self-review)

| Spec requirement | Task |
|------------------|------|
| C1 replace Summary with TongSL | 2, 3 |
| S1 filter by Code; count by Code | 1–4 |
| P1 RFID only; NSCL untouched | 5 + Global Constraints |
| HiPro param map + XN+RIGHT | 1, 2 |
| Atomic insert | 2 |
| Soft-check + error codes | 3, 4 |
| can-map field semantics | 4 |
| Sample TongSL=85 verification | 1, 5 |
| Tests | 3 |

No placeholders left. Method names consistent: `getTongSLThucNhanByCodeAndKhsx`, `getQuantityLimitBtp(String, Long)`.
