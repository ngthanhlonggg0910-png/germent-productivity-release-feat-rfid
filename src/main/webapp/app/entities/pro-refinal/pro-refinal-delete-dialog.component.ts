import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IProRefinal } from 'app/shared/model/pro-refinal.model';
import { ProRefinalService } from './pro-refinal.service';

@Component({
  selector: 'jhi-pro-refinal-delete-dialog',
  templateUrl: './pro-refinal-delete-dialog.component.html'
})
export class ProRefinalDeleteDialogComponent {
  proRefinal: IProRefinal;

  constructor(
    protected proRefinalService: ProRefinalService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.proRefinalService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'proRefinalListModification',
        content: 'Deleted an proRefinal'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-pro-refinal-delete-popup',
  template: ''
})
export class ProRefinalDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ proRefinal }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(ProRefinalDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.proRefinal = proRefinal;
        this.ngbModalRef.result.then(
          () => {
            this.router.navigate(['/pro-refinal', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          () => {
            this.router.navigate(['/pro-refinal', { outlets: { popup: null } }]);
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
