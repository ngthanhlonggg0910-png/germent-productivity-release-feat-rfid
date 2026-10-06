import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { ThuVienLoiService } from 'app/entities/thu-vien-loi/thu-vien-loi.service';
import { IThuVienLoi, ThuVienLoi } from 'app/shared/model/thu-vien-loi.model';

describe('Service Tests', () => {
  describe('ThuVienLoi Service', () => {
    let injector: TestBed;
    let service: ThuVienLoiService;
    let httpMock: HttpTestingController;
    let elemDefault: IThuVienLoi;
    let expectedResult;
    let currentDate: moment.Moment;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(ThuVienLoiService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new ThuVienLoi(0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', currentDate, 0);
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

      it('should create a ThuVienLoi', () => {
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
          .create(new ThuVienLoi(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a ThuVienLoi', () => {
        const returnedFromService = Object.assign(
          {
            maLoi: 'BBBBBB',
            tenLoi: 'BBBBBB',
            nguoiTao: 'BBBBBB',
            ngayTao: currentDate.format(DATE_TIME_FORMAT),
            userId: 1
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

      it('should return a list of ThuVienLoi', () => {
        const returnedFromService = Object.assign(
          {
            maLoi: 'BBBBBB',
            tenLoi: 'BBBBBB',
            nguoiTao: 'BBBBBB',
            ngayTao: currentDate.format(DATE_TIME_FORMAT),
            userId: 1
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

      it('should delete a ThuVienLoi', () => {
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
