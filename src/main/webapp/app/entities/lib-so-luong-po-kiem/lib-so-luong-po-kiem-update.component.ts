import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { ILibSoLuongPoKiem, LibSoLuongPoKiem } from 'app/shared/model/lib-so-luong-po-kiem.model';
import { LibSoLuongPoKiemService } from './lib-so-luong-po-kiem.service';

@Component({
  selector: 'jhi-lib-so-luong-po-kiem-update',
  templateUrl: './lib-so-luong-po-kiem-update.component.html'
})
export class LibSoLuongPoKiemUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    value: []
  });

  constructor(
    protected libSoLuongPoKiemService: LibSoLuongPoKiemService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ libSoLuongPoKiem }) => {
      this.updateForm(libSoLuongPoKiem);
    });
  }

  updateForm(libSoLuongPoKiem: ILibSoLuongPoKiem) {
    this.editForm.patchValue({
      id: libSoLuongPoKiem.id,
      value: libSoLuongPoKiem.value
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const libSoLuongPoKiem = this.createFromForm();
    if (libSoLuongPoKiem.id !== undefined) {
      this.subscribeToSaveResponse(this.libSoLuongPoKiemService.update(libSoLuongPoKiem));
    } else {
      this.subscribeToSaveResponse(this.libSoLuongPoKiemService.create(libSoLuongPoKiem));
    }
  }

  private createFromForm(): ILibSoLuongPoKiem {
    return {
      ...new LibSoLuongPoKiem(),
      id: this.editForm.get(['id']).value,
      value: this.editForm.get(['value']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ILibSoLuongPoKiem>>) {
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
