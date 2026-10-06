import { Injectable, Injector } from '@angular/core';
import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor, HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { SERVER_API_URL } from 'app/app.constants';

export class Menu {
  id: string;
  name: string;
  icon?: string;
  link?: string;
}
const keHoachThucHien = {
  caption: 'Ti le hoan thanh',
  mahang: 'PO.505',
  kehoach: 800,
  thuchien: 500,
  kehoachphantram: 60,
  thuchienphantram: 40,
  nhapkho: 400,
  talktimekehoach: 50,
  talktimethuchien: 45,
  tenChuyen: 'chuyen 1'
};
const dataSource = [
  { caption: '7h', valueDat: 81, valueMucTieu: 60, valueTiLeLoi: 12 },
  { caption: '8h', valueDat: 75, valueMucTieu: 60, valueTiLeLoi: 11 },
  { caption: '9h', valueDat: 51, valueMucTieu: 60, valueTiLeLoi: 14 },
  { caption: '11h', valueDat: 70, valueMucTieu: 60, valueTiLeLoi: 11 }
];
const menuItems: Menu[] = [
  { id: '0', name: 'Line 1', icon: 'chart-bar', link: 'line-1' },
  { id: '1', name: 'Line 2', icon: 'chart-bar' },
  { id: '2', name: 'Line 3', icon: 'chart-bar' },
  { id: '3', name: 'Line 4', icon: 'chart-bar' },
  { id: '4', name: 'Line 5', icon: 'chart-bar' },
  { id: '5', name: 'Line 6', icon: 'chart-bar' },
  { id: '6', name: 'Line 7', icon: 'chart-bar' },
  { id: '7', name: 'Line 8', icon: 'chart-bar' }
];

const chiTietLoi = [
  {
    urlHinhMatSau: 'http://localhost:7070/NIKE_MAT_SAU.JPG',
    urlHinhMatTruoc: 'http://localhost:7070/NIKE_MAT_TRUOC.JPG',
    topLois: [
      {
        caption: 'Duc chi',
        soLuong: 10,
        mau: 'green'
      },
      {
        caption: 'Ban',
        soLuong: 5,
        mau: 'red'
      },
      {
        caption: 'Khac mau',
        soLuong: 7,
        mau: 'yellow'
      }
    ],
    chiTietLoiDTOS: [
      {
        id: 1101,
        maLoi: 'QAD00001',
        thoiGian: '1970-01-01T00:02:18Z',
        gioLam: 'C_14H',
        dx: 137.65366108786614,
        dy: 129.60125523012553,
        isFront: true,
        isInside: false,
        proNSCLId: 1051,
        proLoiId: 1,
        proLoi: {
          id: 1,
          maLoi: 'QAD00001',
          cusCode: '106',
          tenLoi: 'Xước vải',
          tenTiengAnh: 'Slub / Knot / Snag',
          label: 'QAD00001',
          stt: 1,
          mauSac: 'green',
          qc: true,
          qa: true,
          checked: true,
          ghiChu: '',
          proCumLoiId: 1,
          proCumLoiTen: 'Cụm Lỗi NPL'
        },
        proLoiMaLoi: 'QAD00001',
        checkPointID: null,
        keHoachSanXuatID: null,
        front: true,
        inside: false
      },
      {
        id: 1103,
        maLoi: 'QAD00001',
        thoiGian: '1970-01-01T00:14:08Z',
        gioLam: 'C_14H',
        dx: 37.216004184100484,
        dy: 376.3225941422594,
        isFront: false,
        isInside: false,
        proNSCLId: 1051,
        proLoiId: 1,
        proLoi: {
          id: 1,
          maLoi: 'QAD00001',
          cusCode: '106',
          tenLoi: 'Xước vải',
          tenTiengAnh: 'Slub / Knot / Snag',
          label: 'QAD00001',
          stt: 1,
          mauSac: 'green',
          qc: true,
          qa: true,
          checked: true,
          ghiChu: '',
          proCumLoiId: 1,
          proCumLoiTen: 'Cụm Lỗi NPL'
        },
        proLoiMaLoi: 'QAD00001',
        checkPointID: null,
        keHoachSanXuatID: null,
        front: false,
        inside: false
      },
      {
        id: 1105,
        maLoi: 'QAD00001',
        thoiGian: '1970-01-01T00:12:32Z',
        gioLam: 'C_14H',
        dx: 254.66286610878666,
        dy: 117.20292887029288,
        isFront: true,
        isInside: true,
        proNSCLId: 1051,
        proLoiId: 1,
        proLoi: {
          id: 1,
          maLoi: 'QAD00001',
          cusCode: '106',
          tenLoi: 'Xước vải',
          tenTiengAnh: 'Slub / Knot / Snag',
          label: 'QAD00001',
          stt: 1,
          mauSac: 'green',
          qc: true,
          qa: true,
          checked: true,
          ghiChu: '',
          proCumLoiId: 1,
          proCumLoiTen: 'Cụm Lỗi NPL'
        },
        proLoiMaLoi: 'QAD00001',
        checkPointID: null,
        keHoachSanXuatID: null,
        front: true,
        inside: true
      }
    ]
  }
];

@Injectable()
export class FakeBackendHttpInterceptor implements HttpInterceptor {
  constructor(private injector: Injector) {}

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    if (request.method === 'GET' && request.url === SERVER_API_URL + 'kehoachthuchien') {
      return of(new HttpResponse({ status: 200, body: keHoachThucHien }));
    }
    if (request.method === 'GET' && request.url === SERVER_API_URL + 'line1') {
      return of(new HttpResponse({ status: 200, body: dataSource }));
    }
    if (request.method === 'GET' && request.url === SERVER_API_URL + 'menuLine') {
      return of(new HttpResponse({ status: 200, body: menuItems }));
    }
    if (request.method === 'GET' && request.url === SERVER_API_URL + 'chitietloi') {
      return of(new HttpResponse({ status: 200, body: chiTietLoi }));
    }
    next.handle(request);
  }
}
