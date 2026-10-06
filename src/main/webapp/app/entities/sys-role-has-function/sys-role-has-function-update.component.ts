import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';
import { ISysRoleHasFunction, SysRoleHasFunction } from 'app/shared/model/sys-role-has-function.model';
import { SysRoleHasFunctionService } from './sys-role-has-function.service';
import { ISysFunction } from 'app/shared/model/sys-function.model';
import { SysFunctionService } from 'app/entities/sys-function/sys-function.service';
import { UserService } from 'app/core/user/user.service';

@Component({
  selector: 'jhi-sys-role-has-function-update',
  templateUrl: './sys-role-has-function-update.component.html'
})
export class SysRoleHasFunctionUpdateComponent implements OnInit {
  isSaving: boolean;

  sysfunctions: ISysFunction[];

  authorities: any[];

  editForm = this.fb.group({
    id: [],
    activated: [],
    sysFunctionId: [],
    authorityName: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected sysRoleHasFunctionService: SysRoleHasFunctionService,
    protected sysFunctionService: SysFunctionService,
    protected activatedRoute: ActivatedRoute,
    private userService: UserService,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ sysRoleHasFunction }) => {
      this.updateForm(sysRoleHasFunction);
    });
    this.sysFunctionService
      .query()
      .subscribe(
        (res: HttpResponse<ISysFunction[]>) => (this.sysfunctions = res.body),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
    this.userService.authorities().subscribe(authorities => {
      this.authorities = authorities;
    });
  }

  updateForm(sysRoleHasFunction: ISysRoleHasFunction) {
    this.editForm.patchValue({
      id: sysRoleHasFunction.id,
      activated: sysRoleHasFunction.activated,
      sysFunctionId: sysRoleHasFunction.sysFunctionId,
      authorityName: sysRoleHasFunction.authorityName
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const sysRoleHasFunction = this.createFromForm();
    if (sysRoleHasFunction.id !== undefined) {
      this.subscribeToSaveResponse(this.sysRoleHasFunctionService.update(sysRoleHasFunction));
    } else {
      this.subscribeToSaveResponse(this.sysRoleHasFunctionService.create(sysRoleHasFunction));
    }
  }

  private createFromForm(): ISysRoleHasFunction {
    return {
      ...new SysRoleHasFunction(),
      id: this.editForm.get(['id']).value,
      activated: this.editForm.get(['activated']).value,
      sysFunctionId: this.editForm.get(['sysFunctionId']).value,
      authorityName: this.editForm.get(['authorityName']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISysRoleHasFunction>>) {
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

  trackSysFunctionById(index: number, item: ISysFunction) {
    return item.id;
  }

  trackAuthorityById(index: number, item: any) {
    return item.id;
  }
}
