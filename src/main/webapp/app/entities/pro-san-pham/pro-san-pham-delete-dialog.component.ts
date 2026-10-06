import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IProSanPham } from 'app/shared/model/pro-san-pham.model';
import { ProSanPhamService } from './pro-san-pham.service';

@Component({
  selector: 'jhi-pro-san-pham-delete-dialog',
  templateUrl: './pro-san-pham-delete-dialog.component.html'
})
export class ProSanPhamDeleteDialogComponent {
  proSanPham: IProSanPham;

  constructor(
    protected proSanPhamService: ProSanPhamService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.proSanPhamService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'proSanPhamListModification',
        content: 'Deleted an proSanPham'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-pro-san-pham-delete-popup',
  template: ''
})
export class ProSanPhamDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ proSanPham }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(ProSanPhamDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.proSanPham = proSanPham;
        this.ngbModalRef.result.then(
          () => {
            this.router.navigate(['/pro-san-pham', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          () => {
            this.router.navigate(['/pro-san-pham', { outlets: { popup: null } }]);
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
