import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IProKeHoachSanXuat } from 'app/shared/model/pro-ke-hoach-san-xuat.model';
import { ProKeHoachSanXuatService } from './pro-ke-hoach-san-xuat.service';

@Component({
  selector: 'jhi-pro-ke-hoach-san-xuat-delete-dialog',
  templateUrl: './pro-ke-hoach-san-xuat-delete-dialog.component.html'
})
export class ProKeHoachSanXuatDeleteDialogComponent {
  proKeHoachSanXuat: IProKeHoachSanXuat;

  constructor(
    protected proKeHoachSanXuatService: ProKeHoachSanXuatService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.proKeHoachSanXuatService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'proKeHoachSanXuatListModification',
        content: 'Deleted an proKeHoachSanXuat'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-pro-ke-hoach-san-xuat-delete-popup',
  template: ''
})
export class ProKeHoachSanXuatDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ proKeHoachSanXuat }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(ProKeHoachSanXuatDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.proKeHoachSanXuat = proKeHoachSanXuat;
        this.ngbModalRef.result.then(
          () => {
            this.router.navigate(['/pro-ke-hoach-san-xuat', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          () => {
            this.router.navigate(['/pro-ke-hoach-san-xuat', { outlets: { popup: null } }]);
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
