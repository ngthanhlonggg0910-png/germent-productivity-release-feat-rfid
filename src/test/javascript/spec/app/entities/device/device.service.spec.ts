import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { DeviceService } from 'app/entities/device/device.service';
import { IDevice, Device } from 'app/shared/model/device.model';
import { LWT } from 'app/shared/model/enumerations/lwt.model';

describe('Service Tests', () => {
  describe('Device Service', () => {
    let injector: TestBed;
    let service: DeviceService;
    let httpMock: HttpTestingController;
    let elemDefault: IDevice;
    let expectedResult;
    let currentDate: moment.Moment;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(DeviceService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Device(
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        LWT.ON_LINE
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            time: currentDate.format(DATE_TIME_FORMAT)
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

      it('should create a Device', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            time: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            time: currentDate
          },
          returnedFromService
        );
        service
          .create(new Device(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a Device', () => {
        const returnedFromService = Object.assign(
          {
            name: 'BBBBBB',
            devIds: 'BBBBBB',
            stateTopic: 'BBBBBB',
            lwtTopic: 'BBBBBB',
            sw: 'BBBBBB',
            module: 'BBBBBB',
            restartReason: 'BBBBBB',
            time: currentDate.format(DATE_TIME_FORMAT),
            uptime: 'BBBBBB',
            hostName: 'BBBBBB',
            ipAddress: 'BBBBBB',
            lwt: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            time: currentDate
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

      it('should return a list of Device', () => {
        const returnedFromService = Object.assign(
          {
            name: 'BBBBBB',
            devIds: 'BBBBBB',
            stateTopic: 'BBBBBB',
            lwtTopic: 'BBBBBB',
            sw: 'BBBBBB',
            module: 'BBBBBB',
            restartReason: 'BBBBBB',
            time: currentDate.format(DATE_TIME_FORMAT),
            uptime: 'BBBBBB',
            hostName: 'BBBBBB',
            ipAddress: 'BBBBBB',
            lwt: 'BBBBBB'
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            time: currentDate
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

      it('should delete a Device', () => {
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
