import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { NVButtonService } from 'app/entities/nv-button/nv-button.service';
import { INVButton, NVButton } from 'app/shared/model/nv-button.model';
import { NVButtonState } from 'app/shared/model/enumerations/nv-button-state.model';
import { LWT } from 'app/shared/model/enumerations/lwt.model';
import { GroupButtonType } from 'app/shared/model/enumerations/group-button-type.model';

describe('Service Tests', () => {
  describe('NVButton Service', () => {
    let injector: TestBed;
    let service: NVButtonService;
    let httpMock: HttpTestingController;
    let elemDefault: INVButton;
    let expectedResult;
    let currentDate: moment.Moment;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(NVButtonService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new NVButton(
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        NVButtonState.ON,
        LWT.ON_LINE,
        currentDate,
        GroupButtonType.LIGHT
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            lastUpdateState: currentDate.format(DATE_TIME_FORMAT)
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

      it('should create a NVButton', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            lastUpdateState: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            lastUpdateState: currentDate
          },
          returnedFromService
        );
        service
          .create(new NVButton(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a NVButton', () => {
        const returnedFromService = Object.assign(
          {
            name: 'BBBBBB',
            discoveredOnTopic: 'BBBBBB',
            cmndTopic: 'BBBBBB',
            plOff: 'BBBBBB',
            plOn: 'BBBBBB',
            uniqId: 'BBBBBB',
            devIds: 'BBBBBB',
            teleTopic: 'BBBBBB',
            statTopic: 'BBBBBB',
            avtyTopic: 'BBBBBB',
            plAvail: 'BBBBBB',
            plNotAvail: 'BBBBBB',
            state: 'BBBBBB',
            lwt: 'BBBBBB',
            lastUpdateState: currentDate.format(DATE_TIME_FORMAT),
            groupType: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            lastUpdateState: currentDate
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

      it('should return a list of NVButton', () => {
        const returnedFromService = Object.assign(
          {
            name: 'BBBBBB',
            discoveredOnTopic: 'BBBBBB',
            cmndTopic: 'BBBBBB',
            plOff: 'BBBBBB',
            plOn: 'BBBBBB',
            uniqId: 'BBBBBB',
            devIds: 'BBBBBB',
            teleTopic: 'BBBBBB',
            statTopic: 'BBBBBB',
            avtyTopic: 'BBBBBB',
            plAvail: 'BBBBBB',
            plNotAvail: 'BBBBBB',
            state: 'BBBBBB',
            lwt: 'BBBBBB',
            lastUpdateState: currentDate.format(DATE_TIME_FORMAT),
            groupType: 'BBBBBB'
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            lastUpdateState: currentDate
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

      it('should delete a NVButton', () => {
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
