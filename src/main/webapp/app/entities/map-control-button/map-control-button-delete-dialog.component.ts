import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMapControlButton } from 'app/shared/model/map-control-button.model';
import { MapControlButtonService } from './map-control-button.service';

@Component({
  selector: 'jhi-map-control-button-delete-dialog',
  templateUrl: './map-control-button-delete-dialog.component.html'
})
export class MapControlButtonDeleteDialogComponent {
  mapControlButton: IMapControlButton;

  constructor(
    protected mapControlButtonService: MapControlButtonService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mapControlButtonService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'mapControlButtonListModification',
        content: 'Deleted an mapControlButton'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-map-control-button-delete-popup',
  template: ''
})
export class MapControlButtonDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mapControlButton }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MapControlButtonDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mapControlButton = mapControlButton;
        this.ngbModalRef.result.then(
          () => {
            this.router.navigate(['/map-control-button', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          () => {
            this.router.navigate(['/map-control-button', { outlets: { popup: null } }]);
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
