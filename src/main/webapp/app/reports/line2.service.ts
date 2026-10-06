import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, Subject, of, from } from 'rxjs';
import { delay, tap, mergeMap, repeat, catchError } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';

@Injectable({
  providedIn: 'root'
})
export class Line2Service {
  public thongTinChung: Subject<any> = new Subject();
  private nangSuatTheoGio: Subject<any> = new Subject();
  private nangSuatLaoDongNgay: Subject<any> = new Subject();

  public refinalTheoGio: Subject<any> = new Subject();
  private topLoiChiTietLoi: Subject<any> = new Subject();
  private video: Subject<any> = new Subject();

  constructor(private httpClient: HttpClient) {}

  fecthAllData(id) {
    this.fetchThongTinChung(id);
    this.fetchNangSuatTheoGio(id);
    this.fetchChiTietLoi(id);
    this.fetchNangSuatLaoDongNgay(id);
    this.fetchVideo();
    this.fetchRefinalTheoGio(id);
  }

  public getVideo() {
    return this.video;
  }

  public getTopLoiChiTietLoi() {
    return this.topLoiChiTietLoi;
  }

  public getNangSuatLaoDongNgaySubject() {
    return this.nangSuatLaoDongNgay;
  }

  public getThongTinChung() {
    return this.thongTinChung;
  }

  public getNangSuatTheoGio() {
    return this.nangSuatTheoGio;
  }
  public getRefinalTheoGio() {
    return this.refinalTheoGio;
  }

  fetchNangSuatTheoGio(id) {
    const poll = of({}).pipe(
      mergeMap(_ => from(this.getKeHoachMucTieu(id)).pipe(catchError(err => of({})))),
      tap(res => {
        this.nangSuatTheoGio.next(res);
      }),
      delay(15000 + Math.floor(Math.random() * 10 + 1)),
      repeat()
    );
    poll.subscribe();
  }
  fetchRefinalTheoGio(id) {
    const poll = of({}).pipe(
      mergeMap(_ => from(this.getProRefinalTheoGio(id)).pipe(catchError(err => of({})))),
      tap(res => {
        this.refinalTheoGio.next(res);
      }),
      delay(10000 + Math.floor(Math.random() * 10 + 1)),
      repeat()
    );
    poll.subscribe();
  }
  fetchThongTinChung(id) {
    const poll = of({}).pipe(
      mergeMap(_ => from(this.getKeHoachThuchien(id)).pipe(catchError(err => of({})))),
      tap(res => {
        this.thongTinChung.next(res);
      }),
      delay(10000 + Math.floor(Math.random() * 10 + 1)),
      repeat()
    );
    poll.subscribe();
  }

  fetchChiTietLoi(id) {
    const poll = of({}).pipe(
      mergeMap(_ => from(this.getChiTietLoi(id)).pipe(catchError(err => of({})))),
      tap(res => {
        this.topLoiChiTietLoi.next(res);
      }),
      delay(20000 + Math.floor(Math.random() * 10 + 1)),
      repeat()
    );
    poll.subscribe();
  }

  fetchNangSuatLaoDongNgay(id) {
    const poll = of({}).pipe(
      mergeMap(_ => from(this.getNangSuatLaoDongNgay(id)).pipe(catchError(err => of({})))),
      tap(res => {
        this.nangSuatLaoDongNgay.next(res);
      }),
      delay(14000 + Math.floor(Math.random() * 10 + 1)),
      repeat()
    );
    poll.subscribe();
  }

  fetchVideo() {
    const poll = of({}).pipe(
      mergeMap(_ => from(this.getVideoSource()).pipe(catchError(err => of({})))),
      tap(res => {
        this.video.next(res);
      }),
      delay(60000 * 5 + Math.floor(Math.random() * 10 + 1)),
      repeat()
    );
    poll.subscribe();
  }

  getDomain() {
    return SERVER_API_URL;
  }

  getVideoSource(): Observable<any> {
    return this.httpClient.get(SERVER_API_URL + 'api/videos');
  }

  getKeHoachThuchien(chuyenId: number): Observable<any> {
    return this.httpClient.get(SERVER_API_URL + 'api/pro-nscls/theo-gio-hien-tai/theo-chuyen-thong-tin-chung/' + chuyenId);
  }
  getKeHoachMucTieu(chuyenId: number): Observable<any> {
    return this.httpClient.get(SERVER_API_URL + 'api/pro-nscls/theo-gio-hien-tai/theo-chuyen/' + chuyenId);
  }
  getChiTietLoi(chuyenId: number): Observable<any> {
    return this.httpClient.get(SERVER_API_URL + 'api/pro-nscls/theo-gio-hien-tai/top-loi-chi-tiet-loi/' + chuyenId);
  }
  getNangSuatLaoDongNgay(chuyenId: number): Observable<any> {
    return this.httpClient.get(SERVER_API_URL + 'api/pro-nscls/theo-gio-hien-tai/nang-suat-lao-dong-ngay/' + chuyenId);
  }
  getProRefinalTheoGio(chuyenId: number): Observable<any> {
    return this.httpClient.get(SERVER_API_URL + 'api/pro-refinals/refinal-theo-gio/' + chuyenId);
  }

  computeColor(mau) {
    switch (mau) {
      case 'yellow':
        return '#e6d93e';
      case 'blue':
        return '#007bff';
      case 'green':
        return '#28a745';
      case 'red':
        return '#d91f07';
      default:
        return '#bd11ba';
    }
  }
}
