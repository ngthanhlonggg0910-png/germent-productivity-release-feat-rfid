import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';
import { ISysModule, SysModule } from 'app/shared/model/sys-module.model';
import { SysModuleService } from './sys-module.service';
import { ISysGroupModule } from 'app/shared/model/sys-group-module.model';
import { SysGroupModuleService } from 'app/entities/sys-group-module/sys-group-module.service';

@Component({
  selector: 'jhi-sys-module-update',
  templateUrl: './sys-module-update.component.html'
})
export class SysModuleUpdateComponent implements OnInit {
  isSaving: boolean;

  sysgroupmodules: ISysGroupModule[];

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.required, Validators.maxLength(100)]],
    caption: [null, [Validators.maxLength(255)]],
    imageUri: [],
    sysGroupModuleId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected sysModuleService: SysModuleService,
    protected sysGroupModuleService: SysGroupModuleService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ sysModule }) => {
      this.updateForm(sysModule);
    });
    this.sysGroupModuleService
      .query()
      .subscribe(
        (res: HttpResponse<ISysGroupModule[]>) => (this.sysgroupmodules = res.body),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  updateForm(sysModule: ISysModule) {
    this.editForm.patchValue({
      id: sysModule.id,
      name: sysModule.name,
      caption: sysModule.caption,
      imageUri: sysModule.imageUri,
      sysGroupModuleId: sysModule.sysGroupModuleId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const sysModule = this.createFromForm();
    if (sysModule.id !== undefined) {
      this.subscribeToSaveResponse(this.sysModuleService.update(sysModule));
    } else {
      this.subscribeToSaveResponse(this.sysModuleService.create(sysModule));
    }
  }

  private createFromForm(): ISysModule {
    return {
      ...new SysModule(),
      id: this.editForm.get(['id']).value,
      name: this.editForm.get(['name']).value,
      caption: this.editForm.get(['caption']).value,
      imageUri: this.editForm.get(['imageUri']).value,
      sysGroupModuleId: this.editForm.get(['sysGroupModuleId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISysModule>>) {
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

  trackSysGroupModuleById(index: number, item: ISysGroupModule) {
    return item.id;
  }
}
