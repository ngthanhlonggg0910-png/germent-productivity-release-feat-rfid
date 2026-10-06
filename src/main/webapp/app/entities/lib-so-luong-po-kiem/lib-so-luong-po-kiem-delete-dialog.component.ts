import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ILibSoLuongPoKiem } from 'app/shared/model/lib-so-luong-po-kiem.model';
import { LibSoLuongPoKiemService } from './lib-so-luong-po-kiem.service';

@Component({
  selector: 'jhi-lib-so-luong-po-kiem-delete-dialog',
  templateUrl: './lib-so-luong-po-kiem-delete-dialog.component.html'
})
export class LibSoLuongPoKiemDeleteDialogComponent {
  libSoLuongPoKiem: ILibSoLuongPoKiem;

  constructor(
    protected libSoLuongPoKiemService: LibSoLuongPoKiemService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.libSoLuongPoKiemService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'libSoLuongPoKiemListModification',
        content: 'Deleted an libSoLuongPoKiem'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-lib-so-luong-po-kiem-delete-popup',
  template: ''
})
export class LibSoLuongPoKiemDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ libSoLuongPoKiem }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(LibSoLuongPoKiemDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.libSoLuongPoKiem = libSoLuongPoKiem;
        this.ngbModalRef.result.then(
          () => {
            this.router.navigate(['/lib-so-luong-po-kiem', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          () => {
            this.router.navigate(['/lib-so-luong-po-kiem', { outlets: { popup: null } }]);
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
