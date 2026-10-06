import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IProRefinalChiTietLoi } from 'app/shared/model/pro-refinal-chi-tiet-loi.model';
import { ProRefinalChiTietLoiService } from './pro-refinal-chi-tiet-loi.service';

@Component({
  selector: 'jhi-pro-refinal-chi-tiet-loi-delete-dialog',
  templateUrl: './pro-refinal-chi-tiet-loi-delete-dialog.component.html'
})
export class ProRefinalChiTietLoiDeleteDialogComponent {
  proRefinalChiTietLoi: IProRefinalChiTietLoi;

  constructor(
    protected proRefinalChiTietLoiService: ProRefinalChiTietLoiService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.proRefinalChiTietLoiService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'proRefinalChiTietLoiListModification',
        content: 'Deleted an proRefinalChiTietLoi'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-pro-refinal-chi-tiet-loi-delete-popup',
  template: ''
})
export class ProRefinalChiTietLoiDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ proRefinalChiTietLoi }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(ProRefinalChiTietLoiDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.proRefinalChiTietLoi = proRefinalChiTietLoi;
        this.ngbModalRef.result.then(
          () => {
            this.router.navigate(['/pro-refinal-chi-tiet-loi', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          () => {
            this.router.navigate(['/pro-refinal-chi-tiet-loi', { outlets: { popup: null } }]);
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
