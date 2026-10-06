import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IThongSoDoSP } from 'app/shared/model/thong-so-do-sp.model';
import { ThongSoDoSPService } from './thong-so-do-sp.service';

@Component({
  selector: 'jhi-thong-so-do-sp-delete-dialog',
  templateUrl: './thong-so-do-sp-delete-dialog.component.html'
})
export class ThongSoDoSPDeleteDialogComponent {
  thongSoDoSP: IThongSoDoSP;

  constructor(
    protected thongSoDoSPService: ThongSoDoSPService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.thongSoDoSPService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'thongSoDoSPListModification',
        content: 'Deleted an thongSoDoSP'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-thong-so-do-sp-delete-popup',
  template: ''
})
export class ThongSoDoSPDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ thongSoDoSP }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(ThongSoDoSPDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.thongSoDoSP = thongSoDoSP;
        this.ngbModalRef.result.then(
          () => {
            this.router.navigate(['/thong-so-do-sp', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          () => {
            this.router.navigate(['/thong-so-do-sp', { outlets: { popup: null } }]);
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
