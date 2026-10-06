import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';
import { IMapButtonAction, MapButtonAction } from 'app/shared/model/map-button-action.model';
import { MapButtonActionService } from './map-button-action.service';
import { INVButtonTrackingType } from 'app/shared/model/nv-button-tracking-type.model';
import { NVButtonTrackingTypeService } from 'app/entities/nv-button-tracking-type/nv-button-tracking-type.service';

@Component({
  selector: 'jhi-map-button-action-update',
  templateUrl: './map-button-action-update.component.html'
})
export class MapButtonActionUpdateComponent implements OnInit {
  isSaving: boolean;

  nvbuttontrackingtypes: INVButtonTrackingType[];

  editForm = this.fb.group({
    id: [],
    sourceAliasState: [],
    targetState: [],
    trackingTypeId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected mapButtonActionService: MapButtonActionService,
    protected nVButtonTrackingTypeService: NVButtonTrackingTypeService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mapButtonAction }) => {
      this.updateForm(mapButtonAction);
    });
    this.nVButtonTrackingTypeService
      .query()
      .subscribe(
        (res: HttpResponse<INVButtonTrackingType[]>) => (this.nvbuttontrackingtypes = res.body),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  updateForm(mapButtonAction: IMapButtonAction) {
    this.editForm.patchValue({
      id: mapButtonAction.id,
      sourceAliasState: mapButtonAction.sourceAliasState,
      targetState: mapButtonAction.targetState,
      trackingTypeId: mapButtonAction.trackingTypeId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const mapButtonAction = this.createFromForm();
    if (mapButtonAction.id !== undefined) {
      this.subscribeToSaveResponse(this.mapButtonActionService.update(mapButtonAction));
    } else {
      this.subscribeToSaveResponse(this.mapButtonActionService.create(mapButtonAction));
    }
  }

  private createFromForm(): IMapButtonAction {
    return {
      ...new MapButtonAction(),
      id: this.editForm.get(['id']).value,
      sourceAliasState: this.editForm.get(['sourceAliasState']).value,
      targetState: this.editForm.get(['targetState']).value,
      trackingTypeId: this.editForm.get(['trackingTypeId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMapButtonAction>>) {
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
