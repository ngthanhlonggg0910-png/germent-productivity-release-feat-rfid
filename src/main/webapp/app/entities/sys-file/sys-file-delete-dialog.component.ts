import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISysFile } from 'app/shared/model/sys-file.model';
import { SysFileService } from './sys-file.service';

@Component({
  selector: 'jhi-sys-file-delete-dialog',
  templateUrl: './sys-file-delete-dialog.component.html'
})
export class SysFileDeleteDialogComponent {
  sysFile: ISysFile;

  constructor(protected sysFileService: SysFileService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.sysFileService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'sysFileListModification',
        content: 'Deleted an sysFile'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-sys-file-delete-popup',
  template: ''
})
export class SysFileDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ sysFile }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(SysFileDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.sysFile = sysFile;
        this.ngbModalRef.result.then(
          () => {
            this.router.navigate(['/sys-file', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          () => {
            this.router.navigate(['/sys-file', { outlets: { popup: null } }]);
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
