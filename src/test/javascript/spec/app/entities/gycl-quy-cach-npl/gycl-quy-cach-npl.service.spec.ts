import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { GYCLQuyCachNPLService } from 'app/entities/gycl-quy-cach-npl/gycl-quy-cach-npl.service';
import { IGYCLQuyCachNPL, GYCLQuyCachNPL } from 'app/shared/model/gycl-quy-cach-npl.model';

describe('Service Tests', () => {
  describe('GYCLQuyCachNPL Service', () => {
    let injector: TestBed;
    let service: GYCLQuyCachNPLService;
    let httpMock: HttpTestingController;
    let elemDefault: IGYCLQuyCachNPL;
    let expectedResult;
    let currentDate: moment.Moment;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(GYCLQuyCachNPLService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new GYCLQuyCachNPL(0, 'AAAAAAA', 'AAAAAAA', currentDate, 0, false, 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
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

      it('should create a GYCLQuyCachNPL', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            ngayTao: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            ngayTao: currentDate
          },
          returnedFromService
        );
        service
          .create(new GYCLQuyCachNPL(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a GYCLQuyCachNPL', () => {
        const returnedFromService = Object.assign(
          {
            tenNPL: 'BBBBBB',
            nguoiTao: 'BBBBBB',
            ngayTao: currentDate.format(DATE_TIME_FORMAT),
            userId: 1,
            yesNo: true,
            nA: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign(
          {
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

      it('should return a list of GYCLQuyCachNPL', () => {
        const returnedFromService = Object.assign(
          {
            tenNPL: 'BBBBBB',
            nguoiTao: 'BBBBBB',
            ngayTao: currentDate.format(DATE_TIME_FORMAT),
            userId: 1,
            yesNo: true,
            nA: 'BBBBBB'
          },
          elemDefault
        );
        const expected = Object.assign(
          {
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

      it('should delete a GYCLQuyCachNPL', () => {
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
