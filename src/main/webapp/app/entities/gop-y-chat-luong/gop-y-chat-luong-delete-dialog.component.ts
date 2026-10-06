import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IGopYChatLuong } from 'app/shared/model/gop-y-chat-luong.model';
import { GopYChatLuongService } from './gop-y-chat-luong.service';

@Component({
  selector: 'jhi-gop-y-chat-luong-delete-dialog',
  templateUrl: './gop-y-chat-luong-delete-dialog.component.html'
})
export class GopYChatLuongDeleteDialogComponent {
  gopYChatLuong: IGopYChatLuong;

  constructor(
    protected gopYChatLuongService: GopYChatLuongService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.gopYChatLuongService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'gopYChatLuongListModification',
        content: 'Deleted an gopYChatLuong'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-gop-y-chat-luong-delete-popup',
  template: ''
})
export class GopYChatLuongDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ gopYChatLuong }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(GopYChatLuongDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.gopYChatLuong = gopYChatLuong;
        this.ngbModalRef.result.then(
          () => {
            this.router.navigate(['/gop-y-chat-luong', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          () => {
            this.router.navigate(['/gop-y-chat-luong', { outlets: { popup: null } }]);
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
