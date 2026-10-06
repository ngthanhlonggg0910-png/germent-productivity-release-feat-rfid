import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDoKimMd } from 'app/shared/model/do-kim-md.model';
import { DoKimMdService } from './do-kim-md.service';

@Component({
  selector: 'jhi-do-kim-md-delete-dialog',
  templateUrl: './do-kim-md-delete-dialog.component.html'
})
export class DoKimMdDeleteDialogComponent {
  doKimMd: IDoKimMd;

  constructor(protected doKimMdService: DoKimMdService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.doKimMdService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'doKimMdListModification',
        content: 'Deleted an doKimMd'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-do-kim-md-delete-popup',
  template: ''
})
export class DoKimMdDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ doKimMd }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(DoKimMdDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.doKimMd = doKimMd;
        this.ngbModalRef.result.then(
          () => {
            this.router.navigate(['/do-kim-md', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          () => {
            this.router.navigate(['/do-kim-md', { outlets: { popup: null } }]);
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
