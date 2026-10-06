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
import { IButtonTracking, ButtonTracking } from 'app/shared/model/button-tracking.model';
import { ButtonTrackingService } from './button-tracking.service';
import { INVButton } from 'app/shared/model/nv-button.model';
import { NVButtonService } from 'app/entities/nv-button/nv-button.service';

@Component({
  selector: 'jhi-button-tracking-update',
  templateUrl: './button-tracking-update.component.html'
})
export class ButtonTrackingUpdateComponent implements OnInit {
  isSaving: boolean;

  nvbuttons: INVButton[];

  editForm = this.fb.group({
    id: [],
    updateDate: [],
    currentAliasState: [],
    trackingId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected buttonTrackingService: ButtonTrackingService,
    protected nVButtonService: NVButtonService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ buttonTracking }) => {
      this.updateForm(buttonTracking);
    });
    this.nVButtonService
      .query()
      .subscribe((res: HttpResponse<INVButton[]>) => (this.nvbuttons = res.body), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(buttonTracking: IButtonTracking) {
    this.editForm.patchValue({
      id: buttonTracking.id,
      updateDate: buttonTracking.updateDate != null ? buttonTracking.updateDate.format(DATE_TIME_FORMAT) : null,
      currentAliasState: buttonTracking.currentAliasState,
      trackingId: buttonTracking.trackingId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const buttonTracking = this.createFromForm();
    if (buttonTracking.id !== undefined) {
      this.subscribeToSaveResponse(this.buttonTrackingService.update(buttonTracking));
    } else {
      this.subscribeToSaveResponse(this.buttonTrackingService.create(buttonTracking));
    }
  }

  private createFromForm(): IButtonTracking {
    return {
      ...new ButtonTracking(),
      id: this.editForm.get(['id']).value,
      updateDate:
        this.editForm.get(['updateDate']).value != null ? moment(this.editForm.get(['updateDate']).value, DATE_TIME_FORMAT) : undefined,
      currentAliasState: this.editForm.get(['currentAliasState']).value,
      trackingId: this.editForm.get(['trackingId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IButtonTracking>>) {
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

  trackNVButtonById(index: number, item: INVButton) {
    return item.id;
  }
}
