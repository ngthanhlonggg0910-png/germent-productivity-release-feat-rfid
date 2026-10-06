import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { take, map } from 'rxjs/operators';
import { ProSanPhamService } from 'app/entities/pro-san-pham/pro-san-pham.service';
import { IProSanPham, ProSanPham } from 'app/shared/model/pro-san-pham.model';

describe('Service Tests', () => {
  describe('ProSanPham Service', () => {
    let injector: TestBed;
    let service: ProSanPhamService;
    let httpMock: HttpTestingController;
    let elemDefault: IProSanPham;
    let expectedResult;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(ProSanPhamService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new ProSanPham(
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        'AAAAAAA',
        'AAAAAAA',
        false,
        'AAAAAAA',
        'AAAAAAA',
        false,
        'AAAAAAA',
        'AAAAAAA',
        false,
        'AAAAAAA',
        'AAAAAAA',
        false,
        'AAAAAAA',
        'AAAAAAA',
        false,
        'AAAAAAA',
        'AAAAAAA',
        false,
        'AAAAAAA',
        'AAAAAAA',
        false,
        'AAAAAAA',
        'AAAAAAA',
        false,
        'AAAAAAA',
        'AAAAAAA',
        false,
        'AAAAAAA',
        'AAAAAAA',
        false,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA'
      );
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

      it('should create a ProSanPham', () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .create(new ProSanPham(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a ProSanPham', () => {
        const returnedFromService = Object.assign(
          {
            maSanPham: 'BBBBBB',
            maDonHang: 'BBBBBB',
            po: 'BBBBBB',
            soLuongSanXuat: 1,
            thongSoValue1: 'BBBBBB',
            thongSoCaption1: 'BBBBBB',
            thongSoCheck1: true,
            thongSoValue2: 'BBBBBB',
            thongSoCaption2: 'BBBBBB',
            thongSoCheck2: true,
            thongSoValue3: 'BBBBBB',
            thongSoCaption3: 'BBBBBB',
            thongSoCheck3: true,
            thongSoValue4: 'BBBBBB',
            thongSoCaption4: 'BBBBBB',
            thongSoCheck4: true,
            thongSoValue5: 'BBBBBB',
            thongSoCaption5: 'BBBBBB',
            thongSoCheck5: true,
            thongSoValue6: 'BBBBBB',
            thongSoCaption6: 'BBBBBB',
            thongSoCheck6: true,
            thongSoValue7: 'BBBBBB',
            thongSoCaption7: 'BBBBBB',
            thongSoCheck7: true,
            thongSoValue8: 'BBBBBB',
            thongSoCaption8: 'BBBBBB',
            thongSoCheck8: true,
            thongSoValue9: 'BBBBBB',
            thongSoCaption9: 'BBBBBB',
            thongSoCheck9: true,
            thongSoValue10: 'BBBBBB',
            thongSoCaption10: 'BBBBBB',
            thongSoCheck10: true,
            color: 'BBBBBB',
            hinhMatTruocURL: 'BBBBBB',
            hinhMatSauURL: 'BBBBBB'
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

      it('should return a list of ProSanPham', () => {
        const returnedFromService = Object.assign(
          {
            maSanPham: 'BBBBBB',
            maDonHang: 'BBBBBB',
            po: 'BBBBBB',
            soLuongSanXuat: 1,
            thongSoValue1: 'BBBBBB',
            thongSoCaption1: 'BBBBBB',
            thongSoCheck1: true,
            thongSoValue2: 'BBBBBB',
            thongSoCaption2: 'BBBBBB',
            thongSoCheck2: true,
            thongSoValue3: 'BBBBBB',
            thongSoCaption3: 'BBBBBB',
            thongSoCheck3: true,
            thongSoValue4: 'BBBBBB',
            thongSoCaption4: 'BBBBBB',
            thongSoCheck4: true,
            thongSoValue5: 'BBBBBB',
            thongSoCaption5: 'BBBBBB',
            thongSoCheck5: true,
            thongSoValue6: 'BBBBBB',
            thongSoCaption6: 'BBBBBB',
            thongSoCheck6: true,
            thongSoValue7: 'BBBBBB',
            thongSoCaption7: 'BBBBBB',
            thongSoCheck7: true,
            thongSoValue8: 'BBBBBB',
            thongSoCaption8: 'BBBBBB',
            thongSoCheck8: true,
            thongSoValue9: 'BBBBBB',
            thongSoCaption9: 'BBBBBB',
            thongSoCheck9: true,
            thongSoValue10: 'BBBBBB',
            thongSoCaption10: 'BBBBBB',
            thongSoCheck10: true,
            color: 'BBBBBB',
            hinhMatTruocURL: 'BBBBBB',
            hinhMatSauURL: 'BBBBBB'
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

      it('should delete a ProSanPham', () => {
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
