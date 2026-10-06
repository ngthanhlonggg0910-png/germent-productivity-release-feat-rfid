import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IThuVienLoi } from 'app/shared/model/thu-vien-loi.model';
import { ThuVienLoiService } from './thu-vien-loi.service';

@Component({
  selector: 'jhi-thu-vien-loi-delete-dialog',
  templateUrl: './thu-vien-loi-delete-dialog.component.html'
})
export class ThuVienLoiDeleteDialogComponent {
  thuVienLoi: IThuVienLoi;

  constructor(
    protected thuVienLoiService: ThuVienLoiService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.thuVienLoiService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'thuVienLoiListModification',
        content: 'Deleted an thuVienLoi'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-thu-vien-loi-delete-popup',
  template: ''
})
export class ThuVienLoiDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ thuVienLoi }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(ThuVienLoiDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.thuVienLoi = thuVienLoi;
        this.ngbModalRef.result.then(
          () => {
            this.router.navigate(['/thu-vien-loi', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          () => {
            this.router.navigate(['/thu-vien-loi', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          }
        );
      }, 0);
    });
  }

  ngOnDestroy() {
    this.ngbModalRef = null;
  }
}
