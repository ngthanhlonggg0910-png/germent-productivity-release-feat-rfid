import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { take, map } from 'rxjs/operators';
import { QuyCachNPLService } from 'app/entities/quy-cach-npl/quy-cach-npl.service';
import { IQuyCachNPL, QuyCachNPL } from 'app/shared/model/quy-cach-npl.model';

describe('Service Tests', () => {
  describe('QuyCachNPL Service', () => {
    let injector: TestBed;
    let service: QuyCachNPLService;
    let httpMock: HttpTestingController;
    let elemDefault: IQuyCachNPL;
    let expectedResult;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(QuyCachNPLService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new QuyCachNPL(0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 0);
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

      it('should create a QuyCachNPL', () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .create(new QuyCachNPL(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a QuyCachNPL', () => {
        const returnedFromService = Object.assign(
          {
            tenNPL: 'BBBBBB',
            maNPL: 'BBBBBB',
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

      it('should return a list of QuyCachNPL', () => {
        const returnedFromService = Object.assign(
          {
            tenNPL: 'BBBBBB',
            maNPL: 'BBBBBB',
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

      it('should delete a QuyCachNPL', () => {
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
