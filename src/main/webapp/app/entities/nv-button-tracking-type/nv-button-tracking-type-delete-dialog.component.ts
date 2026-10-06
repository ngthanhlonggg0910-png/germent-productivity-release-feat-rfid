import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { INVButtonTrackingType } from 'app/shared/model/nv-button-tracking-type.model';
import { NVButtonTrackingTypeService } from './nv-button-tracking-type.service';

@Component({
  selector: 'jhi-nv-button-tracking-type-delete-dialog',
  templateUrl: './nv-button-tracking-type-delete-dialog.component.html'
})
export class NVButtonTrackingTypeDeleteDialogComponent {
  nVButtonTrackingType: INVButtonTrackingType;

  constructor(
    protected nVButtonTrackingTypeService: NVButtonTrackingTypeService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.nVButtonTrackingTypeService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'nVButtonTrackingTypeListModification',
        content: 'Deleted an nVButtonTrackingType'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-nv-button-tracking-type-delete-popup',
  template: ''
})
export class NVButtonTrackingTypeDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ nVButtonTrackingType }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(NVButtonTrackingTypeDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.nVButtonTrackingType = nVButtonTrackingType;
        this.ngbModalRef.result.then(
          () => {
            this.router.navigate(['/nv-button-tracking-type', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          () => {
            this.router.navigate(['/nv-button-tracking-type', { outlets: { popup: null } }]);
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
