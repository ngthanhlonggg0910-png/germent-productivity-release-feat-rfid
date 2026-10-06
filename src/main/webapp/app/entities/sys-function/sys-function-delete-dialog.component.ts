import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISysFunction } from 'app/shared/model/sys-function.model';
import { SysFunctionService } from './sys-function.service';

@Component({
  selector: 'jhi-sys-function-delete-dialog',
  templateUrl: './sys-function-delete-dialog.component.html'
})
export class SysFunctionDeleteDialogComponent {
  sysFunction: ISysFunction;

  constructor(
    protected sysFunctionService: SysFunctionService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.sysFunctionService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'sysFunctionListModification',
        content: 'Deleted an sysFunction'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-sys-function-delete-popup',
  template: ''
})
export class SysFunctionDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ sysFunction }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(SysFunctionDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.sysFunction = sysFunction;
        this.ngbModalRef.result.then(
          () => {
            this.router.navigate(['/sys-function', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          () => {
            this.router.navigate(['/sys-function', { outlets: { popup: null } }]);
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
