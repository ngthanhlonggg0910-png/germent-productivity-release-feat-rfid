import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IProGioLamViec } from 'app/shared/model/pro-gio-lam-viec.model';
import { ProGioLamViecService } from './pro-gio-lam-viec.service';

@Component({
  selector: 'jhi-pro-gio-lam-viec-delete-dialog',
  templateUrl: './pro-gio-lam-viec-delete-dialog.component.html'
})
export class ProGioLamViecDeleteDialogComponent {
  proGioLamViec: IProGioLamViec;

  constructor(
    protected proGioLamViecService: ProGioLamViecService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.proGioLamViecService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'proGioLamViecListModification',
        content: 'Deleted an proGioLamViec'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-pro-gio-lam-viec-delete-popup',
  template: ''
})
export class ProGioLamViecDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ proGioLamViec }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(ProGioLamViecDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.proGioLamViec = proGioLamViec;
        this.ngbModalRef.result.then(
          () => {
            this.router.navigate(['/pro-gio-lam-viec', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          () => {
            this.router.navigate(['/pro-gio-lam-viec', { outlets: { popup: null } }]);
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
