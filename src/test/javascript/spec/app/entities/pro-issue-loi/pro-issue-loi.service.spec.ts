import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { take, map } from 'rxjs/operators';
import { ProIssueLoiService } from 'app/entities/pro-issue-loi/pro-issue-loi.service';
import { IProIssueLoi, ProIssueLoi } from 'app/shared/model/pro-issue-loi.model';

describe('Service Tests', () => {
  describe('ProIssueLoi Service', () => {
    let injector: TestBed;
    let service: ProIssueLoiService;
    let httpMock: HttpTestingController;
    let elemDefault: IProIssueLoi;
    let expectedResult;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(ProIssueLoiService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new ProIssueLoi(0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 0, 'AAAAAAA', 'AAAAAAA');
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

      it('should create a ProIssueLoi', () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .create(new ProIssueLoi(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a ProIssueLoi', () => {
        const returnedFromService = Object.assign(
          {
            maLoi: 'BBBBBB',
            cusCode: 'BBBBBB',
            tenLoi: 'BBBBBB',
            tenTiengAnh: 'BBBBBB',
            label: 'BBBBBB',
            stt: 1,
            mauSac: 'BBBBBB',
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

      it('should return a list of ProIssueLoi', () => {
        const returnedFromService = Object.assign(
          {
            maLoi: 'BBBBBB',
            cusCode: 'BBBBBB',
            tenLoi: 'BBBBBB',
            tenTiengAnh: 'BBBBBB',
            label: 'BBBBBB',
            stt: 1,
            mauSac: 'BBBBBB',
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

      it('should delete a ProIssueLoi', () => {
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
