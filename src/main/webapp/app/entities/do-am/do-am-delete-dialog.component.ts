import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDoAm } from 'app/shared/model/do-am.model';
import { DoAmService } from './do-am.service';

@Component({
  selector: 'jhi-do-am-delete-dialog',
  templateUrl: './do-am-delete-dialog.component.html'
})
export class DoAmDeleteDialogComponent {
  doAm: IDoAm;

  constructor(protected doAmService: DoAmService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.doAmService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'doAmListModification',
        content: 'Deleted an doAm'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-do-am-delete-popup',
  template: ''
})
export class DoAmDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ doAm }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(DoAmDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.doAm = doAm;
        this.ngbModalRef.result.then(
          () => {
            this.router.navigate(['/do-am', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          () => {
            this.router.navigate(['/do-am', { outlets: { popup: null } }]);
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
