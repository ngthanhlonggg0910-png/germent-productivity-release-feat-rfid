import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { CtTestModule } from '../../../test.module';
import { NVButtonDeleteDialogComponent } from 'app/entities/nv-button/nv-button-delete-dialog.component';
import { NVButtonService } from 'app/entities/nv-button/nv-button.service';

describe('Component Tests', () => {
  describe('NVButton Management Delete Component', () => {
    let comp: NVButtonDeleteDialogComponent;
    let fixture: ComponentFixture<NVButtonDeleteDialogComponent>;
    let service: NVButtonService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [NVButtonDeleteDialogComponent]
      })
        .overrideTemplate(NVButtonDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(NVButtonDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(NVButtonService);
      mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
      mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete(123);
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith(123);
          expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        })
      ));
    });
  });
});
