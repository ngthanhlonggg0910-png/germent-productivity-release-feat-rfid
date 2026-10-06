import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISysGroupModule } from 'app/shared/model/sys-group-module.model';
import { SysGroupModuleService } from './sys-group-module.service';

@Component({
  selector: 'jhi-sys-group-module-delete-dialog',
  templateUrl: './sys-group-module-delete-dialog.component.html'
})
export class SysGroupModuleDeleteDialogComponent {
  sysGroupModule: ISysGroupModule;

  constructor(
    protected sysGroupModuleService: SysGroupModuleService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.sysGroupModuleService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'sysGroupModuleListModification',
        content: 'Deleted an sysGroupModule'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-sys-group-module-delete-popup',
  template: ''
})
export class SysGroupModuleDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ sysGroupModule }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(SysGroupModuleDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.sysGroupModule = sysGroupModule;
        this.ngbModalRef.result.then(
          () => {
            this.router.navigate(['/sys-group-module', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          () => {
            this.router.navigate(['/sys-group-module', { outlets: { popup: null } }]);
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
