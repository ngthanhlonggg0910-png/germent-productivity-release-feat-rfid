USE [HiPro]
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

-- Bản sao getAllManufacturePlanOnCheckPointToday (production) — KHÔNG ALTER proc cũ.
-- Khác biệt:
--   1) slDaKiem: cộng thêm isnull(sua_po_luy_ke, 0) trên pool pro_nscl (mapping giữ nguyên).
--   2) @idNhom: tham số tùy chọn cho client mới (lọc theo nhóm sẽ bổ sung sau; NULL = không lọc).
--
-- exec [dbo].[getAllManufacturePlanOnCheckPointToday_PoFix] @checkpointId = 8145
-- exec [dbo].[getAllManufacturePlanOnCheckPointToday_PoFix] @checkpointId = 8145, @idNhom = 12

IF OBJECT_ID(N'dbo.getAllManufacturePlanOnCheckPointToday_PoFix', N'P') IS NOT NULL
    DROP PROCEDURE [dbo].[getAllManufacturePlanOnCheckPointToday_PoFix];
GO

CREATE PROCEDURE [dbo].[getAllManufacturePlanOnCheckPointToday_PoFix]
    @checkpointId bigint,
    @idNhom       bigint = NULL
AS
BEGIN
    SET NOCOUNT ON;

    exec PRO_CREATE_UPDATE_KIEM_VUOT_GETDATE_Checkpoint  @checkpointId;
    exec Proc_CapNhatTinhTrang_KeHoachThang_HoanThanh;

    exec DongBoDuLieu_Pro_KhachHang;
    exec DongBoDuLieu_Pro_ChungLoaiSanPham;
    exec DongBoDuLieu_DM_Mua;

    update a
    set a.so_luong_yeu_cau=c.SoLuongSanXuat
    from pro_ke_hoach_san_xuat a
    inner join pro_san_pham b on a.pro_san_pham_id=b.id
    left join DM_SanPham_PO c on b.oid_mapping=c.Oid
    where datediff(day,ngay_san_xuat,getdate())=0 and a.so_luong_yeu_cau=0

    EXEC PRO_UPDATE_SOLUONGYEUCAU_KEHOACHSANXUAT_Checkpoint @checkpointId

    exec UpdateHinhSanPhamDangActive_KhongHienTablet;

    declare @NgayKiemTra datetime = GETDATE();
    declare @Chuyen uniqueidentifier,@STTChuyen int;
    select @Chuyen=pro_chuyen.oid_mapping,@STTChuyen = pro_chuyen.stt
    from pro_check_point 
    inner join pro_chuyen on pro_check_point.pro_chuyen_id = pro_chuyen.id
    where pro_check_point.id =@checkpointId;

    DECLARE @FromDate DATE = DATEADD(DAY, -3, @NgayKiemTra);
    DECLARE @ToDate   DATE = DATEADD(DAY,  3, @NgayKiemTra);
    declare @tbNgay table (NgayThang date)
    
    ;WITH DateList AS
    (
        SELECT @FromDate AS NgayCheck
        UNION ALL
        SELECT DATEADD(DAY, 1, NgayCheck)
        FROM DateList
        WHERE NgayCheck < @ToDate
    )

    insert into @tbNgay
    select NgayCheck from DateList
    OPTION (MAXRECURSION 0);

    DECLARE @tblQ table (MaHang nvarchar(500),CumPO nvarchar(500),ChuyenId bigint,SoLuongYeuCau INT,NgayBatDau datetime,KHTId bigint,STT int,DHId bigint)
    insert into @tblQ (MaHang ,CumPO ,ChuyenId ,SoLuongYeuCau ,NgayBatDau ,KHTId ,STT,DHId )
    select dh.MaHang,PO.NhomPO,ChuyenId,SUM(isnull(kht.DaChiaTacNghiepChiaTo,0)) SoLuongYeuCau,min(KHT.NgayVaoChuyenKeHoachBatDau) NgayBatDau,min(KHTId) KHTId,min(KHT.STT) STT,DH.DHId
    from eGMF.dbo.OMM_KeHoachThang KHT
    inner join eGMF.dbo.OMM_PO_ChiTiet ct on ct.POCTId = KHT.CTPOId
    inner join eGMF.dbo.OMM_PO po on po.POId = ct.POId
    INNER join eGMF.dbo.OMM_DonHang dh on dh.DHId = po.DHId
    INNER JOIN eGMF.dbo.Lib_CumChuyen cum on cum.CumId = ChuyenId
    inner join @tbNgay ng on cast(ng.NgayThang as date) >= cast(kht.NgayVaoChuyenKeHoachBatDau as date)
    and  cast(ng.NgayThang as date) <= cast(kht.NgayVaoChuyenKeHoachKetThuc as date)
    where cum.STT = @STTChuyen  and kht.TinhTrang = 'MoiTao'
    group by dh.MaHang,PO.NhomPO,ChuyenId,DH.DHId
    ORDER BY min(KHT.NgayVaoChuyenKeHoachBatDau)

    if(@STTChuyen=99)
    begin
        insert into @tblQ (MaHang ,CumPO ,ChuyenId ,SoLuongYeuCau ,NgayBatDau ,KHTId ,STT,DHId )
        select  dh.ProductCode MaHang,PO.NhomPO,ChuyenId,SUM(isnull(kht.DaChiaTacNghiepChiaTo,0)) SoLuongYeuCau,min(KHT.NgayVaoChuyenKeHoachBatDau) NgayBatDau,min(KHTId) KHTId,min(KHT.STT) STT,DH.Id DHId
        from eGMF.dbo.OMM_KeHoachThang KHT
        inner join eGMF.dbo.OMM_PO_ChiTiet ct on ct.POCTId = KHT.CTPOId
        inner join eGMF.dbo.OMM_PO po on po.POId = ct.POId
        INNER join eGMF.dbo.TEC_ProductInformation dh on dh.Id = po.DHId
        INNER JOIN eGMF.dbo.Lib_CumChuyen cum on cum.CumId = ChuyenId
        inner join @tbNgay ng on cast(ng.NgayThang as date) >= cast(kht.NgayVaoChuyenKeHoachBatDau as date)
        and  cast(ng.NgayThang as date) <= cast(kht.NgayVaoChuyenKeHoachKetThuc as date)
        where cum.STT = @STTChuyen  and kht.TinhTrang = 'MoiTao'
        and dh.ProductCode not in (select MaHang from @tblQ)
        group by dh.ProductCode,PO.NhomPO,ChuyenId,DH.Id
        ORDER BY min(KHT.NgayVaoChuyenKeHoachBatDau)
    end

    declare @TblA table ([KHTId] bigint,[NgaySanXuat] datetime,[SoLuongYeuCau] int,[TinhTrang] nvarchar(500),[GhiChu] nvarchar(max),[Sam] int, [LotNo] nvarchar(500),Chuyen uniqueidentifier, SanPham uniqueidentifier, NgayVaoChuyenKeHoachBatDau datetime,NgayVaoChuyenKeHoachKetThuc datetime,STT int,oid nvarchar(500),PO nvarchar(500),CUMPO  nvarchar(500),MaHang  nvarchar(500),DHId bigint)
    insert into @TblA ([KHTId],NgaySanXuat,[SoLuongYeuCau],[TinhTrang],[GhiChu],[Sam], [LotNo],Chuyen, SanPham, NgayVaoChuyenKeHoachBatDau ,NgayVaoChuyenKeHoachKetThuc,STT,OID,po,CUMPO,MaHang ,kkk.DHId)
    select kkk.KHTId,kkk.NgayBatDau,kkk.SoLuongYeuCau,'MoiTao','' [GhiChu],0 [Sam], [LotNo], @Chuyen, null, kkk.NgayBatDau, kkk.NgayBatDau,kkk.STT,cast(NEWID() as uniqueidentifier) Oid,kkk.CumPO,kkk.CumPO,kkk.MaHang,kkk.DHId
    from @tblQ kkk
    left join (select sp.DHId,po.CumPO,kh.Oid,kh.LotNo,kh.KHTId
               from DM_SanPham sp 
               INNER JOIN DM_SanPham_PO po on sp.Oid= po.SanPham 
               inner join NV_KeHoachSanXuat kh on kh.SanPham = po.Oid and kh.Chuyen = @Chuyen
               group by sp.DHId,po.CumPO,kh.Oid,kh.LotNo,kh.KHTId) kh on kh.DHId = kkk.DHId and kh.CumPO=kkk.CumPO and kh.KHTId=kkk.KHTId
    where kh.Oid is null
    order by kkk.NgayBatDau

    update kh set kh.isCompleted = 0, complete_time=null
    from @tblQ kkk
    inner join NV_KeHoachSanXuat kh on kh.KHTId = kkk.KHTId 
    where kh.isCompleted = 1

    declare @cumpo nvarchar(500),@dhid bigint
    DECLARE kht_cursor2 CURSOR FOR select CUMPO,DHId FROM @TblA a
    OPEN kht_cursor2;
    FETCH NEXT FROM kht_cursor2 into @cumpo,@dhid;
    WHILE @@FETCH_STATUS = 0
    BEGIN
        declare @SPPO_Count int = 0
        set @SPPO_Count = isnull((select count(po.POId) from DM_SanPham_PO po inner join DM_SanPham on po.SanPham = DM_SanPham.Oid and @cumpo = po.CumPO and @dhid =DM_SanPham.DHId),0)
        if @SPPO_Count =0
        begin
            declare @tbaSP_PO table ([Oid] uniqueidentifier,[PO] nvarchar(500),[MauSanPham] nvarchar(500),[Mua] nvarchar(500),[SoLuongSanXuat] int,[POId] bigint,[CTPOId] bigint,[SanPham] uniqueidentifier,[ChungLoaiSanPham] uniqueidentifier,[CumPO] nvarchar(500))
            insert into @tbaSP_PO ([Oid],[PO],[MauSanPham],[Mua],[SoLuongSanXuat],[POId],[CTPOId],[SanPham],[ChungLoaiSanPham],[CumPO])
            select top 1 NEWID(),po.PONo,po.MaMau,DM_Mua.Oid,a.SoLuongYeuCau,po.[POId],ct.POCTId,cast(DM_SanPham.Oid as uniqueidentifier),CL.Oid,po.NhomPO
            from @TblA a
            inner join eGMF.dbo.OMM_KeHoachThang k on a.KHTId = k.KHTId
            inner join eGMF.dbo.OMM_PO_ChiTiet ct on ct.POCTId = k.CTPOId
            inner join eGMF.dbo.OMM_PO po on ct.POId = po.POId
            inner join eGMF.dbo.OMM_DonHang dh on dh.DHId = po.DHId
            inner join DM_ChungLoaiSanPham CL ON CL.MaChungLoai = dh.MaChungLoai
            inner join DM_SanPham on DM_SanPham.DHId = dh.DHId
            INNER JOIN eGMF.dbo.Lib_CumChuyen cum on cum.CumId = ChuyenId
            left join DM_Mua on DM_Mua.TenMua = po.Mua
            where a.CUMPO=@cumpo and a.DHId=@dhid and cum.STT = @STTChuyen

            if(@STTChuyen=99)
            begin
                insert into @tbaSP_PO ([Oid],[PO],[MauSanPham],[Mua],[SoLuongSanXuat],[POId],[CTPOId],[SanPham],[ChungLoaiSanPham],[CumPO])
                select top 1 NEWID(),po.PONo,po.MaMau,DM_Mua.Oid,a.SoLuongYeuCau,po.[POId],ct.POCTId,DM_SanPham.Oid,CL.Oid,po.NhomPO
                from @TblA a
                inner join eGMF.dbo.OMM_KeHoachThang k on a.KHTId = k.KHTId
                inner join eGMF.dbo.OMM_PO_ChiTiet ct on ct.POCTId = k.CTPOId
                inner join eGMF.dbo.OMM_PO po on ct.POId = po.POId
                inner join eGMF.dbo.TEC_ProductInformation dh on dh.Id = po.DHId
                inner join DM_ChungLoaiSanPham CL ON CL.MaChungLoai = dh.MaChungLoai
                inner join DM_SanPham on DM_SanPham.DHId = dh.Id
                INNER JOIN eGMF.dbo.Lib_CumChuyen cum on cum.CumId = ChuyenId
                left join DM_Mua on DM_Mua.TenMua = po.Mua
                where a.CUMPO=@cumpo and a.DHId=@dhid and cum.STT = @STTChuyen
            end

            insert into DM_SanPham_PO ([Oid],[PO],[MauSanPham],[Mua],[SoLuongSanXuat],[POId],[CTPOId],[SanPham],[ChungLoaiSanPham],[CumPO])
            select [Oid],[PO],[MauSanPham],[Mua],[SoLuongSanXuat],[POId],[CTPOId],[SanPham],[ChungLoaiSanPham],[CumPO] from @tbaSP_PO
            delete from @tbaSP_PO
        end
        FETCH NEXT FROM kht_cursor2 into @cumpo,@dhid;
    END;
    CLOSE kht_cursor2;
    DEALLOCATE kht_cursor2;

    exec PRO_UPDATE_SOLUONG_PO @Chuyen
    exec DongBoDuLieu_pro_san_pham

    update a set A.PO = PO.PO,A.SanPham = PO.Oid
    from @TblA a
    inner join DM_SanPham on a.DHId = DM_SanPham.DHId
    inner join DM_SanPham_PO po on po.CumPO = a.CUMPO and po.SanPham=DM_SanPham.Oid

    insert into [HiPro].[dbo].[NV_KeHoachSanXuat] ([KHTId],[NgaySanXuat],[SoLuongYeuCau],[TinhTrang],[GhiChu],[Sam],[LotNo],[Chuyen],[SanPham],[NgayBatDau],[NgayKetThuc],[ChuyenOld],[STT], OID)
    SELECT [KHTId],[NgaySanXuat],[SoLuongYeuCau],[TinhTrang],[GhiChu],[Sam],[LotNo],[Chuyen],[SanPham],NgayVaoChuyenKeHoachBatDau,NgayVaoChuyenKeHoachKetThuc,Chuyen,[STT], cast(OID as uniqueidentifier)
    FROM @TblA AA

    DECLARE @oidkh uniqueidentifier
    DECLARE kht_cursor1 CURSOR FOR
    select A.Oid FROM NV_KeHoachSanXuat A 
    inner join DM_SanPham_PO PO on a.SanPham = PO.Oid
    inner join DM_SanPham SP on PO.SanPham = SP.Oid
    inner join @tblQ AA ON AA.CumPO=PO.CumPO and AA.DHId=SP.DHId
    where a.Chuyen = @Chuyen
    OPEN kht_cursor1;
    FETCH NEXT FROM kht_cursor1 into @oidkh;
    WHILE @@FETCH_STATUS = 0
    BEGIN
        declare @count int = 0 
        set @count = isnull((select count(pro_ke_hoach_san_xuat.id) from pro_ke_hoach_san_xuat where oid_mapping=@oidkh and datediff(day,ngay_san_xuat,@NgayKiemTra) = 0),0)
        if @count = 0
        begin
            insert into pro_ke_hoach_san_xuat ([ngay_san_xuat],[so_luong_yeu_cau],[tinh_trang],[ghi_chu],[sam],[lot_no],[pro_chuyen_id],[pro_san_pham_id],[oid_mapping],STT,isActived,active_time,isCompleted)
            select @NgayKiemTra,[SoLuongYeuCau], case when [TinhTrang] = 'MoiTao' then 0 else 1 end,[GhiChu],[Sam],[LotNo],pro_chuyen_id,min(pro_san_pham_id),Oid,STT,1,@NgayKiemTra,0
            from (
                select [SoLuongYeuCau], TinhTrang,[GhiChu],[Sam],[LotNo],pro_chuyen.id as pro_chuyen_id,pro_san_pham.id as pro_san_pham_id,AA.Oid,aa.STT
                from NV_KeHoachSanXuat AA
                inner join pro_chuyen on pro_chuyen.oid_mapping = aa.Chuyen
                inner join pro_san_pham on pro_san_pham.oid_mapping = aa.SanPham
                where AA.Oid = @oidkh
            ) mot 
            group by SoLuongYeuCau,TinhTrang,GhiChu,Sam,LotNo,pro_chuyen_id,Oid,STT
        end
        FETCH NEXT FROM kht_cursor1 into @oidkh;
    END;
    CLOSE kht_cursor1;
    DEALLOCATE kht_cursor1;

    select *
    ,case when DVT='SET' then (select top 1 URL from DM_HinhSanPham where SanPham=a.SanPham and HinhMatTruoc=1 and ChungLoaiChiTiet=MaChungLoai) else spFrontImgUrl1 end spFrontImgUrl
    ,case when DVT='SET' then (select top 1 URL from DM_HinhSanPham where SanPham=a.SanPham and HinhMatTruoc=0 and ChungLoaiChiTiet=MaChungLoai) else spBehindImgUrl1 end spBehindImgUrl
    from (
        SELECT khsx.ID as planId, khsx.NGAY_SAN_XUAT as planManufactureDate, khsx.SO_LUONG_YEU_CAU as planRequestedQuantity,
               khsx.TINH_TRANG as planStatus, khsx.GHI_CHU as planNote, khsx.SAM as planSam, khsx.LOT_NO as planLotNumber,
               chuyen.ID as bandId, chuyen.TEN as bandName, chuyen.STT as bandIndex,
               sp.ID as spId, sp.MA_SAN_PHAM as spMaSanPham, sp.MA_DON_HANG as spMaDonHang,
               sp.po+CHAR(10) +'('+ SPPO.CumPO + ')' as spPo, '-' as spColor, sp.SO_LUONG_SAN_XUAT as spSoLuongSanXuat,
               sp.PRO_KHACH_HANG_ID as spKhachHangId, kh.TEN as khTen, sp.PRO_TEN_MUA_ID as spTenMuaId,
               (select SeasonCode from eGMF.dbo.TEC_ProductInformation tec1 inner join egmf.dbo.OMM_PO tec2 on tec1.Id=tec2.DHId where tec1.ProductCode=sp.ma_don_hang and tec2.PONo=sp.po) tenMua,
               sp.HINH_MAT_TRUOC_URL as spFrontImgUrl1, sp.HINH_MAT_SAU_URL as spBehindImgUrl1, b3.DVT, b3.MaChungLoai, SPPO.SanPham,
               (
                   select isnull((
                       select sum(
                           qty_1_h+qty_2_h+qty_3_h+qty_4_h+qty_5_h+qty_6_h+qty_7_h+qty_8_h+qty_9_h+qty_10_h+qty_11_h+qty_12_h+qty_13_h+qty_14_h
                           +fix_1_h+fix_2_h+fix_3_h+fix_4_h+fix_5_h+fix_6_h+fix_7_h+fix_8_h+fix_9_h+fix_10_h+fix_11_h+fix_12_h+fix_13_h+fix_14_h
                           +isnull(a1.sua_po_luy_ke, 0)
                       )
                       from pro_nscl a1
                       inner join pro_ke_hoach_san_xuat b1 on a1.pro_ke_hoach_san_xuat_id=b1.id
                       left join NV_KeHoachSanXuat b2 on b1.oid_mapping=b2.Oid
                       inner join pro_san_pham c1 on b1.pro_san_pham_id=c1.id
                       left join DM_SanPham_PO on c1.oid_mapping=DM_SanPham_PO.Oid
                       where c1.ma_don_hang=sp.ma_san_pham
                         and DM_SanPham_PO.CumPO=SPPO.CumPO
                         and b2.Chuyen=chuyen.oid_mapping
                         and datediff(day,b1.ngay_san_xuat,@NgayKiemTra) >= 0
                         and b2.Oid=khsx.oid_mapping
                   ), 0)
               ) as slDaKiem,
               b2.STT
        FROM PRO_CHECK_POINT cp
        INNER JOIN PRO_CHUYEN chuyen ON cp.PRO_CHUYEN_ID = chuyen.ID AND cp.ID = @checkpointId
        INNER JOIN PRO_KE_HOACH_SAN_XUAT khsx ON khsx.PRO_CHUYEN_ID = chuyen.ID AND DATEDIFF(day, khsx.NGAY_SAN_XUAT, @NgayKiemTra) = 0
        INNER JOIN PRO_SAN_PHAM sp ON khsx.PRO_SAN_PHAM_ID = sp.ID
        inner join DM_SanPham_PO SPPO ON SPPO.Oid = SP.oid_mapping
        inner JOIN PRO_KHACH_HANG kh ON sp.PRO_KHACH_HANG_ID = kh.ID
        LEFT JOIN PRO_TEN_MUA mua ON sp.PRO_TEN_MUA_ID = mua.ID
        left join NV_KeHoachSanXuat b1 on khsx.oid_mapping=b1.Oid
        left join eGMF.dbo.OMM_KeHoachThang b2 on b2.KHTId=b1.KHTId
        left join eGMF.dbo.OMM_PO_ChiTiet b3 on b2.CTPOId=b3.POCTId
        where isnull(khsx.isCompleted,0) = 0 and b2.TinhTrang='MoiTao'
    ) a
    order by STT
END
GO
