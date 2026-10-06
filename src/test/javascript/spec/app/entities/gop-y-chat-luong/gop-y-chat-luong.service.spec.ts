import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { GopYChatLuongService } from 'app/entities/gop-y-chat-luong/gop-y-chat-luong.service';
import { IGopYChatLuong, GopYChatLuong } from 'app/shared/model/gop-y-chat-luong.model';

describe('Service Tests', () => {
  describe('GopYChatLuong Service', () => {
    let injector: TestBed;
    let service: GopYChatLuongService;
    let httpMock: HttpTestingController;
    let elemDefault: IGopYChatLuong;
    let expectedResult;
    let currentDate: moment.Moment;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(GopYChatLuongService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new GopYChatLuong(
        0,
        0,
        'AAAAAAA',
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        'AAAAAAA',
        'AAAAAAA',
        false,
        false,
        0,
        currentDate,
        'AAAAAAA',
        currentDate,
        currentDate,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA'
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            ngayBaoCao: currentDate.format(DATE_TIME_FORMAT),
            userId: currentDate.format(DATE_TIME_FORMAT),
            ngayTao: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );
        service
          .find(123)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: elemDefault });
      });

      it('should create a GopYChatLuong', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            ngayBaoCao: currentDate.format(DATE_TIME_FORMAT),
            userId: currentDate.format(DATE_TIME_FORMAT),
            ngayTao: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            ngayBaoCao: currentDate,
            userId: currentDate,
            ngayTao: currentDate
          },
          returnedFromService
        );
        service
          .create(new GopYChatLuong(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a GopYChatLuong', () => {
        const returnedFromService = Object.assign(
          {
            cusId: 1,
            cusName: 'BBBBBB',
            chuyenId: 1,
            chuyenName: 'BBBBBB',
            maHang: 'BBBBBB',
            po: 'BBBBBB',
            poId: 1,
            color: 'BBBBBB',
            size: 'BBBBBB',
            firstCut: true,
            inline: true,
            lanBanHanh: 1,
            ngayBaoCao: currentDate.format(DATE_TIME_FORMAT),
            nguoiTao: 'BBBBBB',
            userId: currentDate.format(DATE_TIME_FORMAT),
            ngayTao: currentDate.format(DATE_TIME_FORMAT),
            tenQuanLyXuong: 'BBBBBB',
            tenKiThuat: 'BBBBBB',
            tenQanLyCat: 'BBBBBB',
            tenQA: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            ngayBaoCao: currentDate,
            userId: currentDate,
            ngayTao: currentDate
          },
          returnedFromService
        );
        service
          .update(expected)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should return a list of GopYChatLuong', () => {
        const returnedFromService = Object.assign(
          {
            cusId: 1,
            cusName: 'BBBBBB',
            chuyenId: 1,
            chuyenName: 'BBBBBB',
            maHang: 'BBBBBB',
            po: 'BBBBBB',
            poId: 1,
            color: 'BBBBBB',
            size: 'BBBBBB',
            firstCut: true,
            inline: true,
            lanBanHanh: 1,
            ngayBaoCao: currentDate.format(DATE_TIME_FORMAT),
            nguoiTao: 'BBBBBB',
            userId: currentDate.format(DATE_TIME_FORMAT),
            ngayTao: currentDate.format(DATE_TIME_FORMAT),
            tenQuanLyXuong: 'BBBBBB',
            tenKiThuat: 'BBBBBB',
            tenQanLyCat: 'BBBBBB',
            tenQA: 'BBBBBB'
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            ngayBaoCao: currentDate,
            userId: currentDate,
            ngayTao: currentDate
          },
          returnedFromService
        );
        service
          .query(expected)
          .pipe(
            take(1),
            map(resp => resp.body)
          )
          .subscribe(body => (expectedResult = body));
        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a GopYChatLuong', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
