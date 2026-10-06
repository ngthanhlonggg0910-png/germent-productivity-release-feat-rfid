import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMapButtonAction } from 'app/shared/model/map-button-action.model';
import { MapButtonActionService } from './map-button-action.service';

@Component({
  selector: 'jhi-map-button-action-delete-dialog',
  templateUrl: './map-button-action-delete-dialog.component.html'
})
export class MapButtonActionDeleteDialogComponent {
  mapButtonAction: IMapButtonAction;

  constructor(
    protected mapButtonActionService: MapButtonActionService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mapButtonActionService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'mapButtonActionListModification',
        content: 'Deleted an mapButtonAction'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-map-button-action-delete-popup',
  template: ''
})
export class MapButtonActionDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mapButtonAction }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MapButtonActionDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mapButtonAction = mapButtonAction;
        this.ngbModalRef.result.then(
          () => {
            this.router.navigate(['/map-button-action', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          () => {
            this.router.navigate(['/map-button-action', { outlets: { popup: null } }]);
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
