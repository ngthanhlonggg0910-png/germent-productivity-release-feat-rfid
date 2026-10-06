import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IProChuyen } from 'app/shared/model/pro-chuyen.model';
import { ProChuyenService } from './pro-chuyen.service';

@Component({
  selector: 'jhi-pro-chuyen-delete-dialog',
  templateUrl: './pro-chuyen-delete-dialog.component.html'
})
export class ProChuyenDeleteDialogComponent {
  proChuyen: IProChuyen;

  constructor(protected proChuyenService: ProChuyenService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.proChuyenService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'proChuyenListModification',
        content: 'Deleted an proChuyen'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-pro-chuyen-delete-popup',
  template: ''
})
export class ProChuyenDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ proChuyen }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(ProChuyenDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.proChuyen = proChuyen;
        this.ngbModalRef.result.then(
          () => {
            this.router.navigate(['/pro-chuyen', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          () => {
            this.router.navigate(['/pro-chuyen', { outlets: { popup: null } }]);
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
