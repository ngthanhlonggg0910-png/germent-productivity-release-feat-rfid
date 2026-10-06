import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IProNhaMay } from 'app/shared/model/pro-nha-may.model';
import { ProNhaMayService } from './pro-nha-may.service';

@Component({
  selector: 'jhi-pro-nha-may-delete-dialog',
  templateUrl: './pro-nha-may-delete-dialog.component.html'
})
export class ProNhaMayDeleteDialogComponent {
  proNhaMay: IProNhaMay;

  constructor(protected proNhaMayService: ProNhaMayService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.proNhaMayService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'proNhaMayListModification',
        content: 'Deleted an proNhaMay'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-pro-nha-may-delete-popup',
  template: ''
})
export class ProNhaMayDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ proNhaMay }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(ProNhaMayDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.proNhaMay = proNhaMay;
        this.ngbModalRef.result.then(
          () => {
            this.router.navigate(['/pro-nha-may', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          () => {
            this.router.navigate(['/pro-nha-may', { outlets: { popup: null } }]);
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
