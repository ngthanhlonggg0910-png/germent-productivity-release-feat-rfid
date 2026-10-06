import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IProFinal } from 'app/shared/model/pro-final.model';
import { ProFinalService } from './pro-final.service';

@Component({
  selector: 'jhi-pro-final-delete-dialog',
  templateUrl: './pro-final-delete-dialog.component.html'
})
export class ProFinalDeleteDialogComponent {
  proFinal: IProFinal;

  constructor(protected proFinalService: ProFinalService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.proFinalService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'proFinalListModification',
        content: 'Deleted an proFinal'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-pro-final-delete-popup',
  template: ''
})
export class ProFinalDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ proFinal }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(ProFinalDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.proFinal = proFinal;
        this.ngbModalRef.result.then(
          () => {
            this.router.navigate(['/pro-final', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          () => {
            this.router.navigate(['/pro-final', { outlets: { popup: null } }]);
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
