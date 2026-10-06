import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { ProFinalService } from 'app/entities/pro-final/pro-final.service';
import { IProFinal, ProFinal } from 'app/shared/model/pro-final.model';

describe('Service Tests', () => {
  describe('ProFinal Service', () => {
    let injector: TestBed;
    let service: ProFinalService;
    let httpMock: HttpTestingController;
    let elemDefault: IProFinal;
    let expectedResult;
    let currentDate: moment.Moment;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(ProFinalService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new ProFinal(
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
        0,
        'AAAAAAA',
        'AAAAAAA',
        0,
        false,
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

      it('should create a ProFinal', () => {
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
          .create(new ProFinal(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a ProFinal', () => {
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
            soThuTuThung: 1,
            doKimMd: 'BBBBBB',
            aql: 'BBBBBB',
            maxLoiChoPhep: 1,
            completed: true,
            soLuongPoKiem: 1,
            pass: true,
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

      it('should return a list of ProFinal', () => {
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
            soThuTuThung: 1,
            doKimMd: 'BBBBBB',
            aql: 'BBBBBB',
            maxLoiChoPhep: 1,
            completed: true,
            soLuongPoKiem: 1,
            pass: true,
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

      it('should delete a ProFinal', () => {
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
