import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { ProIssueService } from 'app/entities/pro-issue/pro-issue.service';
import { IProIssue, ProIssue } from 'app/shared/model/pro-issue.model';

describe('Service Tests', () => {
  describe('ProIssue Service', () => {
    let injector: TestBed;
    let service: ProIssueService;
    let httpMock: HttpTestingController;
    let elemDefault: IProIssue;
    let expectedResult;
    let currentDate: moment.Moment;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(ProIssueService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new ProIssue(
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        currentDate,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        0,
        'AAAAAAA',
        'AAAAAAA',
        0,
        'AAAAAAA'
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            ngayGioBatDau: currentDate.format(DATE_TIME_FORMAT),
            ngayGioKetThuc: currentDate.format(DATE_TIME_FORMAT)
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

      it('should create a ProIssue', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            ngayGioBatDau: currentDate.format(DATE_TIME_FORMAT),
            ngayGioKetThuc: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            ngayGioBatDau: currentDate,
            ngayGioKetThuc: currentDate
          },
          returnedFromService
        );
        service
          .create(new ProIssue(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a ProIssue', () => {
        const returnedFromService = Object.assign(
          {
            maHang: 'BBBBBB',
            pono: 'BBBBBB',
            tenChuyen: 'BBBBBB',
            ngayGioBatDau: currentDate.format(DATE_TIME_FORMAT),
            ngayGioKetThuc: currentDate.format(DATE_TIME_FORMAT),
            maLoi: 'BBBBBB',
            moTaLoi: 'BBBBBB',
            maMay: 'BBBBBB',
            soTram: 'BBBBBB',
            proChuyenId: 1,
            proSanPhamId: 1,
            nguoiTao: 'BBBBBB',
            checkPointType: 'BBBBBB',
            checkPointId: 1,
            checkPointName: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            ngayGioBatDau: currentDate,
            ngayGioKetThuc: currentDate
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

      it('should return a list of ProIssue', () => {
        const returnedFromService = Object.assign(
          {
            maHang: 'BBBBBB',
            pono: 'BBBBBB',
            tenChuyen: 'BBBBBB',
            ngayGioBatDau: currentDate.format(DATE_TIME_FORMAT),
            ngayGioKetThuc: currentDate.format(DATE_TIME_FORMAT),
            maLoi: 'BBBBBB',
            moTaLoi: 'BBBBBB',
            maMay: 'BBBBBB',
            soTram: 'BBBBBB',
            proChuyenId: 1,
            proSanPhamId: 1,
            nguoiTao: 'BBBBBB',
            checkPointType: 'BBBBBB',
            checkPointId: 1,
            checkPointName: 'BBBBBB'
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            ngayGioBatDau: currentDate,
            ngayGioKetThuc: currentDate
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

      it('should delete a ProIssue', () => {
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
