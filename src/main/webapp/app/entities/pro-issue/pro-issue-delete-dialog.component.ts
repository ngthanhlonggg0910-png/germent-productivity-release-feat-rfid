import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IProIssue } from 'app/shared/model/pro-issue.model';
import { ProIssueService } from './pro-issue.service';

@Component({
  selector: 'jhi-pro-issue-delete-dialog',
  templateUrl: './pro-issue-delete-dialog.component.html'
})
export class ProIssueDeleteDialogComponent {
  proIssue: IProIssue;

  constructor(protected proIssueService: ProIssueService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.proIssueService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'proIssueListModification',
        content: 'Deleted an proIssue'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-pro-issue-delete-popup',
  template: ''
})
export class ProIssueDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ proIssue }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(ProIssueDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.proIssue = proIssue;
        this.ngbModalRef.result.then(
          () => {
            this.router.navigate(['/pro-issue', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          () => {
            this.router.navigate(['/pro-issue', { outlets: { popup: null } }]);
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
