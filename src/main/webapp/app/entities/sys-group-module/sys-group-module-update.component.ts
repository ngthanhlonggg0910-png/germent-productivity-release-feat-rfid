import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { ISysGroupModule, SysGroupModule } from 'app/shared/model/sys-group-module.model';
import { SysGroupModuleService } from './sys-group-module.service';

@Component({
  selector: 'jhi-sys-group-module-update',
  templateUrl: './sys-group-module-update.component.html'
})
export class SysGroupModuleUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.required, Validators.maxLength(100)]],
    caption: [null, [Validators.maxLength(255)]],
    primaryImageUri: [],
    secondaryImageUri: []
  });

  constructor(protected sysGroupModuleService: SysGroupModuleService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ sysGroupModule }) => {
      this.updateForm(sysGroupModule);
    });
  }

  updateForm(sysGroupModule: ISysGroupModule) {
    this.editForm.patchValue({
      id: sysGroupModule.id,
      name: sysGroupModule.name,
      caption: sysGroupModule.caption,
      primaryImageUri: sysGroupModule.primaryImageUri,
      secondaryImageUri: sysGroupModule.secondaryImageUri
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const sysGroupModule = this.createFromForm();
    if (sysGroupModule.id !== undefined) {
      this.subscribeToSaveResponse(this.sysGroupModuleService.update(sysGroupModule));
    } else {
      this.subscribeToSaveResponse(this.sysGroupModuleService.create(sysGroupModule));
    }
  }

  private createFromForm(): ISysGroupModule {
    return {
      ...new SysGroupModule(),
      id: this.editForm.get(['id']).value,
      name: this.editForm.get(['name']).value,
      caption: this.editForm.get(['caption']).value,
      primaryImageUri: this.editForm.get(['primaryImageUri']).value,
      secondaryImageUri: this.editForm.get(['secondaryImageUri']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISysGroupModule>>) {
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
