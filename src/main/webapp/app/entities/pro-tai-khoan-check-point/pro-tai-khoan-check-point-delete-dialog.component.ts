import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IProTaiKhoanCheckPoint } from 'app/shared/model/pro-tai-khoan-check-point.model';
import { ProTaiKhoanCheckPointService } from './pro-tai-khoan-check-point.service';

@Component({
  selector: 'jhi-pro-tai-khoan-check-point-delete-dialog',
  templateUrl: './pro-tai-khoan-check-point-delete-dialog.component.html'
})
export class ProTaiKhoanCheckPointDeleteDialogComponent {
  proTaiKhoanCheckPoint: IProTaiKhoanCheckPoint;

  constructor(
    protected proTaiKhoanCheckPointService: ProTaiKhoanCheckPointService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.proTaiKhoanCheckPointService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'proTaiKhoanCheckPointListModification',
        content: 'Deleted an proTaiKhoanCheckPoint'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-pro-tai-khoan-check-point-delete-popup',
  template: ''
})
export class ProTaiKhoanCheckPointDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ proTaiKhoanCheckPoint }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(ProTaiKhoanCheckPointDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.proTaiKhoanCheckPoint = proTaiKhoanCheckPoint;
        this.ngbModalRef.result.then(
          () => {
            this.router.navigate(['/pro-tai-khoan-check-point', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          () => {
            this.router.navigate(['/pro-tai-khoan-check-point', { outlets: { popup: null } }]);
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
