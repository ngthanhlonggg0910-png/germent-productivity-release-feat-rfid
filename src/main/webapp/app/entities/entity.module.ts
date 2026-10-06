import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'sys-role-has-function',
        loadChildren: () => import('./sys-role-has-function/sys-role-has-function.module').then(m => m.CtSysRoleHasFunctionModule)
      },
      {
        path: 'sys-function',
        loadChildren: () => import('./sys-function/sys-function.module').then(m => m.CtSysFunctionModule)
      },
      {
        path: 'sys-module',
        loadChildren: () => import('./sys-module/sys-module.module').then(m => m.CtSysModuleModule)
      },
      {
        path: 'sys-permission',
        loadChildren: () => import('./sys-permission/sys-permission.module').then(m => m.CtSysPermissionModule)
      },
      {
        path: 'sys-group-module',
        loadChildren: () => import('./sys-group-module/sys-group-module.module').then(m => m.CtSysGroupModuleModule)
      },
      {
        path: 'khac-phuc-loi',
        loadChildren: () => import('./khac-phuc-loi/khac-phuc-loi.module').then(m => m.CtKhacPhucLoiModule)
      },
      {
        path: 'thu-vien-loi',
        loadChildren: () => import('./thu-vien-loi/thu-vien-loi.module').then(m => m.CtThuVienLoiModule)
      },
      {
        path: 'quy-cach-npl',
        loadChildren: () => import('./quy-cach-npl/quy-cach-npl.module').then(m => m.CtQuyCachNPLModule)
      },
      {
        path: 'gop-y-chat-luong',
        loadChildren: () => import('./gop-y-chat-luong/gop-y-chat-luong.module').then(m => m.CtGopYChatLuongModule)
      },
      {
        path: 'gycl-quy-cach-npl',
        loadChildren: () => import('./gycl-quy-cach-npl/gycl-quy-cach-npl.module').then(m => m.CtGYCLQuyCachNPLModule)
      },
      {
        path: 'gycl-khac-phuc',
        loadChildren: () => import('./gycl-khac-phuc/gycl-khac-phuc.module').then(m => m.CtGYCLKhacPhucModule)
      },
      {
        path: 'pro-khach-hang',
        loadChildren: () => import('./pro-khach-hang/pro-khach-hang.module').then(m => m.CtProKhachHangModule)
      },
      {
        path: 'pro-san-pham',
        loadChildren: () => import('./pro-san-pham/pro-san-pham.module').then(m => m.CtProSanPhamModule)
      },
      {
        path: 'pro-chung-loai-san-pham',
        loadChildren: () => import('./pro-chung-loai-san-pham/pro-chung-loai-san-pham.module').then(m => m.CtProChungLoaiSanPhamModule)
      },
      {
        path: 'pro-ten-mua',
        loadChildren: () => import('./pro-ten-mua/pro-ten-mua.module').then(m => m.CtProTenMuaModule)
      },
      {
        path: 'pro-ke-hoach-san-xuat',
        loadChildren: () => import('./pro-ke-hoach-san-xuat/pro-ke-hoach-san-xuat.module').then(m => m.CtProKeHoachSanXuatModule)
      },
      {
        path: 'pro-loai-chuyen',
        loadChildren: () => import('./pro-loai-chuyen/pro-loai-chuyen.module').then(m => m.CtProLoaiChuyenModule)
      },
      {
        path: 'pro-chuyen',
        loadChildren: () => import('./pro-chuyen/pro-chuyen.module').then(m => m.CtProChuyenModule)
      },
      {
        path: 'pro-nha-may',
        loadChildren: () => import('./pro-nha-may/pro-nha-may.module').then(m => m.CtProNhaMayModule)
      },
      {
        path: 'pro-cong-ty',
        loadChildren: () => import('./pro-cong-ty/pro-cong-ty.module').then(m => m.CtProCongTyModule)
      },
      {
        path: 'pro-check-point',
        loadChildren: () => import('./pro-check-point/pro-check-point.module').then(m => m.CtProCheckPointModule)
      },
      {
        path: 'pro-tai-khoan-check-point',
        loadChildren: () =>
          import('./pro-tai-khoan-check-point/pro-tai-khoan-check-point.module').then(m => m.CtProTaiKhoanCheckPointModule)
      },
      {
        path: 'pro-cong-doan',
        loadChildren: () => import('./pro-cong-doan/pro-cong-doan.module').then(m => m.CtProCongDoanModule)
      },
      {
        path: 'pro-nhom-cong-doan',
        loadChildren: () => import('./pro-nhom-cong-doan/pro-nhom-cong-doan.module').then(m => m.CtProNhomCongDoanModule)
      },
      {
        path: 'pro-cum-loi',
        loadChildren: () => import('./pro-cum-loi/pro-cum-loi.module').then(m => m.CtProCumLoiModule)
      },
      {
        path: 'pro-loi',
        loadChildren: () => import('./pro-loi/pro-loi.module').then(m => m.CtProLoiModule)
      },
      {
        path: 'pro-nscl',
        loadChildren: () => import('./pro-nscl/pro-nscl.module').then(m => m.CtProNSCLModule)
      },
      {
        path: 'pro-nscl-chi-tiet-loi',
        loadChildren: () => import('./pro-nscl-chi-tiet-loi/pro-nscl-chi-tiet-loi.module').then(m => m.CtProNSCLChiTietLoiModule)
      },
      {
        path: 'pro-gio-lam-viec',
        loadChildren: () => import('./pro-gio-lam-viec/pro-gio-lam-viec.module').then(m => m.CtProGioLamViecModule)
      },
      {
        path: 'dung-sai',
        loadChildren: () => import('./dung-sai/dung-sai.module').then(m => m.CtDungSaiModule)
      },
      {
        path: 'thong-so-do',
        loadChildren: () => import('./thong-so-do/thong-so-do.module').then(m => m.CtThongSoDoModule)
      },
      {
        path: 'thong-so-do-sp',
        loadChildren: () => import('./thong-so-do-sp/thong-so-do-sp.module').then(m => m.CtThongSoDoSPModule)
      },
      {
        path: 'thong-so-do-sp-size',
        loadChildren: () => import('./thong-so-do-sp-size/thong-so-do-sp-size.module').then(m => m.CtThongSoDoSPSizeModule)
      },
      {
        path: 'thong-so-thuc-thi',
        loadChildren: () => import('./thong-so-thuc-thi/thong-so-thuc-thi.module').then(m => m.CtThongSoThucThiModule)
      },
      {
        path: 'sys-file',
        loadChildren: () => import('./sys-file/sys-file.module').then(m => m.CtSysFileModule)
      },
      {
        path: 'group-topic',
        loadChildren: () => import('./group-topic/group-topic.module').then(m => m.CtGroupTopicModule)
      },
      {
        path: 'nv-button-tracking-type',
        loadChildren: () => import('./nv-button-tracking-type/nv-button-tracking-type.module').then(m => m.CtNVButtonTrackingTypeModule)
      },
      {
        path: 'nv-button',
        loadChildren: () => import('./nv-button/nv-button.module').then(m => m.CtNVButtonModule)
      },
      {
        path: 'device',
        loadChildren: () => import('./device/device.module').then(m => m.CtDeviceModule)
      },
      {
        path: 'button-tracking',
        loadChildren: () => import('./button-tracking/button-tracking.module').then(m => m.CtButtonTrackingModule)
      },
      {
        path: 'tracking',
        loadChildren: () => import('./tracking/tracking.module').then(m => m.CtTrackingModule)
      },
      {
        path: 'map-control-button',
        loadChildren: () => import('./map-control-button/map-control-button.module').then(m => m.CtMapControlButtonModule)
      },
      {
        path: 'map-button-action',
        loadChildren: () => import('./map-button-action/map-button-action.module').then(m => m.CtMapButtonActionModule)
      },
      {
        path: 'pro-refinal',
        loadChildren: () => import('./pro-refinal/pro-refinal.module').then(m => m.CtProRefinalModule)
      },
      {
        path: 'pro-refinal-chi-tiet-loi',
        loadChildren: () => import('./pro-refinal-chi-tiet-loi/pro-refinal-chi-tiet-loi.module').then(m => m.CtProRefinalChiTietLoiModule)
      },
      {
        path: 'so-luong-hang-kiem',
        loadChildren: () => import('./so-luong-hang-kiem/so-luong-hang-kiem.module').then(m => m.CtSoLuongHangKiemModule)
      },
      {
        path: 'do-am',
        loadChildren: () => import('./do-am/do-am.module').then(m => m.CtDoAmModule)
      },
      {
        path: 'video',
        loadChildren: () => import('./video/video.module').then(m => m.CtVideoModule)
      },
      {
        path: 'pro-refinal-cc',
        loadChildren: () => import('./pro-refinal-cc/pro-refinal-cc.module').then(m => m.CtProRefinalCCModule)
      },
      {
        path: 'pro-refinal-cc-chi-tiet-loi',
        loadChildren: () =>
          import('./pro-refinal-cc-chi-tiet-loi/pro-refinal-cc-chi-tiet-loi.module').then(m => m.CtProRefinalCCChiTietLoiModule)
      },
      {
        path: 'pro-final',
        loadChildren: () => import('./pro-final/pro-final.module').then(m => m.CtProFinalModule)
      },
      {
        path: 'pro-final-chi-tiet-loi',
        loadChildren: () => import('./pro-final-chi-tiet-loi/pro-final-chi-tiet-loi.module').then(m => m.CtProFinalChiTietLoiModule)
      },
      {
        path: 'pro-aql',
        loadChildren: () => import('./pro-aql/pro-aql.module').then(m => m.CtProAqlModule)
      },
      {
        path: 'do-kim-md',
        loadChildren: () => import('./do-kim-md/do-kim-md.module').then(m => m.CtDoKimMdModule)
      },
      {
        path: 'lib-so-luong-po-kiem',
        loadChildren: () => import('./lib-so-luong-po-kiem/lib-so-luong-po-kiem.module').then(m => m.CtLibSoLuongPoKiemModule)
      },
      {
        path: 'pro-issue-cum-loi',
        loadChildren: () => import('./pro-issue-cum-loi/pro-issue-cum-loi.module').then(m => m.CtProIssueCumLoiModule)
      },
      {
        path: 'pro-issue-loi',
        loadChildren: () => import('./pro-issue-loi/pro-issue-loi.module').then(m => m.CtProIssueLoiModule)
      },
      {
        path: 'pro-issue',
        loadChildren: () => import('./pro-issue/pro-issue.module').then(m => m.CtProIssueModule)
      },
      {
        path: 'cau-hinh-zalo',
        loadChildren: () => import('./cau-hinh-zalo/cau-hinh-zalo.module').then(m => m.CtCauHinhZaloModule)
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ]
})
export class CtEntityModule {}
