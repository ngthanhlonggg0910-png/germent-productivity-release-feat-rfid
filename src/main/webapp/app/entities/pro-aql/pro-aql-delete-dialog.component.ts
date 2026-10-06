import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IProAql } from 'app/shared/model/pro-aql.model';
import { ProAqlService } from './pro-aql.service';

@Component({
  selector: 'jhi-pro-aql-delete-dialog',
  templateUrl: './pro-aql-delete-dialog.component.html'
})
export class ProAqlDeleteDialogComponent {
  proAql: IProAql;

  constructor(protected proAqlService: ProAqlService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.proAqlService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'proAqlListModification',
        content: 'Deleted an proAql'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-pro-aql-delete-popup',
  template: ''
})
export class ProAqlDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ proAql }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(ProAqlDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.proAql = proAql;
        this.ngbModalRef.result.then(
          () => {
            this.router.navigate(['/pro-aql', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          () => {
            this.router.navigate(['/pro-aql', { outlets: { popup: null } }]);
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
