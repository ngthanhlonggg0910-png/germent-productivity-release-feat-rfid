import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { TrackingService } from 'app/entities/tracking/tracking.service';
import { ITracking, Tracking } from 'app/shared/model/tracking.model';

describe('Service Tests', () => {
  describe('Tracking Service', () => {
    let injector: TestBed;
    let service: TrackingService;
    let httpMock: HttpTestingController;
    let elemDefault: ITracking;
    let expectedResult;
    let currentDate: moment.Moment;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(TrackingService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Tracking(0, 0, currentDate, currentDate, currentDate, currentDate, currentDate, currentDate);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            state1Time: currentDate.format(DATE_TIME_FORMAT),
            state2Time: currentDate.format(DATE_TIME_FORMAT),
            state3Time: currentDate.format(DATE_TIME_FORMAT),
            state4Time: currentDate.format(DATE_TIME_FORMAT),
            state5Time: currentDate.format(DATE_TIME_FORMAT),
            state6Time: currentDate.format(DATE_TIME_FORMAT)
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

      it('should create a Tracking', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            state1Time: currentDate.format(DATE_TIME_FORMAT),
            state2Time: currentDate.format(DATE_TIME_FORMAT),
            state3Time: currentDate.format(DATE_TIME_FORMAT),
            state4Time: currentDate.format(DATE_TIME_FORMAT),
            state5Time: currentDate.format(DATE_TIME_FORMAT),
            state6Time: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            state1Time: currentDate,
            state2Time: currentDate,
            state3Time: currentDate,
            state4Time: currentDate,
            state5Time: currentDate,
            state6Time: currentDate
          },
          returnedFromService
        );
        service
          .create(new Tracking(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a Tracking', () => {
        const returnedFromService = Object.assign(
          {
            nvButtonId: 1,
            state1Time: currentDate.format(DATE_TIME_FORMAT),
            state2Time: currentDate.format(DATE_TIME_FORMAT),
            state3Time: currentDate.format(DATE_TIME_FORMAT),
            state4Time: currentDate.format(DATE_TIME_FORMAT),
            state5Time: currentDate.format(DATE_TIME_FORMAT),
            state6Time: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            state1Time: currentDate,
            state2Time: currentDate,
            state3Time: currentDate,
            state4Time: currentDate,
            state5Time: currentDate,
            state6Time: currentDate
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

      it('should return a list of Tracking', () => {
        const returnedFromService = Object.assign(
          {
            nvButtonId: 1,
            state1Time: currentDate.format(DATE_TIME_FORMAT),
            state2Time: currentDate.format(DATE_TIME_FORMAT),
            state3Time: currentDate.format(DATE_TIME_FORMAT),
            state4Time: currentDate.format(DATE_TIME_FORMAT),
            state5Time: currentDate.format(DATE_TIME_FORMAT),
            state6Time: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            state1Time: currentDate,
            state2Time: currentDate,
            state3Time: currentDate,
            state4Time: currentDate,
            state5Time: currentDate,
            state6Time: currentDate
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

      it('should delete a Tracking', () => {
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
