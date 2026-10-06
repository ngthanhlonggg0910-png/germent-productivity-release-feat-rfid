import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISysPermission } from 'app/shared/model/sys-permission.model';
import { SysPermissionService } from './sys-permission.service';

@Component({
  selector: 'jhi-sys-permission-delete-dialog',
  templateUrl: './sys-permission-delete-dialog.component.html'
})
export class SysPermissionDeleteDialogComponent {
  sysPermission: ISysPermission;

  constructor(
    protected sysPermissionService: SysPermissionService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.sysPermissionService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'sysPermissionListModification',
        content: 'Deleted an sysPermission'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-sys-permission-delete-popup',
  template: ''
})
export class SysPermissionDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ sysPermission }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(SysPermissionDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.sysPermission = sysPermission;
        this.ngbModalRef.result.then(
          () => {
            this.router.navigate(['/sys-permission', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          () => {
            this.router.navigate(['/sys-permission', { outlets: { popup: null } }]);
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
