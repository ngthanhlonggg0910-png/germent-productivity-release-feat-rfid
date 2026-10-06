import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';
import { IThongSoDo, ThongSoDo } from 'app/shared/model/thong-so-do.model';
import { ThongSoDoService } from './thong-so-do.service';
import { IDungSai } from 'app/shared/model/dung-sai.model';
import { DungSaiService } from 'app/entities/dung-sai/dung-sai.service';

@Component({
  selector: 'jhi-thong-so-do-update',
  templateUrl: './thong-so-do-update.component.html'
})
export class ThongSoDoUpdateComponent implements OnInit {
  isSaving: boolean;

  dungsais: IDungSai[];

  editForm = this.fb.group({
    id: [],
    code: [null, []],
    ten: [],
    dungSaiId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected thongSoDoService: ThongSoDoService,
    protected dungSaiService: DungSaiService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ thongSoDo }) => {
      this.updateForm(thongSoDo);
    });
    this.dungSaiService
      .query()
      .subscribe((res: HttpResponse<IDungSai[]>) => (this.dungsais = res.body), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(thongSoDo: IThongSoDo) {
    this.editForm.patchValue({
      id: thongSoDo.id,
      code: thongSoDo.code,
      ten: thongSoDo.ten,
      dungSaiId: thongSoDo.dungSaiId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const thongSoDo = this.createFromForm();
    if (thongSoDo.id !== undefined) {
      this.subscribeToSaveResponse(this.thongSoDoService.update(thongSoDo));
    } else {
      this.subscribeToSaveResponse(this.thongSoDoService.create(thongSoDo));
    }
  }

  private createFromForm(): IThongSoDo {
    return {
      ...new ThongSoDo(),
      id: this.editForm.get(['id']).value,
      code: this.editForm.get(['code']).value,
      ten: this.editForm.get(['ten']).value,
      dungSaiId: this.editForm.get(['dungSaiId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IThongSoDo>>) {
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

  trackDungSaiById(index: number, item: IDungSai) {
    return item.id;
  }
}
