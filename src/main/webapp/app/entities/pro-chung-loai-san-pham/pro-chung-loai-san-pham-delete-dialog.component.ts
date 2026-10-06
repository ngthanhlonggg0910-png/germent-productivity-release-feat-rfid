import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IProChungLoaiSanPham } from 'app/shared/model/pro-chung-loai-san-pham.model';
import { ProChungLoaiSanPhamService } from './pro-chung-loai-san-pham.service';

@Component({
  selector: 'jhi-pro-chung-loai-san-pham-delete-dialog',
  templateUrl: './pro-chung-loai-san-pham-delete-dialog.component.html'
})
export class ProChungLoaiSanPhamDeleteDialogComponent {
  proChungLoaiSanPham: IProChungLoaiSanPham;

  constructor(
    protected proChungLoaiSanPhamService: ProChungLoaiSanPhamService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.proChungLoaiSanPhamService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'proChungLoaiSanPhamListModification',
        content: 'Deleted an proChungLoaiSanPham'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-pro-chung-loai-san-pham-delete-popup',
  template: ''
})
export class ProChungLoaiSanPhamDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ proChungLoaiSanPham }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(ProChungLoaiSanPhamDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.proChungLoaiSanPham = proChungLoaiSanPham;
        this.ngbModalRef.result.then(
          () => {
            this.router.navigate(['/pro-chung-loai-san-pham', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          () => {
            this.router.navigate(['/pro-chung-loai-san-pham', { outlets: { popup: null } }]);
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
