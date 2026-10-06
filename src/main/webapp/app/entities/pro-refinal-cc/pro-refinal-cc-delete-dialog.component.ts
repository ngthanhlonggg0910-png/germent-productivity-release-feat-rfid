import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IProRefinalCC } from 'app/shared/model/pro-refinal-cc.model';
import { ProRefinalCCService } from './pro-refinal-cc.service';

@Component({
  selector: 'jhi-pro-refinal-cc-delete-dialog',
  templateUrl: './pro-refinal-cc-delete-dialog.component.html'
})
export class ProRefinalCCDeleteDialogComponent {
  proRefinalCC: IProRefinalCC;

  constructor(
    protected proRefinalCCService: ProRefinalCCService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.proRefinalCCService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'proRefinalCCListModification',
        content: 'Deleted an proRefinalCC'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-pro-refinal-cc-delete-popup',
  template: ''
})
export class ProRefinalCCDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ proRefinalCC }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(ProRefinalCCDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.proRefinalCC = proRefinalCC;
        this.ngbModalRef.result.then(
          () => {
            this.router.navigate(['/pro-refinal-cc', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          () => {
            this.router.navigate(['/pro-refinal-cc', { outlets: { popup: null } }]);
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
