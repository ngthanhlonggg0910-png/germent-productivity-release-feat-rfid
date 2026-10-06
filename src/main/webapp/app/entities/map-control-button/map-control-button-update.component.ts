import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';
import { IMapControlButton, MapControlButton } from 'app/shared/model/map-control-button.model';
import { MapControlButtonService } from './map-control-button.service';
import { INVButton } from 'app/shared/model/nv-button.model';
import { NVButtonService } from 'app/entities/nv-button/nv-button.service';

@Component({
  selector: 'jhi-map-control-button-update',
  templateUrl: './map-control-button-update.component.html'
})
export class MapControlButtonUpdateComponent implements OnInit {
  isSaving: boolean;

  nvbuttons: INVButton[];

  editForm = this.fb.group({
    id: [],
    sourceButtonId: [],
    targetButtonId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected mapControlButtonService: MapControlButtonService,
    protected nVButtonService: NVButtonService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mapControlButton }) => {
      this.updateForm(mapControlButton);
    });
    this.nVButtonService
      .query()
      .subscribe((res: HttpResponse<INVButton[]>) => (this.nvbuttons = res.body), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(mapControlButton: IMapControlButton) {
    this.editForm.patchValue({
      id: mapControlButton.id,
      sourceButtonId: mapControlButton.sourceButtonId,
      targetButtonId: mapControlButton.targetButtonId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const mapControlButton = this.createFromForm();
    if (mapControlButton.id !== undefined) {
      this.subscribeToSaveResponse(this.mapControlButtonService.update(mapControlButton));
    } else {
      this.subscribeToSaveResponse(this.mapControlButtonService.create(mapControlButton));
    }
  }

  private createFromForm(): IMapControlButton {
    return {
      ...new MapControlButton(),
      id: this.editForm.get(['id']).value,
      sourceButtonId: this.editForm.get(['sourceButtonId']).value,
      targetButtonId: this.editForm.get(['targetButtonId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMapControlButton>>) {
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
