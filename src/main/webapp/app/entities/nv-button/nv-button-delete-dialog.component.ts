import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { INVButton } from 'app/shared/model/nv-button.model';
import { NVButtonService } from './nv-button.service';

@Component({
  selector: 'jhi-nv-button-delete-dialog',
  templateUrl: './nv-button-delete-dialog.component.html'
})
export class NVButtonDeleteDialogComponent {
  nVButton: INVButton;

  constructor(protected nVButtonService: NVButtonService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.nVButtonService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'nVButtonListModification',
        content: 'Deleted an nVButton'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-nv-button-delete-popup',
  template: ''
})
export class NVButtonDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ nVButton }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(NVButtonDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.nVButton = nVButton;
        this.ngbModalRef.result.then(
          () => {
            this.router.navigate(['/nv-button', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          () => {
            this.router.navigate(['/nv-button', { outlets: { popup: null } }]);
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
