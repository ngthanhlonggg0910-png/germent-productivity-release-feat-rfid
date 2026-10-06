import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { ISysPermission, SysPermission } from 'app/shared/model/sys-permission.model';
import { SysPermissionService } from './sys-permission.service';

@Component({
  selector: 'jhi-sys-permission-update',
  templateUrl: './sys-permission-update.component.html'
})
export class SysPermissionUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.required, Validators.maxLength(100)]],
    caption: [null, [Validators.maxLength(255)]],
    level: [],
    imageUri: [],
    eventName: [null, [Validators.maxLength(50)]],
    hotkey: [null, [Validators.maxLength(5)]]
  });

  constructor(protected sysPermissionService: SysPermissionService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ sysPermission }) => {
      this.updateForm(sysPermission);
    });
  }

  updateForm(sysPermission: ISysPermission) {
    this.editForm.patchValue({
      id: sysPermission.id,
      name: sysPermission.name,
      caption: sysPermission.caption,
      level: sysPermission.level,
      imageUri: sysPermission.imageUri,
      eventName: sysPermission.eventName,
      hotkey: sysPermission.hotkey
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const sysPermission = this.createFromForm();
    if (sysPermission.id !== undefined) {
      this.subscribeToSaveResponse(this.sysPermissionService.update(sysPermission));
    } else {
      this.subscribeToSaveResponse(this.sysPermissionService.create(sysPermission));
    }
  }

  private createFromForm(): ISysPermission {
    return {
      ...new SysPermission(),
      id: this.editForm.get(['id']).value,
      name: this.editForm.get(['name']).value,
      caption: this.editForm.get(['caption']).value,
      level: this.editForm.get(['level']).value,
      imageUri: this.editForm.get(['imageUri']).value,
      eventName: this.editForm.get(['eventName']).value,
      hotkey: this.editForm.get(['hotkey']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISysPermission>>) {
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
