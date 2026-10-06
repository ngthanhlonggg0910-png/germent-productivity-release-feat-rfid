import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';
import { ISysFunction, SysFunction } from 'app/shared/model/sys-function.model';
import { SysFunctionService } from './sys-function.service';
import { ISysPermission } from 'app/shared/model/sys-permission.model';
import { SysPermissionService } from 'app/entities/sys-permission/sys-permission.service';
import { ISysModule } from 'app/shared/model/sys-module.model';
import { SysModuleService } from 'app/entities/sys-module/sys-module.service';

@Component({
  selector: 'jhi-sys-function-update',
  templateUrl: './sys-function-update.component.html'
})
export class SysFunctionUpdateComponent implements OnInit {
  isSaving: boolean;

  syspermissions: ISysPermission[];

  sysmodules: ISysModule[];

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.required, Validators.maxLength(100)]],
    caption: [null, [Validators.maxLength(255)]],
    level: [],
    imageUri: [],
    eventName: [null, [Validators.maxLength(50)]],
    hotkey: [null, [Validators.maxLength(5)]],
    sysPermissionId: [],
    sysModuleId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected sysFunctionService: SysFunctionService,
    protected sysPermissionService: SysPermissionService,
    protected sysModuleService: SysModuleService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ sysFunction }) => {
      this.updateForm(sysFunction);
    });
    this.sysPermissionService
      .query()
      .subscribe(
        (res: HttpResponse<ISysPermission[]>) => (this.syspermissions = res.body),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
    this.sysModuleService
      .query()
      .subscribe((res: HttpResponse<ISysModule[]>) => (this.sysmodules = res.body), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(sysFunction: ISysFunction) {
    this.editForm.patchValue({
      id: sysFunction.id,
      name: sysFunction.name,
      caption: sysFunction.caption,
      level: sysFunction.level,
      imageUri: sysFunction.imageUri,
      eventName: sysFunction.eventName,
      hotkey: sysFunction.hotkey,
      sysPermissionId: sysFunction.sysPermissionId,
      sysModuleId: sysFunction.sysModuleId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const sysFunction = this.createFromForm();
    if (sysFunction.id !== undefined) {
      this.subscribeToSaveResponse(this.sysFunctionService.update(sysFunction));
    } else {
      this.subscribeToSaveResponse(this.sysFunctionService.create(sysFunction));
    }
  }

  private createFromForm(): ISysFunction {
    return {
      ...new SysFunction(),
      id: this.editForm.get(['id']).value,
      name: this.editForm.get(['name']).value,
      caption: this.editForm.get(['caption']).value,
      level: this.editForm.get(['level']).value,
      imageUri: this.editForm.get(['imageUri']).value,
      eventName: this.editForm.get(['eventName']).value,
      hotkey: this.editForm.get(['hotkey']).value,
      sysPermissionId: this.editForm.get(['sysPermissionId']).value,
      sysModuleId: this.editForm.get(['sysModuleId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISysFunction>>) {
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

  trackSysPermissionById(index: number, item: ISysPermission) {
    return item.id;
  }

  trackSysModuleById(index: number, item: ISysModule) {
    return item.id;
  }
}
