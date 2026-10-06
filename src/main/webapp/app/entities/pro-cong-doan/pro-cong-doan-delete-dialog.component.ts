import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IProCongDoan } from 'app/shared/model/pro-cong-doan.model';
import { ProCongDoanService } from './pro-cong-doan.service';

@Component({
  selector: 'jhi-pro-cong-doan-delete-dialog',
  templateUrl: './pro-cong-doan-delete-dialog.component.html'
})
export class ProCongDoanDeleteDialogComponent {
  proCongDoan: IProCongDoan;

  constructor(
    protected proCongDoanService: ProCongDoanService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.proCongDoanService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'proCongDoanListModification',
        content: 'Deleted an proCongDoan'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-pro-cong-doan-delete-popup',
  template: ''
})
export class ProCongDoanDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ proCongDoan }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(ProCongDoanDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.proCongDoan = proCongDoan;
        this.ngbModalRef.result.then(
          () => {
            this.router.navigate(['/pro-cong-doan', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          () => {
            this.router.navigate(['/pro-cong-doan', { outlets: { popup: null } }]);
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
