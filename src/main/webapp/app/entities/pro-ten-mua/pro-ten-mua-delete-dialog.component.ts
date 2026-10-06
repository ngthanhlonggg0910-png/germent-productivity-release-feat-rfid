import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IProTenMua } from 'app/shared/model/pro-ten-mua.model';
import { ProTenMuaService } from './pro-ten-mua.service';

@Component({
  selector: 'jhi-pro-ten-mua-delete-dialog',
  templateUrl: './pro-ten-mua-delete-dialog.component.html'
})
export class ProTenMuaDeleteDialogComponent {
  proTenMua: IProTenMua;

  constructor(protected proTenMuaService: ProTenMuaService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.proTenMuaService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'proTenMuaListModification',
        content: 'Deleted an proTenMua'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-pro-ten-mua-delete-popup',
  template: ''
})
export class ProTenMuaDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ proTenMua }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(ProTenMuaDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.proTenMua = proTenMua;
        this.ngbModalRef.result.then(
          () => {
            this.router.navigate(['/pro-ten-mua', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          () => {
            this.router.navigate(['/pro-ten-mua', { outlets: { popup: null } }]);
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
