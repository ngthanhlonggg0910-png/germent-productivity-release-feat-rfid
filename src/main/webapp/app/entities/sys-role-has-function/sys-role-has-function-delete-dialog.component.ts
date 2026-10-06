import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISysRoleHasFunction } from 'app/shared/model/sys-role-has-function.model';
import { SysRoleHasFunctionService } from './sys-role-has-function.service';

@Component({
  selector: 'jhi-sys-role-has-function-delete-dialog',
  templateUrl: './sys-role-has-function-delete-dialog.component.html'
})
export class SysRoleHasFunctionDeleteDialogComponent {
  sysRoleHasFunction: ISysRoleHasFunction;

  constructor(
    protected sysRoleHasFunctionService: SysRoleHasFunctionService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.sysRoleHasFunctionService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'sysRoleHasFunctionListModification',
        content: 'Deleted an sysRoleHasFunction'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-sys-role-has-function-delete-popup',
  template: ''
})
export class SysRoleHasFunctionDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ sysRoleHasFunction }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(SysRoleHasFunctionDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.sysRoleHasFunction = sysRoleHasFunction;
        this.ngbModalRef.result.then(
          () => {
            this.router.navigate(['/sys-role-has-function', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          () => {
            this.router.navigate(['/sys-role-has-function', { outlets: { popup: null } }]);
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
