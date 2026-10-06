import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IProKhachHang, ProKhachHang } from 'app/shared/model/pro-khach-hang.model';
import { ProKhachHangService } from './pro-khach-hang.service';

@Component({
  selector: 'jhi-pro-khach-hang-update',
  templateUrl: './pro-khach-hang-update.component.html'
})
export class ProKhachHangUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    ten: [],
    nhom: [],
    diaChi: []
  });

  constructor(protected proKhachHangService: ProKhachHangService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ proKhachHang }) => {
      this.updateForm(proKhachHang);
    });
  }

  updateForm(proKhachHang: IProKhachHang) {
    this.editForm.patchValue({
      id: proKhachHang.id,
      ten: proKhachHang.ten,
      nhom: proKhachHang.nhom,
      diaChi: proKhachHang.diaChi
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const proKhachHang = this.createFromForm();
    if (proKhachHang.id !== undefined) {
      this.subscribeToSaveResponse(this.proKhachHangService.update(proKhachHang));
    } else {
      this.subscribeToSaveResponse(this.proKhachHangService.create(proKhachHang));
    }
  }

  private createFromForm(): IProKhachHang {
    return {
      ...new ProKhachHang(),
      id: this.editForm.get(['id']).value,
      ten: this.editForm.get(['ten']).value,
      nhom: this.editForm.get(['nhom']).value,
      diaChi: this.editForm.get(['diaChi']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IProKhachHang>>) {
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
