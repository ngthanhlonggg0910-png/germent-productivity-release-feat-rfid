import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IProLoaiChuyen } from 'app/shared/model/pro-loai-chuyen.model';
import { ProLoaiChuyenService } from './pro-loai-chuyen.service';

@Component({
  selector: 'jhi-pro-loai-chuyen-delete-dialog',
  templateUrl: './pro-loai-chuyen-delete-dialog.component.html'
})
export class ProLoaiChuyenDeleteDialogComponent {
  proLoaiChuyen: IProLoaiChuyen;

  constructor(
    protected proLoaiChuyenService: ProLoaiChuyenService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.proLoaiChuyenService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'proLoaiChuyenListModification',
        content: 'Deleted an proLoaiChuyen'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-pro-loai-chuyen-delete-popup',
  template: ''
})
export class ProLoaiChuyenDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ proLoaiChuyen }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(ProLoaiChuyenDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.proLoaiChuyen = proLoaiChuyen;
        this.ngbModalRef.result.then(
          () => {
            this.router.navigate(['/pro-loai-chuyen', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          () => {
            this.router.navigate(['/pro-loai-chuyen', { outlets: { popup: null } }]);
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
