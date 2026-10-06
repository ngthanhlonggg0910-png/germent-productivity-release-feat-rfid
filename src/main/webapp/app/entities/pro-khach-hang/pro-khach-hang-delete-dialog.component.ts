import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IProKhachHang } from 'app/shared/model/pro-khach-hang.model';
import { ProKhachHangService } from './pro-khach-hang.service';

@Component({
  selector: 'jhi-pro-khach-hang-delete-dialog',
  templateUrl: './pro-khach-hang-delete-dialog.component.html'
})
export class ProKhachHangDeleteDialogComponent {
  proKhachHang: IProKhachHang;

  constructor(
    protected proKhachHangService: ProKhachHangService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.proKhachHangService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'proKhachHangListModification',
        content: 'Deleted an proKhachHang'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-pro-khach-hang-delete-popup',
  template: ''
})
export class ProKhachHangDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ proKhachHang }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(ProKhachHangDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.proKhachHang = proKhachHang;
        this.ngbModalRef.result.then(
          () => {
            this.router.navigate(['/pro-khach-hang', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          () => {
            this.router.navigate(['/pro-khach-hang', { outlets: { popup: null } }]);
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
