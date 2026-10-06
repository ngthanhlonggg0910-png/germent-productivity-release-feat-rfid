import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IGYCLKhacPhuc } from 'app/shared/model/gycl-khac-phuc.model';
import { GYCLKhacPhucService } from './gycl-khac-phuc.service';

@Component({
  selector: 'jhi-gycl-khac-phuc-delete-dialog',
  templateUrl: './gycl-khac-phuc-delete-dialog.component.html'
})
export class GYCLKhacPhucDeleteDialogComponent {
  gYCLKhacPhuc: IGYCLKhacPhuc;

  constructor(
    protected gYCLKhacPhucService: GYCLKhacPhucService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.gYCLKhacPhucService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'gYCLKhacPhucListModification',
        content: 'Deleted an gYCLKhacPhuc'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-gycl-khac-phuc-delete-popup',
  template: ''
})
export class GYCLKhacPhucDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ gYCLKhacPhuc }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(GYCLKhacPhucDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.gYCLKhacPhuc = gYCLKhacPhuc;
        this.ngbModalRef.result.then(
          () => {
            this.router.navigate(['/gycl-khac-phuc', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          () => {
            this.router.navigate(['/gycl-khac-phuc', { outlets: { popup: null } }]);
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
