import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IProRefinalCCChiTietLoi } from 'app/shared/model/pro-refinal-cc-chi-tiet-loi.model';
import { ProRefinalCCChiTietLoiService } from './pro-refinal-cc-chi-tiet-loi.service';

@Component({
  selector: 'jhi-pro-refinal-cc-chi-tiet-loi-delete-dialog',
  templateUrl: './pro-refinal-cc-chi-tiet-loi-delete-dialog.component.html'
})
export class ProRefinalCCChiTietLoiDeleteDialogComponent {
  proRefinalCCChiTietLoi: IProRefinalCCChiTietLoi;

  constructor(
    protected proRefinalCCChiTietLoiService: ProRefinalCCChiTietLoiService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.proRefinalCCChiTietLoiService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'proRefinalCCChiTietLoiListModification',
        content: 'Deleted an proRefinalCCChiTietLoi'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-pro-refinal-cc-chi-tiet-loi-delete-popup',
  template: ''
})
export class ProRefinalCCChiTietLoiDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ proRefinalCCChiTietLoi }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(ProRefinalCCChiTietLoiDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.proRefinalCCChiTietLoi = proRefinalCCChiTietLoi;
        this.ngbModalRef.result.then(
          () => {
            this.router.navigate(['/pro-refinal-cc-chi-tiet-loi', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          () => {
            this.router.navigate(['/pro-refinal-cc-chi-tiet-loi', { outlets: { popup: null } }]);
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
