import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { INVButtonTrackingType, NVButtonTrackingType } from 'app/shared/model/nv-button-tracking-type.model';
import { NVButtonTrackingTypeService } from './nv-button-tracking-type.service';

@Component({
  selector: 'jhi-nv-button-tracking-type-update',
  templateUrl: './nv-button-tracking-type-update.component.html'
})
export class NVButtonTrackingTypeUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    name: [null, []],
    numberOfState: []
  });

  constructor(
    protected nVButtonTrackingTypeService: NVButtonTrackingTypeService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ nVButtonTrackingType }) => {
      this.updateForm(nVButtonTrackingType);
    });
  }

  updateForm(nVButtonTrackingType: INVButtonTrackingType) {
    this.editForm.patchValue({
      id: nVButtonTrackingType.id,
      name: nVButtonTrackingType.name,
      numberOfState: nVButtonTrackingType.numberOfState
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const nVButtonTrackingType = this.createFromForm();
    if (nVButtonTrackingType.id !== undefined) {
      this.subscribeToSaveResponse(this.nVButtonTrackingTypeService.update(nVButtonTrackingType));
    } else {
      this.subscribeToSaveResponse(this.nVButtonTrackingTypeService.create(nVButtonTrackingType));
    }
  }

  private createFromForm(): INVButtonTrackingType {
    return {
      ...new NVButtonTrackingType(),
      id: this.editForm.get(['id']).value,
      name: this.editForm.get(['name']).value,
      numberOfState: this.editForm.get(['numberOfState']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<INVButtonTrackingType>>) {
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
