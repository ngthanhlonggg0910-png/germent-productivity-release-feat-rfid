import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IKhacPhucLoi } from 'app/shared/model/khac-phuc-loi.model';
import { KhacPhucLoiService } from './khac-phuc-loi.service';

@Component({
  selector: 'jhi-khac-phuc-loi-delete-dialog',
  templateUrl: './khac-phuc-loi-delete-dialog.component.html'
})
export class KhacPhucLoiDeleteDialogComponent {
  khacPhucLoi: IKhacPhucLoi;

  constructor(
    protected khacPhucLoiService: KhacPhucLoiService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.khacPhucLoiService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'khacPhucLoiListModification',
        content: 'Deleted an khacPhucLoi'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-khac-phuc-loi-delete-popup',
  template: ''
})
export class KhacPhucLoiDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ khacPhucLoi }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(KhacPhucLoiDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.khacPhucLoi = khacPhucLoi;
        this.ngbModalRef.result.then(
          () => {
            this.router.navigate(['/khac-phuc-loi', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          () => {
            this.router.navigate(['/khac-phuc-loi', { outlets: { popup: null } }]);
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
