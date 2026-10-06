import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { take, map } from 'rxjs/operators';
import { ProLoiService } from 'app/entities/pro-loi/pro-loi.service';
import { IProLoi, ProLoi } from 'app/shared/model/pro-loi.model';

describe('Service Tests', () => {
  describe('ProLoi Service', () => {
    let injector: TestBed;
    let service: ProLoiService;
    let httpMock: HttpTestingController;
    let elemDefault: IProLoi;
    let expectedResult;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(ProLoiService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new ProLoi(0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 0, 'AAAAAAA', false, false, false, 'AAAAAAA');
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

      it('should create a ProLoi', () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .create(new ProLoi(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a ProLoi', () => {
        const returnedFromService = Object.assign(
          {
            maLoi: 'BBBBBB',
            cusCode: 'BBBBBB',
            tenLoi: 'BBBBBB',
            tenTiengAnh: 'BBBBBB',
            label: 'BBBBBB',
            stt: 1,
            mauSac: 'BBBBBB',
            qc: true,
            qa: true,
            checked: true,
            ghiChu: 'BBBBBB'
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

      it('should return a list of ProLoi', () => {
        const returnedFromService = Object.assign(
          {
            maLoi: 'BBBBBB',
            cusCode: 'BBBBBB',
            tenLoi: 'BBBBBB',
            tenTiengAnh: 'BBBBBB',
            label: 'BBBBBB',
            stt: 1,
            mauSac: 'BBBBBB',
            qc: true,
            qa: true,
            checked: true,
            ghiChu: 'BBBBBB'
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

      it('should delete a ProLoi', () => {
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
