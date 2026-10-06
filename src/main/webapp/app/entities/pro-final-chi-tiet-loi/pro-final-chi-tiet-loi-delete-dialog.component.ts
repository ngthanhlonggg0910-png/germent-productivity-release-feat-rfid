import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IProFinalChiTietLoi } from 'app/shared/model/pro-final-chi-tiet-loi.model';
import { ProFinalChiTietLoiService } from './pro-final-chi-tiet-loi.service';

@Component({
  selector: 'jhi-pro-final-chi-tiet-loi-delete-dialog',
  templateUrl: './pro-final-chi-tiet-loi-delete-dialog.component.html'
})
export class ProFinalChiTietLoiDeleteDialogComponent {
  proFinalChiTietLoi: IProFinalChiTietLoi;

  constructor(
    protected proFinalChiTietLoiService: ProFinalChiTietLoiService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.proFinalChiTietLoiService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'proFinalChiTietLoiListModification',
        content: 'Deleted an proFinalChiTietLoi'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-pro-final-chi-tiet-loi-delete-popup',
  template: ''
})
export class ProFinalChiTietLoiDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ proFinalChiTietLoi }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(ProFinalChiTietLoiDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.proFinalChiTietLoi = proFinalChiTietLoi;
        this.ngbModalRef.result.then(
          () => {
            this.router.navigate(['/pro-final-chi-tiet-loi', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          () => {
            this.router.navigate(['/pro-final-chi-tiet-loi', { outlets: { popup: null } }]);
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
