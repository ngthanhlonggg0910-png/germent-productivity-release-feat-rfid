import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { ButtonTrackingService } from 'app/entities/button-tracking/button-tracking.service';
import { IButtonTracking, ButtonTracking } from 'app/shared/model/button-tracking.model';

describe('Service Tests', () => {
  describe('ButtonTracking Service', () => {
    let injector: TestBed;
    let service: ButtonTrackingService;
    let httpMock: HttpTestingController;
    let elemDefault: IButtonTracking;
    let expectedResult;
    let currentDate: moment.Moment;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(ButtonTrackingService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new ButtonTracking(0, currentDate, 0, 0);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            updateDate: currentDate.format(DATE_TIME_FORMAT)
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

      it('should create a ButtonTracking', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            updateDate: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            updateDate: currentDate
          },
          returnedFromService
        );
        service
          .create(new ButtonTracking(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a ButtonTracking', () => {
        const returnedFromService = Object.assign(
          {
            updateDate: currentDate.format(DATE_TIME_FORMAT),
            currentAliasState: 1,
            trackingId: 1
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            updateDate: currentDate
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

      it('should return a list of ButtonTracking', () => {
        const returnedFromService = Object.assign(
          {
            updateDate: currentDate.format(DATE_TIME_FORMAT),
            currentAliasState: 1,
            trackingId: 1
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            updateDate: currentDate
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

      it('should delete a ButtonTracking', () => {
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
