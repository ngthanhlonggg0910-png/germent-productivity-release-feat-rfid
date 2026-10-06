import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IProIssueCumLoi } from 'app/shared/model/pro-issue-cum-loi.model';
import { ProIssueCumLoiService } from './pro-issue-cum-loi.service';

@Component({
  selector: 'jhi-pro-issue-cum-loi-delete-dialog',
  templateUrl: './pro-issue-cum-loi-delete-dialog.component.html'
})
export class ProIssueCumLoiDeleteDialogComponent {
  proIssueCumLoi: IProIssueCumLoi;

  constructor(
    protected proIssueCumLoiService: ProIssueCumLoiService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.proIssueCumLoiService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'proIssueCumLoiListModification',
        content: 'Deleted an proIssueCumLoi'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-pro-issue-cum-loi-delete-popup',
  template: ''
})
export class ProIssueCumLoiDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ proIssueCumLoi }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(ProIssueCumLoiDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.proIssueCumLoi = proIssueCumLoi;
        this.ngbModalRef.result.then(
          () => {
            this.router.navigate(['/pro-issue-cum-loi', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          () => {
            this.router.navigate(['/pro-issue-cum-loi', { outlets: { popup: null } }]);
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
