import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISoLuongHangKiem } from 'app/shared/model/so-luong-hang-kiem.model';
import { SoLuongHangKiemService } from './so-luong-hang-kiem.service';

@Component({
  selector: 'jhi-so-luong-hang-kiem-delete-dialog',
  templateUrl: './so-luong-hang-kiem-delete-dialog.component.html'
})
export class SoLuongHangKiemDeleteDialogComponent {
  soLuongHangKiem: ISoLuongHangKiem;

  constructor(
    protected soLuongHangKiemService: SoLuongHangKiemService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.soLuongHangKiemService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'soLuongHangKiemListModification',
        content: 'Deleted an soLuongHangKiem'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-so-luong-hang-kiem-delete-popup',
  template: ''
})
export class SoLuongHangKiemDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ soLuongHangKiem }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(SoLuongHangKiemDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.soLuongHangKiem = soLuongHangKiem;
        this.ngbModalRef.result.then(
          () => {
            this.router.navigate(['/so-luong-hang-kiem', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          () => {
            this.router.navigate(['/so-luong-hang-kiem', { outlets: { popup: null } }]);
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
