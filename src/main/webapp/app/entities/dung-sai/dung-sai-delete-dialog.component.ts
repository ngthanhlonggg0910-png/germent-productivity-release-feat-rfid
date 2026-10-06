import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDungSai } from 'app/shared/model/dung-sai.model';
import { DungSaiService } from './dung-sai.service';

@Component({
  selector: 'jhi-dung-sai-delete-dialog',
  templateUrl: './dung-sai-delete-dialog.component.html'
})
export class DungSaiDeleteDialogComponent {
  dungSai: IDungSai;

  constructor(protected dungSaiService: DungSaiService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.dungSaiService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'dungSaiListModification',
        content: 'Deleted an dungSai'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-dung-sai-delete-popup',
  template: ''
})
export class DungSaiDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ dungSai }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(DungSaiDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.dungSai = dungSai;
        this.ngbModalRef.result.then(
          () => {
            this.router.navigate(['/dung-sai', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          () => {
            this.router.navigate(['/dung-sai', { outlets: { popup: null } }]);
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
