import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { IDevice, Device } from 'app/shared/model/device.model';
import { DeviceService } from './device.service';

@Component({
  selector: 'jhi-device-update',
  templateUrl: './device-update.component.html'
})
export class DeviceUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    name: [],
    devIds: [],
    stateTopic: [null, []],
    lwtTopic: [null, []],
    sw: [],
    module: [],
    restartReason: [],
    time: [],
    uptime: [],
    hostName: [],
    ipAddress: [],
    lwt: []
  });

  constructor(protected deviceService: DeviceService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ device }) => {
      this.updateForm(device);
    });
  }

  updateForm(device: IDevice) {
    this.editForm.patchValue({
      id: device.id,
      name: device.name,
      devIds: device.devIds,
      stateTopic: device.stateTopic,
      lwtTopic: device.lwtTopic,
      sw: device.sw,
      module: device.module,
      restartReason: device.restartReason,
      time: device.time != null ? device.time.format(DATE_TIME_FORMAT) : null,
      uptime: device.uptime,
      hostName: device.hostName,
      ipAddress: device.ipAddress,
      lwt: device.lwt
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const device = this.createFromForm();
    if (device.id !== undefined) {
      this.subscribeToSaveResponse(this.deviceService.update(device));
    } else {
      this.subscribeToSaveResponse(this.deviceService.create(device));
    }
  }

  private createFromForm(): IDevice {
    return {
      ...new Device(),
      id: this.editForm.get(['id']).value,
      name: this.editForm.get(['name']).value,
      devIds: this.editForm.get(['devIds']).value,
      stateTopic: this.editForm.get(['stateTopic']).value,
      lwtTopic: this.editForm.get(['lwtTopic']).value,
      sw: this.editForm.get(['sw']).value,
      module: this.editForm.get(['module']).value,
      restartReason: this.editForm.get(['restartReason']).value,
      time: this.editForm.get(['time']).value != null ? moment(this.editForm.get(['time']).value, DATE_TIME_FORMAT) : undefined,
      uptime: this.editForm.get(['uptime']).value,
      hostName: this.editForm.get(['hostName']).value,
      ipAddress: this.editForm.get(['ipAddress']).value,
      lwt: this.editForm.get(['lwt']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDevice>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
