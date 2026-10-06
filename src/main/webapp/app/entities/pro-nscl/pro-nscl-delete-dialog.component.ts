import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IProNSCL } from 'app/shared/model/pro-nscl.model';
import { ProNSCLService } from './pro-nscl.service';

@Component({
  templateUrl: './pro-nscl-delete-dialog.component.html'
})
export class ProNSCLDeleteDialogComponent {
  proNSCL?: IProNSCL;

  constructor(protected proNSCLService: ProNSCLService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.proNSCLService.delete(id).subscribe(() => {
      this.eventManager.broadcast('proNSCLListModification');
      this.activeModal.close();
    });
  }
}
