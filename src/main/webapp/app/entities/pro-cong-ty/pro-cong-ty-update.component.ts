import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IProCongTy, ProCongTy } from 'app/shared/model/pro-cong-ty.model';
import { ProCongTyService } from './pro-cong-ty.service';

@Component({
  selector: 'jhi-pro-cong-ty-update',
  templateUrl: './pro-cong-ty-update.component.html'
})
export class ProCongTyUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    ten: [],
    diaChi: [],
    mst: []
  });

  constructor(protected proCongTyService: ProCongTyService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ proCongTy }) => {
      this.updateForm(proCongTy);
    });
  }

  updateForm(proCongTy: IProCongTy) {
    this.editForm.patchValue({
      id: proCongTy.id,
      ten: proCongTy.ten,
      diaChi: proCongTy.diaChi,
      mst: proCongTy.mst
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const proCongTy = this.createFromForm();
    if (proCongTy.id !== undefined) {
      this.subscribeToSaveResponse(this.proCongTyService.update(proCongTy));
    } else {
      this.subscribeToSaveResponse(this.proCongTyService.create(proCongTy));
    }
  }

  private createFromForm(): IProCongTy {
    return {
      ...new ProCongTy(),
      id: this.editForm.get(['id']).value,
      ten: this.editForm.get(['ten']).value,
      diaChi: this.editForm.get(['diaChi']).value,
      mst: this.editForm.get(['mst']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IProCongTy>>) {
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
