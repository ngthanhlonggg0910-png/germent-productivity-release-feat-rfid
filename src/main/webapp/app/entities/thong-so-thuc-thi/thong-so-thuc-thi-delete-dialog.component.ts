import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IThongSoThucThi } from 'app/shared/model/thong-so-thuc-thi.model';
import { ThongSoThucThiService } from './thong-so-thuc-thi.service';

@Component({
  selector: 'jhi-thong-so-thuc-thi-delete-dialog',
  templateUrl: './thong-so-thuc-thi-delete-dialog.component.html'
})
export class ThongSoThucThiDeleteDialogComponent {
  thongSoThucThi: IThongSoThucThi;

  constructor(
    protected thongSoThucThiService: ThongSoThucThiService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.thongSoThucThiService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'thongSoThucThiListModification',
        content: 'Deleted an thongSoThucThi'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-thong-so-thuc-thi-delete-popup',
  template: ''
})
export class ThongSoThucThiDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ thongSoThucThi }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(ThongSoThucThiDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.thongSoThucThi = thongSoThucThi;
        this.ngbModalRef.result.then(
          () => {
            this.router.navigate(['/thong-so-thuc-thi', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          () => {
            this.router.navigate(['/thong-so-thuc-thi', { outlets: { popup: null } }]);
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
