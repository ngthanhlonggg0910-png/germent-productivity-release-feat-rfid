import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IProNSCLChiTietLoi } from 'app/shared/model/pro-nscl-chi-tiet-loi.model';
import { ProNSCLChiTietLoiService } from './pro-nscl-chi-tiet-loi.service';

@Component({
  selector: 'jhi-pro-nscl-chi-tiet-loi-delete-dialog',
  templateUrl: './pro-nscl-chi-tiet-loi-delete-dialog.component.html'
})
export class ProNSCLChiTietLoiDeleteDialogComponent {
  proNSCLChiTietLoi: IProNSCLChiTietLoi;

  constructor(
    protected proNSCLChiTietLoiService: ProNSCLChiTietLoiService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.proNSCLChiTietLoiService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'proNSCLChiTietLoiListModification',
        content: 'Deleted an proNSCLChiTietLoi'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-pro-nscl-chi-tiet-loi-delete-popup',
  template: ''
})
export class ProNSCLChiTietLoiDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ proNSCLChiTietLoi }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(ProNSCLChiTietLoiDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.proNSCLChiTietLoi = proNSCLChiTietLoi;
        this.ngbModalRef.result.then(
          () => {
            this.router.navigate(['/pro-nscl-chi-tiet-loi', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          () => {
            this.router.navigate(['/pro-nscl-chi-tiet-loi', { outlets: { popup: null } }]);
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
