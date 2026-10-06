import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IQuyCachNPL } from 'app/shared/model/quy-cach-npl.model';
import { QuyCachNPLService } from './quy-cach-npl.service';

@Component({
  selector: 'jhi-quy-cach-npl-delete-dialog',
  templateUrl: './quy-cach-npl-delete-dialog.component.html'
})
export class QuyCachNPLDeleteDialogComponent {
  quyCachNPL: IQuyCachNPL;

  constructor(
    protected quyCachNPLService: QuyCachNPLService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.quyCachNPLService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'quyCachNPLListModification',
        content: 'Deleted an quyCachNPL'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-quy-cach-npl-delete-popup',
  template: ''
})
export class QuyCachNPLDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ quyCachNPL }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(QuyCachNPLDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.quyCachNPL = quyCachNPL;
        this.ngbModalRef.result.then(
          () => {
            this.router.navigate(['/quy-cach-npl', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          () => {
            this.router.navigate(['/quy-cach-npl', { outlets: { popup: null } }]);
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
