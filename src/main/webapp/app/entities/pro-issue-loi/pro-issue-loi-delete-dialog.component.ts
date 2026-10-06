import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IProIssueLoi } from 'app/shared/model/pro-issue-loi.model';
import { ProIssueLoiService } from './pro-issue-loi.service';

@Component({
  selector: 'jhi-pro-issue-loi-delete-dialog',
  templateUrl: './pro-issue-loi-delete-dialog.component.html'
})
export class ProIssueLoiDeleteDialogComponent {
  proIssueLoi: IProIssueLoi;

  constructor(
    protected proIssueLoiService: ProIssueLoiService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.proIssueLoiService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'proIssueLoiListModification',
        content: 'Deleted an proIssueLoi'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-pro-issue-loi-delete-popup',
  template: ''
})
export class ProIssueLoiDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ proIssueLoi }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(ProIssueLoiDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.proIssueLoi = proIssueLoi;
        this.ngbModalRef.result.then(
          () => {
            this.router.navigate(['/pro-issue-loi', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          () => {
            this.router.navigate(['/pro-issue-loi', { outlets: { popup: null } }]);
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
