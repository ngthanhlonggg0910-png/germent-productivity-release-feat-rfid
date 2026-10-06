import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { take, map } from 'rxjs/operators';
import { ThongSoDoSPSizeService } from 'app/entities/thong-so-do-sp-size/thong-so-do-sp-size.service';
import { IThongSoDoSPSize, ThongSoDoSPSize } from 'app/shared/model/thong-so-do-sp-size.model';

describe('Service Tests', () => {
  describe('ThongSoDoSPSize Service', () => {
    let injector: TestBed;
    let service: ThongSoDoSPSizeService;
    let httpMock: HttpTestingController;
    let elemDefault: IThongSoDoSPSize;
    let expectedResult;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(ThongSoDoSPSizeService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new ThongSoDoSPSize(0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 0, 0);
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

      it('should create a ThongSoDoSPSize', () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .create(new ThongSoDoSPSize(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a ThongSoDoSPSize', () => {
        const returnedFromService = Object.assign(
          {
            size: 'BBBBBB',
            cmThongSoChuan: 'BBBBBB',
            inchThongSoChuan: 'BBBBBB',
            cmThongSoChuanValue: 1,
            inchThongSoChuanValue: 1
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

      it('should return a list of ThongSoDoSPSize', () => {
        const returnedFromService = Object.assign(
          {
            size: 'BBBBBB',
            cmThongSoChuan: 'BBBBBB',
            inchThongSoChuan: 'BBBBBB',
            cmThongSoChuanValue: 1,
            inchThongSoChuanValue: 1
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

      it('should delete a ThongSoDoSPSize', () => {
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
