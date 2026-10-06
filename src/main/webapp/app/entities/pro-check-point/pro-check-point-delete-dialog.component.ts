import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IProCheckPoint } from 'app/shared/model/pro-check-point.model';
import { ProCheckPointService } from './pro-check-point.service';

@Component({
  selector: 'jhi-pro-check-point-delete-dialog',
  templateUrl: './pro-check-point-delete-dialog.component.html'
})
export class ProCheckPointDeleteDialogComponent {
  proCheckPoint: IProCheckPoint;

  constructor(
    protected proCheckPointService: ProCheckPointService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.proCheckPointService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'proCheckPointListModification',
        content: 'Deleted an proCheckPoint'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-pro-check-point-delete-popup',
  template: ''
})
export class ProCheckPointDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ proCheckPoint }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(ProCheckPointDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.proCheckPoint = proCheckPoint;
        this.ngbModalRef.result.then(
          () => {
            this.router.navigate(['/pro-check-point', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          () => {
            this.router.navigate(['/pro-check-point', { outlets: { popup: null } }]);
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
