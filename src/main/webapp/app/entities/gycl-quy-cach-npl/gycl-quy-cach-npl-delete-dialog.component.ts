import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IGYCLQuyCachNPL } from 'app/shared/model/gycl-quy-cach-npl.model';
import { GYCLQuyCachNPLService } from './gycl-quy-cach-npl.service';

@Component({
  selector: 'jhi-gycl-quy-cach-npl-delete-dialog',
  templateUrl: './gycl-quy-cach-npl-delete-dialog.component.html'
})
export class GYCLQuyCachNPLDeleteDialogComponent {
  gYCLQuyCachNPL: IGYCLQuyCachNPL;

  constructor(
    protected gYCLQuyCachNPLService: GYCLQuyCachNPLService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.gYCLQuyCachNPLService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'gYCLQuyCachNPLListModification',
        content: 'Deleted an gYCLQuyCachNPL'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-gycl-quy-cach-npl-delete-popup',
  template: ''
})
export class GYCLQuyCachNPLDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ gYCLQuyCachNPL }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(GYCLQuyCachNPLDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.gYCLQuyCachNPL = gYCLQuyCachNPL;
        this.ngbModalRef.result.then(
          () => {
            this.router.navigate(['/gycl-quy-cach-npl', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          () => {
            this.router.navigate(['/gycl-quy-cach-npl', { outlets: { popup: null } }]);
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
