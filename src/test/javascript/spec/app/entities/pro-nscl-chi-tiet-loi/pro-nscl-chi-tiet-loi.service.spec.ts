import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { ProNSCLChiTietLoiService } from 'app/entities/pro-nscl-chi-tiet-loi/pro-nscl-chi-tiet-loi.service';
import { IProNSCLChiTietLoi, ProNSCLChiTietLoi } from 'app/shared/model/pro-nscl-chi-tiet-loi.model';
import { GioLam } from 'app/shared/model/enumerations/gio-lam.model';

describe('Service Tests', () => {
  describe('ProNSCLChiTietLoi Service', () => {
    let injector: TestBed;
    let service: ProNSCLChiTietLoiService;
    let httpMock: HttpTestingController;
    let elemDefault: IProNSCLChiTietLoi;
    let expectedResult;
    let currentDate: moment.Moment;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(ProNSCLChiTietLoiService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new ProNSCLChiTietLoi(0, 'AAAAAAA', currentDate, GioLam.C_1H, 0, 0, false, false);
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

      it('should create a ProNSCLChiTietLoi', () => {
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
          .create(new ProNSCLChiTietLoi(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a ProNSCLChiTietLoi', () => {
        const returnedFromService = Object.assign(
          {
            maLoi: 'BBBBBB',
            thoiGian: currentDate.format(DATE_TIME_FORMAT),
            gioLam: 'BBBBBB',
            dx: 1,
            dy: 1,
            isFront: true,
            isInside: true
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

      it('should return a list of ProNSCLChiTietLoi', () => {
        const returnedFromService = Object.assign(
          {
            maLoi: 'BBBBBB',
            thoiGian: currentDate.format(DATE_TIME_FORMAT),
            gioLam: 'BBBBBB',
            dx: 1,
            dy: 1,
            isFront: true,
            isInside: true
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

      it('should delete a ProNSCLChiTietLoi', () => {
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
