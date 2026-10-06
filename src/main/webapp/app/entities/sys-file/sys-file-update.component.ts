import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { ISysFile, SysFile } from 'app/shared/model/sys-file.model';
import { SysFileService } from './sys-file.service';

@Component({
  selector: 'jhi-sys-file-update',
  templateUrl: './sys-file-update.component.html'
})
export class SysFileUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    fileName: [],
    fileUri: [],
    fileExtension: [],
    size: [],
    uploadedAt: []
  });

  constructor(protected sysFileService: SysFileService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ sysFile }) => {
      this.updateForm(sysFile);
    });
  }

  updateForm(sysFile: ISysFile) {
    this.editForm.patchValue({
      id: sysFile.id,
      fileName: sysFile.fileName,
      fileUri: sysFile.fileUri,
      fileExtension: sysFile.fileExtension,
      size: sysFile.size,
      uploadedAt: sysFile.uploadedAt != null ? sysFile.uploadedAt.format(DATE_TIME_FORMAT) : null
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const sysFile = this.createFromForm();
    if (sysFile.id !== undefined) {
      this.subscribeToSaveResponse(this.sysFileService.update(sysFile));
    } else {
      this.subscribeToSaveResponse(this.sysFileService.create(sysFile));
    }
  }

  private createFromForm(): ISysFile {
    return {
      ...new SysFile(),
      id: this.editForm.get(['id']).value,
      fileName: this.editForm.get(['fileName']).value,
      fileUri: this.editForm.get(['fileUri']).value,
      fileExtension: this.editForm.get(['fileExtension']).value,
      size: this.editForm.get(['size']).value,
      uploadedAt:
        this.editForm.get(['uploadedAt']).value != null ? moment(this.editForm.get(['uploadedAt']).value, DATE_TIME_FORMAT) : undefined
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISysFile>>) {
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
