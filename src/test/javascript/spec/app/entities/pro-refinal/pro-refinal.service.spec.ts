import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { ProRefinalService } from 'app/entities/pro-refinal/pro-refinal.service';
import { IProRefinal, ProRefinal } from 'app/shared/model/pro-refinal.model';

describe('Service Tests', () => {
  describe('ProRefinal Service', () => {
    let injector: TestBed;
    let service: ProRefinalService;
    let httpMock: HttpTestingController;
    let elemDefault: IProRefinal;
    let expectedResult;
    let currentDate: moment.Moment;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(ProRefinalService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new ProRefinal(0, 0, 0, 0, currentDate, 0, 0, 0);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
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

      it('should create a ProRefinal', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            ngayGioKiem: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            ngayGioKiem: currentDate
          },
          returnedFromService
        );
        service
          .create(new ProRefinal(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a ProRefinal', () => {
        const returnedFromService = Object.assign(
          {
            proKHSXId: 1,
            checkPointID: 1,
            proNSCLId: 1,
            ngayGioKiem: currentDate.format(DATE_TIME_FORMAT),
            soLuongKiem: 1,
            hangDatHienTai: 1,
            doAm: 1
          },
          elemDefault
        );

        const expected = Object.assign(
          {
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

      it('should return a list of ProRefinal', () => {
        const returnedFromService = Object.assign(
          {
            proKHSXId: 1,
            checkPointID: 1,
            proNSCLId: 1,
            ngayGioKiem: currentDate.format(DATE_TIME_FORMAT),
            soLuongKiem: 1,
            hangDatHienTai: 1,
            doAm: 1
          },
          elemDefault
        );
        const expected = Object.assign(
          {
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

      it('should delete a ProRefinal', () => {
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
