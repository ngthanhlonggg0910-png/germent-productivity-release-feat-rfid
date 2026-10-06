USE [eGMF]
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

-- Bổ sung cột khsx_id, nscl_id, po cho bảng mapping RFID <-> mã cây.
-- BarcodeTachCay có thể gắn nhiều PO, nên khi map RFID ta lưu đúng PO (và KHSX/NSCL)
-- để proc getManufacturePlanOnCheckPointTodayByRfid_PoFix lọc đúng kế hoạch.
-- Idempotent: chỉ thêm cột nếu chưa tồn tại.

IF COL_LENGTH('eGMF.dbo.CUTTING_TemBarcode_TachCay_RFID_Mapping', 'khsx_id') IS NULL
    ALTER TABLE eGMF.dbo.CUTTING_TemBarcode_TachCay_RFID_Mapping ADD khsx_id bigint NULL;
GO

IF COL_LENGTH('eGMF.dbo.CUTTING_TemBarcode_TachCay_RFID_Mapping', 'nscl_id') IS NULL
    ALTER TABLE eGMF.dbo.CUTTING_TemBarcode_TachCay_RFID_Mapping ADD nscl_id bigint NULL;
GO

IF COL_LENGTH('eGMF.dbo.CUTTING_TemBarcode_TachCay_RFID_Mapping', 'po') IS NULL
    ALTER TABLE eGMF.dbo.CUTTING_TemBarcode_TachCay_RFID_Mapping ADD po nvarchar(500) NULL;
GO

IF COL_LENGTH('eGMF.dbo.CUTTING_TemBarcode_TachCay_RFID_Mapping', 'productcode') IS NULL
    ALTER TABLE eGMF.dbo.CUTTING_TemBarcode_TachCay_RFID_Mapping ADD productcode nvarchar(200) NULL;
GO

-- Hỗ trợ lookup nhanh PO theo RFID trong proc (tùy chọn).
IF NOT EXISTS (
    SELECT 1 FROM sys.indexes
    WHERE name = 'IX_CUTTING_TemBarcode_TachCay_RFID_Mapping_po'
      AND object_id = OBJECT_ID('eGMF.dbo.CUTTING_TemBarcode_TachCay_RFID_Mapping')
)
    CREATE INDEX IX_CUTTING_TemBarcode_TachCay_RFID_Mapping_po
        ON eGMF.dbo.CUTTING_TemBarcode_TachCay_RFID_Mapping (po);
GO
