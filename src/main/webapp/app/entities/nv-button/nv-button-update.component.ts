import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';
import { INVButton, NVButton } from 'app/shared/model/nv-button.model';
import { NVButtonService } from './nv-button.service';
import { IButtonTracking } from 'app/shared/model/button-tracking.model';
import { ButtonTrackingService } from 'app/entities/button-tracking/button-tracking.service';
import { IDevice } from 'app/shared/model/device.model';
import { DeviceService } from 'app/entities/device/device.service';
import { INVButtonTrackingType } from 'app/shared/model/nv-button-tracking-type.model';
import { NVButtonTrackingTypeService } from 'app/entities/nv-button-tracking-type/nv-button-tracking-type.service';

@Component({
  selector: 'jhi-nv-button-update',
  templateUrl: './nv-button-update.component.html'
})
export class NVButtonUpdateComponent implements OnInit {
  isSaving: boolean;

  buttontrackings: IButtonTracking[];

  devices: IDevice[];

  nvbuttontrackingtypes: INVButtonTrackingType[];

  editForm = this.fb.group({
    id: [],
    name: [],
    discoveredOnTopic: [],
    cmndTopic: [null, []],
    plOff: [],
    plOn: [],
    uniqId: [],
    devIds: [],
    teleTopic: [],
    statTopic: [],
    avtyTopic: [],
    plAvail: [],
    plNotAvail: [],
    state: [],
    lwt: [],
    lastUpdateState: [],
    groupType: [],
    buttonTrackingId: [],
    deviceId: [],
    trackingTypeId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected nVButtonService: NVButtonService,
    protected buttonTrackingService: ButtonTrackingService,
    protected deviceService: DeviceService,
    protected nVButtonTrackingTypeService: NVButtonTrackingTypeService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ nVButton }) => {
      this.updateForm(nVButton);
    });
    this.buttonTrackingService.query({ filter: 'nvbutton-is-null' }).subscribe(
      (res: HttpResponse<IButtonTracking[]>) => {
        if (!this.editForm.get('buttonTrackingId').value) {
          this.buttontrackings = res.body;
        } else {
          this.buttonTrackingService
            .find(this.editForm.get('buttonTrackingId').value)
            .subscribe(
              (subRes: HttpResponse<IButtonTracking>) => (this.buttontrackings = [subRes.body].concat(res.body)),
              (subRes: HttpErrorResponse) => this.onError(subRes.message)
            );
        }
      },
      (res: HttpErrorResponse) => this.onError(res.message)
    );
    this.deviceService
      .query()
      .subscribe((res: HttpResponse<IDevice[]>) => (this.devices = res.body), (res: HttpErrorResponse) => this.onError(res.message));
    this.nVButtonTrackingTypeService
      .query()
      .subscribe(
        (res: HttpResponse<INVButtonTrackingType[]>) => (this.nvbuttontrackingtypes = res.body),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  updateForm(nVButton: INVButton) {
    this.editForm.patchValue({
      id: nVButton.id,
      name: nVButton.name,
      discoveredOnTopic: nVButton.discoveredOnTopic,
      cmndTopic: nVButton.cmndTopic,
      plOff: nVButton.plOff,
      plOn: nVButton.plOn,
      uniqId: nVButton.uniqId,
      devIds: nVButton.devIds,
      teleTopic: nVButton.teleTopic,
      statTopic: nVButton.statTopic,
      avtyTopic: nVButton.avtyTopic,
      plAvail: nVButton.plAvail,
      plNotAvail: nVButton.plNotAvail,
      state: nVButton.state,
      lwt: nVButton.lwt,
      lastUpdateState: nVButton.lastUpdateState != null ? nVButton.lastUpdateState.format(DATE_TIME_FORMAT) : null,
      groupType: nVButton.groupType,
      buttonTrackingId: nVButton.buttonTrackingId,
      deviceId: nVButton.deviceId,
      trackingTypeId: nVButton.trackingTypeId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const nVButton = this.createFromForm();
    if (nVButton.id !== undefined) {
      this.subscribeToSaveResponse(this.nVButtonService.update(nVButton));
    } else {
      this.subscribeToSaveResponse(this.nVButtonService.create(nVButton));
    }
  }

  private createFromForm(): INVButton {
    return {
      ...new NVButton(),
      id: this.editForm.get(['id']).value,
      name: this.editForm.get(['name']).value,
      discoveredOnTopic: this.editForm.get(['discoveredOnTopic']).value,
      cmndTopic: this.editForm.get(['cmndTopic']).value,
      plOff: this.editForm.get(['plOff']).value,
      plOn: this.editForm.get(['plOn']).value,
      uniqId: this.editForm.get(['uniqId']).value,
      devIds: this.editForm.get(['devIds']).value,
      teleTopic: this.editForm.get(['teleTopic']).value,
      statTopic: this.editForm.get(['statTopic']).value,
      avtyTopic: this.editForm.get(['avtyTopic']).value,
      plAvail: this.editForm.get(['plAvail']).value,
      plNotAvail: this.editForm.get(['plNotAvail']).value,
      state: this.editForm.get(['state']).value,
      lwt: this.editForm.get(['lwt']).value,
      lastUpdateState:
        this.editForm.get(['lastUpdateState']).value != null
          ? moment(this.editForm.get(['lastUpdateState']).value, DATE_TIME_FORMAT)
          : undefined,
      groupType: this.editForm.get(['groupType']).value,
      buttonTrackingId: this.editForm.get(['buttonTrackingId']).value,
      deviceId: this.editForm.get(['deviceId']).value,
      trackingTypeId: this.editForm.get(['trackingTypeId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<INVButton>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  trackButtonTrackingById(index: number, item: IButtonTracking) {
    return item.id;
  }

  trackDeviceById(index: number, item: IDevice) {
    return item.id;
  }

  trackNVButtonTrackingTypeById(index: number, item: INVButtonTrackingType) {
    return item.id;
  }
}
