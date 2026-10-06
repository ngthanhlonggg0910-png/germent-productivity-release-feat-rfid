import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IProCongTy } from 'app/shared/model/pro-cong-ty.model';
import { ProCongTyService } from './pro-cong-ty.service';

@Component({
  selector: 'jhi-pro-cong-ty-delete-dialog',
  templateUrl: './pro-cong-ty-delete-dialog.component.html'
})
export class ProCongTyDeleteDialogComponent {
  proCongTy: IProCongTy;

  constructor(protected proCongTyService: ProCongTyService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.proCongTyService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'proCongTyListModification',
        content: 'Deleted an proCongTy'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-pro-cong-ty-delete-popup',
  template: ''
})
export class ProCongTyDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ proCongTy }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(ProCongTyDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.proCongTy = proCongTy;
        this.ngbModalRef.result.then(
          () => {
            this.router.navigate(['/pro-cong-ty', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          () => {
            this.router.navigate(['/pro-cong-ty', { outlets: { popup: null } }]);
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
