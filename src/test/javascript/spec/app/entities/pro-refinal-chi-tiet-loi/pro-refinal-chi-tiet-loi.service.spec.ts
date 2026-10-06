import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { ProRefinalChiTietLoiService } from 'app/entities/pro-refinal-chi-tiet-loi/pro-refinal-chi-tiet-loi.service';
import { IProRefinalChiTietLoi, ProRefinalChiTietLoi } from 'app/shared/model/pro-refinal-chi-tiet-loi.model';

describe('Service Tests', () => {
  describe('ProRefinalChiTietLoi Service', () => {
    let injector: TestBed;
    let service: ProRefinalChiTietLoiService;
    let httpMock: HttpTestingController;
    let elemDefault: IProRefinalChiTietLoi;
    let expectedResult;
    let currentDate: moment.Moment;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(ProRefinalChiTietLoiService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new ProRefinalChiTietLoi(0, 'AAAAAAA', currentDate, 0, 0, false, false, false);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            thoiGian: currentDate.format(DATE_TIME_FORMAT)
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

      it('should create a ProRefinalChiTietLoi', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            thoiGian: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            thoiGian: currentDate
          },
          returnedFromService
        );
        service
          .create(new ProRefinalChiTietLoi(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a ProRefinalChiTietLoi', () => {
        const returnedFromService = Object.assign(
          {
            maLoi: 'BBBBBB',
            thoiGian: currentDate.format(DATE_TIME_FORMAT),
            dx: 1,
            dy: 1,
            isFront: true,
            isInside: true,
            warning: true
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            thoiGian: currentDate
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

      it('should return a list of ProRefinalChiTietLoi', () => {
        const returnedFromService = Object.assign(
          {
            maLoi: 'BBBBBB',
            thoiGian: currentDate.format(DATE_TIME_FORMAT),
            dx: 1,
            dy: 1,
            isFront: true,
            isInside: true,
            warning: true
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            thoiGian: currentDate
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

      it('should delete a ProRefinalChiTietLoi', () => {
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
