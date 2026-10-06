import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IProTenMua, ProTenMua } from 'app/shared/model/pro-ten-mua.model';
import { ProTenMuaService } from './pro-ten-mua.service';

@Component({
  selector: 'jhi-pro-ten-mua-update',
  templateUrl: './pro-ten-mua-update.component.html'
})
export class ProTenMuaUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    tenMua: []
  });

  constructor(protected proTenMuaService: ProTenMuaService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ proTenMua }) => {
      this.updateForm(proTenMua);
    });
  }

  updateForm(proTenMua: IProTenMua) {
    this.editForm.patchValue({
      id: proTenMua.id,
      tenMua: proTenMua.tenMua
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const proTenMua = this.createFromForm();
    if (proTenMua.id !== undefined) {
      this.subscribeToSaveResponse(this.proTenMuaService.update(proTenMua));
    } else {
      this.subscribeToSaveResponse(this.proTenMuaService.create(proTenMua));
    }
  }

  private createFromForm(): IProTenMua {
    return {
      ...new ProTenMua(),
      id: this.editForm.get(['id']).value,
      tenMua: this.editForm.get(['tenMua']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IProTenMua>>) {
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
