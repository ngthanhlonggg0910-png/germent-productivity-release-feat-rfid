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
import { ITracking, Tracking } from 'app/shared/model/tracking.model';
import { TrackingService } from './tracking.service';
import { INVButtonTrackingType } from 'app/shared/model/nv-button-tracking-type.model';
import { NVButtonTrackingTypeService } from 'app/entities/nv-button-tracking-type/nv-button-tracking-type.service';

@Component({
  selector: 'jhi-tracking-update',
  templateUrl: './tracking-update.component.html'
})
export class TrackingUpdateComponent implements OnInit {
  isSaving: boolean;

  nvbuttontrackingtypes: INVButtonTrackingType[];

  editForm = this.fb.group({
    id: [],
    nvButtonId: [],
    state1Time: [],
    state2Time: [],
    state3Time: [],
    state4Time: [],
    state5Time: [],
    state6Time: [],
    trackingTypeId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected trackingService: TrackingService,
    protected nVButtonTrackingTypeService: NVButtonTrackingTypeService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ tracking }) => {
      this.updateForm(tracking);
    });
    this.nVButtonTrackingTypeService
      .query()
      .subscribe(
        (res: HttpResponse<INVButtonTrackingType[]>) => (this.nvbuttontrackingtypes = res.body),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  updateForm(tracking: ITracking) {
    this.editForm.patchValue({
      id: tracking.id,
      nvButtonId: tracking.nvButtonId,
      state1Time: tracking.state1Time != null ? tracking.state1Time.format(DATE_TIME_FORMAT) : null,
      state2Time: tracking.state2Time != null ? tracking.state2Time.format(DATE_TIME_FORMAT) : null,
      state3Time: tracking.state3Time != null ? tracking.state3Time.format(DATE_TIME_FORMAT) : null,
      state4Time: tracking.state4Time != null ? tracking.state4Time.format(DATE_TIME_FORMAT) : null,
      state5Time: tracking.state5Time != null ? tracking.state5Time.format(DATE_TIME_FORMAT) : null,
      state6Time: tracking.state6Time != null ? tracking.state6Time.format(DATE_TIME_FORMAT) : null,
      trackingTypeId: tracking.trackingTypeId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const tracking = this.createFromForm();
    if (tracking.id !== undefined) {
      this.subscribeToSaveResponse(this.trackingService.update(tracking));
    } else {
      this.subscribeToSaveResponse(this.trackingService.create(tracking));
    }
  }

  private createFromForm(): ITracking {
    return {
      ...new Tracking(),
      id: this.editForm.get(['id']).value,
      nvButtonId: this.editForm.get(['nvButtonId']).value,
      state1Time:
        this.editForm.get(['state1Time']).value != null ? moment(this.editForm.get(['state1Time']).value, DATE_TIME_FORMAT) : undefined,
      state2Time:
        this.editForm.get(['state2Time']).value != null ? moment(this.editForm.get(['state2Time']).value, DATE_TIME_FORMAT) : undefined,
      state3Time:
        this.editForm.get(['state3Time']).value != null ? moment(this.editForm.get(['state3Time']).value, DATE_TIME_FORMAT) : undefined,
      state4Time:
        this.editForm.get(['state4Time']).value != null ? moment(this.editForm.get(['state4Time']).value, DATE_TIME_FORMAT) : undefined,
      state5Time:
        this.editForm.get(['state5Time']).value != null ? moment(this.editForm.get(['state5Time']).value, DATE_TIME_FORMAT) : undefined,
      state6Time:
        this.editForm.get(['state6Time']).value != null ? moment(this.editForm.get(['state6Time']).value, DATE_TIME_FORMAT) : undefined,
      trackingTypeId: this.editForm.get(['trackingTypeId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITracking>>) {
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

  trackNVButtonTrackingTypeById(index: number, item: INVButtonTrackingType) {
    return item.id;
  }
}
