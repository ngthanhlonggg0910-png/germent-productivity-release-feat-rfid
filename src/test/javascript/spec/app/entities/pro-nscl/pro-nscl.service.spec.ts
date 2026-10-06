import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { ProNSCLService } from 'app/entities/pro-nscl/pro-nscl.service';
import { IProNSCL, ProNSCL } from 'app/shared/model/pro-nscl.model';

describe('Service Tests', () => {
  describe('ProNSCL Service', () => {
    let injector: TestBed;
    let service: ProNSCLService;
    let httpMock: HttpTestingController;
    let elemDefault: IProNSCL;
    let expectedResult: IProNSCL | IProNSCL[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(ProNSCLService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new ProNSCL(
        0,
        'AAAAAAA',
        currentDate,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            ngaySanXuat: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a ProNSCL', () => {
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

        service.create(new ProNSCL()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a ProNSCL', () => {
        const returnedFromService = Object.assign(
          {
            taiKhoan: 'BBBBBB',
            ngaySanXuat: currentDate.format(DATE_TIME_FORMAT),
            stt: 1,
            mucTieuNgay: 1,
            thucHienNgay: 1,
            loiNgay: 1,
            suaNgay: 1,
            btpCap: 1,
            qty1h: 1,
            qty2h: 1,
            qty3h: 1,
            qty4h: 1,
            qty5h: 1,
            qty6h: 1,
            qty7h: 1,
            qty8h: 1,
            qty9h: 1,
            qty10h: 1,
            qty11h: 1,
            qty12h: 1,
            qty13h: 1,
            qty14h: 1,
            reject1h: 1,
            reject2h: 1,
            reject3h: 1,
            reject4h: 1,
            reject5h: 1,
            reject6h: 1,
            reject7h: 1,
            reject8h: 1,
            reject9h: 1,
            reject10h: 1,
            reject11h: 1,
            reject12h: 1,
            reject13h: 1,
            reject14h: 1,
            man1h: 1,
            man2h: 1,
            man3h: 1,
            man4h: 1,
            man5h: 1,
            man6h: 1,
            man7h: 1,
            man8h: 1,
            man9h: 1,
            man10h: 1,
            man11h: 1,
            man12h: 1,
            man13h: 1,
            man14h: 1,
            manOnly7Hours: 1,
            fix1h: 1,
            fix2h: 1,
            fix3h: 1,
            fix4h: 1,
            fix5h: 1,
            fix6h: 1,
            fix7h: 1,
            fix8h: 1,
            fix9h: 1,
            fix10h: 1,
            fix11h: 1,
            fix12h: 1,
            fix13h: 1,
            fix14h: 1
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            ngaySanXuat: currentDate
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of ProNSCL', () => {
        const returnedFromService = Object.assign(
          {
            taiKhoan: 'BBBBBB',
            ngaySanXuat: currentDate.format(DATE_TIME_FORMAT),
            stt: 1,
            mucTieuNgay: 1,
            thucHienNgay: 1,
            loiNgay: 1,
            suaNgay: 1,
            btpCap: 1,
            qty1h: 1,
            qty2h: 1,
            qty3h: 1,
            qty4h: 1,
            qty5h: 1,
            qty6h: 1,
            qty7h: 1,
            qty8h: 1,
            qty9h: 1,
            qty10h: 1,
            qty11h: 1,
            qty12h: 1,
            qty13h: 1,
            qty14h: 1,
            reject1h: 1,
            reject2h: 1,
            reject3h: 1,
            reject4h: 1,
            reject5h: 1,
            reject6h: 1,
            reject7h: 1,
            reject8h: 1,
            reject9h: 1,
            reject10h: 1,
            reject11h: 1,
            reject12h: 1,
            reject13h: 1,
            reject14h: 1,
            man1h: 1,
            man2h: 1,
            man3h: 1,
            man4h: 1,
            man5h: 1,
            man6h: 1,
            man7h: 1,
            man8h: 1,
            man9h: 1,
            man10h: 1,
            man11h: 1,
            man12h: 1,
            man13h: 1,
            man14h: 1,
            manOnly7Hours: 1,
            fix1h: 1,
            fix2h: 1,
            fix3h: 1,
            fix4h: 1,
            fix5h: 1,
            fix6h: 1,
            fix7h: 1,
            fix8h: 1,
            fix9h: 1,
            fix10h: 1,
            fix11h: 1,
            fix12h: 1,
            fix13h: 1,
            fix14h: 1
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            ngaySanXuat: currentDate
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a ProNSCL', () => {
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
