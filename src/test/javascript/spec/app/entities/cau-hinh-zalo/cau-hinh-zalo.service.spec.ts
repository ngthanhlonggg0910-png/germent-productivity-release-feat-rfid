import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { CauHinhZaloService } from 'app/entities/cau-hinh-zalo/cau-hinh-zalo.service';
import { ICauHinhZalo, CauHinhZalo } from 'app/shared/model/cau-hinh-zalo.model';

describe('Service Tests', () => {
  describe('CauHinhZalo Service', () => {
    let injector: TestBed;
    let service: CauHinhZaloService;
    let httpMock: HttpTestingController;
    let elemDefault: ICauHinhZalo;
    let expectedResult;
    let currentDate: moment.Moment;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(CauHinhZaloService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new CauHinhZalo(0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 0, currentDate);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            expireTime: currentDate.format(DATE_FORMAT)
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

      it('should return a list of CauHinhZalo', () => {
        const returnedFromService = Object.assign(
          {
            accessToken: 'BBBBBB',
            refreshToken: 'BBBBBB',
            zaloOA: 'BBBBBB',
            secretKey: 'BBBBBB',
            appId: 1,
            expireTime: currentDate.format(DATE_FORMAT)
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            expireTime: currentDate
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
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
