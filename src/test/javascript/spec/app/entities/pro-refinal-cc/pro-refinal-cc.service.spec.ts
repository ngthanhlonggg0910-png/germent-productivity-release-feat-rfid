import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { ProRefinalCCService } from 'app/entities/pro-refinal-cc/pro-refinal-cc.service';
import { IProRefinalCC, ProRefinalCC } from 'app/shared/model/pro-refinal-cc.model';

describe('Service Tests', () => {
  describe('ProRefinalCC Service', () => {
    let injector: TestBed;
    let service: ProRefinalCCService;
    let httpMock: HttpTestingController;
    let elemDefault: IProRefinalCC;
    let expectedResult;
    let currentDate: moment.Moment;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(ProRefinalCCService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new ProRefinalCC(
        0,
        0,
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        'AAAAAAA',
        currentDate,
        0,
        0,
        0,
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        false,
        'AAAAAAA'
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            ngayKiemFinal: currentDate.format(DATE_TIME_FORMAT),
            ngayGioKiem: currentDate.format(DATE_TIME_FORMAT)
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

      it('should create a ProRefinalCC', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            ngayKiemFinal: currentDate.format(DATE_TIME_FORMAT),
            ngayGioKiem: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            ngayKiemFinal: currentDate,
            ngayGioKiem: currentDate
          },
          returnedFromService
        );
        service
          .create(new ProRefinalCC(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a ProRefinalCC', () => {
        const returnedFromService = Object.assign(
          {
            khxhId: 1,
            maHang: 'BBBBBB',
            poNo: 'BBBBBB',
            ngayKiemFinal: currentDate.format(DATE_TIME_FORMAT),
            tenChuyen: 'BBBBBB',
            ngayGioKiem: currentDate.format(DATE_TIME_FORMAT),
            soLuongKiemAql: 1,
            soLuong: 1,
            doAm: 1,
            tongSoLoi: 1,
            soThuTuThung: 'BBBBBB',
            doKimMd: 'BBBBBB',
            aql: 'BBBBBB',
            maxLoiChoPhep: 1,
            completed: true,
            nguoiKiem: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            ngayKiemFinal: currentDate,
            ngayGioKiem: currentDate
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

      it('should return a list of ProRefinalCC', () => {
        const returnedFromService = Object.assign(
          {
            khxhId: 1,
            maHang: 'BBBBBB',
            poNo: 'BBBBBB',
            ngayKiemFinal: currentDate.format(DATE_TIME_FORMAT),
            tenChuyen: 'BBBBBB',
            ngayGioKiem: currentDate.format(DATE_TIME_FORMAT),
            soLuongKiemAql: 1,
            soLuong: 1,
            doAm: 1,
            tongSoLoi: 1,
            soThuTuThung: 'BBBBBB',
            doKimMd: 'BBBBBB',
            aql: 'BBBBBB',
            maxLoiChoPhep: 1,
            completed: true,
            nguoiKiem: 'BBBBBB'
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            ngayKiemFinal: currentDate,
            ngayGioKiem: currentDate
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

      it('should delete a ProRefinalCC', () => {
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
