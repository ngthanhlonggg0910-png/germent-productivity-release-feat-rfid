import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { ProKeHoachSanXuatService } from 'app/entities/pro-ke-hoach-san-xuat/pro-ke-hoach-san-xuat.service';
import { IProKeHoachSanXuat, ProKeHoachSanXuat } from 'app/shared/model/pro-ke-hoach-san-xuat.model';

describe('Service Tests', () => {
  describe('ProKeHoachSanXuat Service', () => {
    let injector: TestBed;
    let service: ProKeHoachSanXuatService;
    let httpMock: HttpTestingController;
    let elemDefault: IProKeHoachSanXuat;
    let expectedResult;
    let currentDate: moment.Moment;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(ProKeHoachSanXuatService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new ProKeHoachSanXuat(0, currentDate, 0, 0, 'AAAAAAA', 0, 0);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            ngaySanXuat: currentDate.format(DATE_TIME_FORMAT)
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

      it('should create a ProKeHoachSanXuat', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            ngaySanXuat: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            ngaySanXuat: currentDate
          },
          returnedFromService
        );
        service
          .create(new ProKeHoachSanXuat(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a ProKeHoachSanXuat', () => {
        const returnedFromService = Object.assign(
          {
            ngaySanXuat: currentDate.format(DATE_TIME_FORMAT),
            soLuongYeuCau: 1,
            tinhTrang: 1,
            ghiChu: 'BBBBBB',
            sam: 1,
            lotNo: 1
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            ngaySanXuat: currentDate
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

      it('should return a list of ProKeHoachSanXuat', () => {
        const returnedFromService = Object.assign(
          {
            ngaySanXuat: currentDate.format(DATE_TIME_FORMAT),
            soLuongYeuCau: 1,
            tinhTrang: 1,
            ghiChu: 'BBBBBB',
            sam: 1,
            lotNo: 1
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            ngaySanXuat: currentDate
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

      it('should delete a ProKeHoachSanXuat', () => {
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
