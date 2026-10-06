import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IProNhomCongDoan } from 'app/shared/model/pro-nhom-cong-doan.model';
import { ProNhomCongDoanService } from './pro-nhom-cong-doan.service';

@Component({
  selector: 'jhi-pro-nhom-cong-doan-delete-dialog',
  templateUrl: './pro-nhom-cong-doan-delete-dialog.component.html'
})
export class ProNhomCongDoanDeleteDialogComponent {
  proNhomCongDoan: IProNhomCongDoan;

  constructor(
    protected proNhomCongDoanService: ProNhomCongDoanService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.proNhomCongDoanService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'proNhomCongDoanListModification',
        content: 'Deleted an proNhomCongDoan'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-pro-nhom-cong-doan-delete-popup',
  template: ''
})
export class ProNhomCongDoanDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ proNhomCongDoan }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(ProNhomCongDoanDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.proNhomCongDoan = proNhomCongDoan;
        this.ngbModalRef.result.then(
          () => {
            this.router.navigate(['/pro-nhom-cong-doan', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          () => {
            this.router.navigate(['/pro-nhom-cong-doan', { outlets: { popup: null } }]);
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
