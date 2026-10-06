import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { GYCLKhacPhucService } from 'app/entities/gycl-khac-phuc/gycl-khac-phuc.service';
import { IGYCLKhacPhuc, GYCLKhacPhuc } from 'app/shared/model/gycl-khac-phuc.model';

describe('Service Tests', () => {
  describe('GYCLKhacPhuc Service', () => {
    let injector: TestBed;
    let service: GYCLKhacPhucService;
    let httpMock: HttpTestingController;
    let elemDefault: IGYCLKhacPhuc;
    let expectedResult;
    let currentDate: moment.Moment;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(GYCLKhacPhucService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new GYCLKhacPhuc(0, 0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', currentDate, currentDate);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
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

      it('should create a GYCLKhacPhuc', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            userId: currentDate.format(DATE_TIME_FORMAT),
            ngayTao: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            userId: currentDate,
            ngayTao: currentDate
          },
          returnedFromService
        );
        service
          .create(new GYCLKhacPhuc(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a GYCLKhacPhuc', () => {
        const returnedFromService = Object.assign(
          {
            cumId: 1,
            tenCum: 'BBBBBB',
            gopY: 'BBBBBB',
            ketQua: 'BBBBBB',
            nguoiTao: 'BBBBBB',
            userId: currentDate.format(DATE_TIME_FORMAT),
            ngayTao: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );

        const expected = Object.assign(
          {
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

      it('should return a list of GYCLKhacPhuc', () => {
        const returnedFromService = Object.assign(
          {
            cumId: 1,
            tenCum: 'BBBBBB',
            gopY: 'BBBBBB',
            ketQua: 'BBBBBB',
            nguoiTao: 'BBBBBB',
            userId: currentDate.format(DATE_TIME_FORMAT),
            ngayTao: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );
        const expected = Object.assign(
          {
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

      it('should delete a GYCLKhacPhuc', () => {
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
