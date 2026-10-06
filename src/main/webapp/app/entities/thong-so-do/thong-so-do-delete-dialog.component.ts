import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IThongSoDo } from 'app/shared/model/thong-so-do.model';
import { ThongSoDoService } from './thong-so-do.service';

@Component({
  selector: 'jhi-thong-so-do-delete-dialog',
  templateUrl: './thong-so-do-delete-dialog.component.html'
})
export class ThongSoDoDeleteDialogComponent {
  thongSoDo: IThongSoDo;

  constructor(protected thongSoDoService: ThongSoDoService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.thongSoDoService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'thongSoDoListModification',
        content: 'Deleted an thongSoDo'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-thong-so-do-delete-popup',
  template: ''
})
export class ThongSoDoDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ thongSoDo }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(ThongSoDoDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.thongSoDo = thongSoDo;
        this.ngbModalRef.result.then(
          () => {
            this.router.navigate(['/thong-so-do', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          () => {
            this.router.navigate(['/thong-so-do', { outlets: { popup: null } }]);
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
