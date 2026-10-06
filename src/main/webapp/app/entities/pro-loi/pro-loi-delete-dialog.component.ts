import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IProLoi } from 'app/shared/model/pro-loi.model';
import { ProLoiService } from './pro-loi.service';

@Component({
  selector: 'jhi-pro-loi-delete-dialog',
  templateUrl: './pro-loi-delete-dialog.component.html'
})
export class ProLoiDeleteDialogComponent {
  proLoi: IProLoi;

  constructor(protected proLoiService: ProLoiService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.proLoiService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'proLoiListModification',
        content: 'Deleted an proLoi'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-pro-loi-delete-popup',
  template: ''
})
export class ProLoiDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ proLoi }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(ProLoiDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.proLoi = proLoi;
        this.ngbModalRef.result.then(
          () => {
            this.router.navigate(['/pro-loi', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          () => {
            this.router.navigate(['/pro-loi', { outlets: { popup: null } }]);
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
