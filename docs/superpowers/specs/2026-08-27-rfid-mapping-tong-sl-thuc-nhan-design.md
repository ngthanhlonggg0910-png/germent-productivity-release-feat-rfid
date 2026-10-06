# Design: Ràng mapping RFID theo TongSLThucNhan (tem BTP)

**Date:** 2026-08-27  
**Scope:** Backend HiPro — luồng mapping RFID (`TemBarcodeTachCay*`) only  
**Out of scope:** NSCL validate Đạt/Sửa/Lỗi (`Summary_PO_QuetNhan` giữ nguyên); mobile bắt buộc đổi field (giữ tên API cũ)

## Decisions

| ID | Choice |
|----|--------|
| C1 | Trần BTP khi map RFID = `TongSLThucNhan`, **không** dùng `Summary_PO_QuetNhan.Quantity` |
| S1 | `TongSLThucNhan` **có lọc `Code` mã cây**; so với `COUNT(map theo Code)` |
| P1 | Chỉ mapping RFID |

## Rules

When inserting RFID mapping, all must hold:

1. RFID not already mapped (`RFID` / `RFID_Barcode` / `RFID_Hex`)
2. Tree code exists in `CUTTING_TemBarcode_TachCay`
3. `COUNT(map by Code) < SoLopTrai` (existing; BTH still × bàn may)
4. `COUNT(map by Code) < TongSLThucNhan` (**replaces** Summary_PO check)

Effective ceiling per tree: `MIN(SoLopTrai, TongSLThucNhan)`.

If `TongSLThucNhan` is null or ≤ 0 → block with `btp_chua_quet_hoac_quantity_0`.

## TongSLThucNhan query

```sql
SELECT SUM(ISNULL(bcct.SoLuongDaNhan, 0)) AS TongSLThucNhan
FROM eGMF.dbo.CUTTING_PhieuCapBTP_BarcodeChiTiet bcct
INNER JOIN eGMF.dbo.CUTTING_PhieuCapBTP cap ON bcct.SoPhieuCapBTP = cap.SoPhieuCapBTP
WHERE ISNULL(bcct.TraBTP, 0) = 0
  AND cap.ProductCode = :productCode   -- HiPro: sp.ma_san_pham
  AND cap.TenCum = :tenCum             -- HiPro: chuyen.ten
  AND bcct.PO = :po                    -- HiPro: sp.po
  AND cap.TenPhanXuong = :tenPhanXuong -- HiPro: 'XN' + RIGHT(nhamay.ten, 1)
  AND EXISTS (
    SELECT 1 FROM eGMF.dbo.CUTTING_TemBarcode_TachCay tc
    WHERE tc.Barcode = bcct.TemBarcodeBTP
      AND tc.Code = :code              -- barcodeTachCay
  )
```

### HiPro → eGMF parameter map

| eGMF filter | HiPro source (via `khsxId`) |
|-------------|-----------------------------|
| `ProductCode` | `pro_san_pham.ma_san_pham` |
| `TenCum` | `pro_chuyen.ten` |
| `PO` | `pro_san_pham.po` |
| `TenPhanXuong` | `'XN' + RIGHT(pro_nha_may.ten, 1)` (e.g. `Xí Nghiệp 01` → `XN1`) |
| `Code` | `barcodeTachCay` argument |

Implementation may inline the KHSX joins inside the repository query so callers pass only `(code, khsxId)`.

## Sample data (verified 2026-08-27)

| Metric | Value |
|--------|-------|
| Code | `BTP2608051515-6` |
| ProductCode / TenCum / PO / TenPhanXuong | `357784` / `15` / `4525042186` / `XN1` |
| `TongSLThucNhan` | **85** |
| `SoLopTrai` | 15 |
| Mapped by tree | 15 |
| `Summary_PO` ToMay=15 Quantity | 1420 (unused for RFID after change) |

## Components to change

### Repository

- Add `getTongSLThucNhanByCodeAndKhsx(code, khsxId)` (native query joining HiPro KHSX + eGMF BTP tables as above). Prefer placing on `TemBarcodeTachCayRepository` (or `ProNSCLRepository` only if reuse is needed; RFID path should not call Summary for this limit).
- Update `insertMappingIfAvailable`:
  - Remove both `Summary_PO_QuetNhan` subqueries (limit + `> 0` gate).
  - Add atomic condition: mapped-by-Code count `< TongSLThucNhan` subquery (same filters), and TongSL `> 0`.

### Service (`TemBarcodeTachCayService`)

- `canMapMoreRfid(code, khsxId)`: after SoLopTrai check, compare mapped-by-Code to `TongSLThucNhan`, not `getQuantityLimitFromSummaryPO`.
- `resolveCannotMapErrorCode` / failed-insert classification: same swap.
- `getQuantityLimitBtp(code, khsxId)` for RFID can-map API: return TongSL for that pair (replace the khsx-only Summary overload on the RFID path).

### Resource (`TemBarcodeTachCayResource`)

- `can-map` response:
  - `quantityLimitBtp` = `TongSLThucNhan` for the tree
  - Remaining / mapped figures compare against **mapped-by-Code** (keep existing JSON keys where possible for mobile compatibility; optionally add `soLuongDaMapMaCay` / `tongSlThucNhan` as clearer aliases)

### Unchanged

- `ProNSCLRepository.getQuantityLimitFromSummaryPO`
- `ProNSCLServiceImpl.validateQuantityLimit` and related NSCL APIs
- Mobile app (no required change if field names preserved)

## Error codes

| Condition | errorCode | User-facing meaning |
|-----------|-----------|---------------------|
| SoLopTrai full | `slot_macay_full` | Tree layer slot full |
| TongSL null/≤0 | `btp_chua_quet_hoac_quantity_0` | No received BTP qty for this tree context |
| mapped ≥ TongSL | `vuot_gioi_han_summary_po` | Exceeded received BTP qty for tree (reuse code; message text should say nhận tem BTP / mã cây) |
| Missing khsxId | `khsx_required` | Unchanged |

## Edge cases

- `TongSL > SoLopTrai` → SoLopTrai binds first
- `TongSL < SoLopTrai` → TongSL binds
- Multiple `bcct` rows for same barcode/Code → `SUM` as in sample query
- Concurrent maps → only atomic `insertMappingIfAvailable` enforces both ceilings

## Test plan

1. Query `(code, khsxId)` for sample tree → TongSL = 85
2. `canMapMoreRfid`: SoLopTrai available but TongSL = 0 → false + `btp_chua_quet_hoac_quantity_0`
3. Atomic insert cannot exceed TongSL or SoLopTrai
4. Regression: NSCL `getQuantityLimitFromSummaryPO` still returns Summary quantity (e.g. 1420 for sample KHSX)

## Approach

Atomic SQL + matching service soft-checks (approach 1). Soft-check-only rejected due to race risk.
