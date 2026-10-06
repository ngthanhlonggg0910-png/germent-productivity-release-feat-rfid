import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IQuyCachNPL, QuyCachNPL } from 'app/shared/model/quy-cach-npl.model';
import { QuyCachNPLService } from './quy-cach-npl.service';

@Component({
  selector: 'jhi-quy-cach-npl-update',
  templateUrl: './quy-cach-npl-update.component.html'
})
export class QuyCachNPLUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    tenNPL: [],
    maNPL: [],
    nguoiTao: [],
    ngayTao: [],
    userId: []
  });

  constructor(protected quyCachNPLService: QuyCachNPLService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ quyCachNPL }) => {
      this.updateForm(quyCachNPL);
    });
  }

  updateForm(quyCachNPL: IQuyCachNPL) {
    this.editForm.patchValue({
      id: quyCachNPL.id,
      tenNPL: quyCachNPL.tenNPL,
      maNPL: quyCachNPL.maNPL,
      nguoiTao: quyCachNPL.nguoiTao,
      ngayTao: quyCachNPL.ngayTao,
      userId: quyCachNPL.userId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const quyCachNPL = this.createFromForm();
    if (quyCachNPL.id !== undefined) {
      this.subscribeToSaveResponse(this.quyCachNPLService.update(quyCachNPL));
    } else {
      this.subscribeToSaveResponse(this.quyCachNPLService.create(quyCachNPL));
    }
  }

  private createFromForm(): IQuyCachNPL {
    return {
      ...new QuyCachNPL(),
      id: this.editForm.get(['id']).value,
      tenNPL: this.editForm.get(['tenNPL']).value,
      maNPL: this.editForm.get(['maNPL']).value,
      nguoiTao: this.editForm.get(['nguoiTao']).value,
      ngayTao: this.editForm.get(['ngayTao']).value,
      userId: this.editForm.get(['userId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IQuyCachNPL>>) {
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
