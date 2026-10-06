import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IButtonTracking } from 'app/shared/model/button-tracking.model';
import { ButtonTrackingService } from './button-tracking.service';

@Component({
  selector: 'jhi-button-tracking-delete-dialog',
  templateUrl: './button-tracking-delete-dialog.component.html'
})
export class ButtonTrackingDeleteDialogComponent {
  buttonTracking: IButtonTracking;

  constructor(
    protected buttonTrackingService: ButtonTrackingService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.buttonTrackingService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'buttonTrackingListModification',
        content: 'Deleted an buttonTracking'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-button-tracking-delete-popup',
  template: ''
})
export class ButtonTrackingDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ buttonTracking }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(ButtonTrackingDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.buttonTracking = buttonTracking;
        this.ngbModalRef.result.then(
          () => {
            this.router.navigate(['/button-tracking', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          () => {
            this.router.navigate(['/button-tracking', { outlets: { popup: null } }]);
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
