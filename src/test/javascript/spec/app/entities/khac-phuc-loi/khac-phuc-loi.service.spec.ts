import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { take, map } from 'rxjs/operators';
import { KhacPhucLoiService } from 'app/entities/khac-phuc-loi/khac-phuc-loi.service';
import { IKhacPhucLoi, KhacPhucLoi } from 'app/shared/model/khac-phuc-loi.model';

describe('Service Tests', () => {
  describe('KhacPhucLoi Service', () => {
    let injector: TestBed;
    let service: KhacPhucLoiService;
    let httpMock: HttpTestingController;
    let elemDefault: IKhacPhucLoi;
    let expectedResult;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(KhacPhucLoiService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new KhacPhucLoi(0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 0);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);
        service
          .find(123)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: elemDefault });
      });

      it('should create a KhacPhucLoi', () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .create(new KhacPhucLoi(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a KhacPhucLoi', () => {
        const returnedFromService = Object.assign(
          {
            maKP: 'BBBBBB',
            tenKP: 'BBBBBB',
            nguoiTao: 'BBBBBB',
            ngayTao: 'BBBBBB',
            userId: 1
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);
        service
          .update(expected)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should return a list of KhacPhucLoi', () => {
        const returnedFromService = Object.assign(
          {
            maKP: 'BBBBBB',
            tenKP: 'BBBBBB',
            nguoiTao: 'BBBBBB',
            ngayTao: 'BBBBBB',
            userId: 1
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
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

      it('should delete a KhacPhucLoi', () => {
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
