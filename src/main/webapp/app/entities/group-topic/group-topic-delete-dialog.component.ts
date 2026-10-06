import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IGroupTopic } from 'app/shared/model/group-topic.model';
import { GroupTopicService } from './group-topic.service';

@Component({
  selector: 'jhi-group-topic-delete-dialog',
  templateUrl: './group-topic-delete-dialog.component.html'
})
export class GroupTopicDeleteDialogComponent {
  groupTopic: IGroupTopic;

  constructor(
    protected groupTopicService: GroupTopicService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.groupTopicService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'groupTopicListModification',
        content: 'Deleted an groupTopic'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-group-topic-delete-popup',
  template: ''
})
export class GroupTopicDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ groupTopic }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(GroupTopicDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.groupTopic = groupTopic;
        this.ngbModalRef.result.then(
          () => {
            this.router.navigate(['/group-topic', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          () => {
            this.router.navigate(['/group-topic', { outlets: { popup: null } }]);
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
