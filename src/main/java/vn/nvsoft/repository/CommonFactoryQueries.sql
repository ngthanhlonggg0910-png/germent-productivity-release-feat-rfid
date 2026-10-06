
-- checkpoint của user
SELECT tkcp.ID             as tkcp_id,
       tkcp.tai_khoan      as tkcp_tai_khoan,
       tkcp.checked        as tkcp_checked,
       cp.ID               as checkpoint_id,
       cp.ten              as checkpoint_name,
       cp.pro_chuyen_id    as chuyen_id,
       cp.pro_cong_doan_id as cong_doan_id
FROM PRO_CHECK_POINT cp
         INNER JOIN PRO_TAI_KHOAN_CHECK_POINT tkcp
                    ON cp.ID = tkcp.PRO_CHECK_POINT_ID
                        AND tkcp.TAI_KHOAN = 'ndq'


-- kế hoạch sản xuất của ngày hiện tại
SELECT khsx.ID               as orderId,
       khsx.NGAY_SAN_XUAT    as orderManufactureDate,
       khsx.SO_LUONG_YEU_CAU as orderRequestedQuantity,
       khsx.TINH_TRANG       as orderStatus,
       khsx.GHI_CHU          as orderNote,
       khsx.SAM              as orderSam,
       khsx.LOT_NO           as orderLotNumber,
       chuyen.ID             as bandId,
       chuyen.TEN            as bandName,
       chuyen.STT            as bandIndex,
       sp.ID                 as sphId,
       sp.MA_SAN_PHAM        as spMaSanPham,
       sp.MA_DON_HANG        as spMaDonHang,
       sp.PO                 as spPo,
       sp.SO_LUONG_SAN_XUAT  as spSoLuongSanXuat,
       sp.PRO_KHACH_HANG_ID  as spKhachHangId,
       sp.PRO_TEN_MUA_ID     as spTenMuaId
FROM PRO_CHECK_POINT cp
         INNER JOIN PRO_CHUYEN chuyen ON cp.PRO_CHUYEN_ID = chuyen.ID AND cp.ID = 7
         INNER JOIN PRO_KE_HOACH_SAN_XUAT khsx
                    ON khsx.PRO_CHUYEN_ID = chuyen.ID AND DATEDIFF(day, khsx.NGAY_SAN_XUAT,GETDATE()) = 0
         INNER JOIN PRO_SAN_PHAM sp ON khsx.ID = sp.PRO_KE_HOACH_SAN_XUAT_ID

-- thông tin phiên làm việc của nhân viên nào
SELECT cp.ID                 as checkpointId,
       cp.PRO_CONG_DOAN_ID   as checkpointCongDoanId,
       ch.ID                 as bandId,
       ch.TEN                as bandName,
       ch.STT                as bandIndex,
       ch.TEN_LOAI_CHUYEN    as bandTypeName,
       ch.PRO_LOAI_CHUYEN_ID as bandTypeId,
       nhm.ID                as factoryId,
       nhm.TEN               as factoryName,
       nhm.STT               as factoryIndex,
       cty.ID                as companyId,
       cty.TEN               as companyName,
       cty.DIA_CHI           as companyAddr,
       cty.MST               as companyTaxCode
FROM PRO_CHECK_POINT cp
         INNER JOIN PRO_CHUYEN ch ON cp.PRO_CHUYEN_ID = ch.ID
         INNER JOIN PRO_NHA_MAY nhm ON nhm.ID = ch.PRO_NHA_MAY_ID
         INNER JOIN PRO_CONG_TY cty ON cty.ID = nhm.PRO_CONG_TY_ID
WHERE cp.ID = 2
