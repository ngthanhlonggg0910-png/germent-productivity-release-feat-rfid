import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IThongSoDoSPSize } from 'app/shared/model/thong-so-do-sp-size.model';
import { ThongSoDoSPSizeService } from './thong-so-do-sp-size.service';

@Component({
  selector: 'jhi-thong-so-do-sp-size-delete-dialog',
  templateUrl: './thong-so-do-sp-size-delete-dialog.component.html'
})
export class ThongSoDoSPSizeDeleteDialogComponent {
  thongSoDoSPSize: IThongSoDoSPSize;

  constructor(
    protected thongSoDoSPSizeService: ThongSoDoSPSizeService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.thongSoDoSPSizeService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'thongSoDoSPSizeListModification',
        content: 'Deleted an thongSoDoSPSize'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-thong-so-do-sp-size-delete-popup',
  template: ''
})
export class ThongSoDoSPSizeDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ thongSoDoSPSize }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(ThongSoDoSPSizeDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.thongSoDoSPSize = thongSoDoSPSize;
        this.ngbModalRef.result.then(
          () => {
            this.router.navigate(['/thong-so-do-sp-size', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          () => {
            this.router.navigate(['/thong-so-do-sp-size', { outlets: { popup: null } }]);
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
