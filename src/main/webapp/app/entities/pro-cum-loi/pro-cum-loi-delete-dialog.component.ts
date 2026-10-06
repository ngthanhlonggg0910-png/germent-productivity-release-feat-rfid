import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IProCumLoi } from 'app/shared/model/pro-cum-loi.model';
import { ProCumLoiService } from './pro-cum-loi.service';

@Component({
  selector: 'jhi-pro-cum-loi-delete-dialog',
  templateUrl: './pro-cum-loi-delete-dialog.component.html'
})
export class ProCumLoiDeleteDialogComponent {
  proCumLoi: IProCumLoi;

  constructor(protected proCumLoiService: ProCumLoiService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.proCumLoiService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'proCumLoiListModification',
        content: 'Deleted an proCumLoi'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-pro-cum-loi-delete-popup',
  template: ''
})
export class ProCumLoiDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ proCumLoi }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(ProCumLoiDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.proCumLoi = proCumLoi;
        this.ngbModalRef.result.then(
          () => {
            this.router.navigate(['/pro-cum-loi', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          () => {
            this.router.navigate(['/pro-cum-loi', { outlets: { popup: null } }]);
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
