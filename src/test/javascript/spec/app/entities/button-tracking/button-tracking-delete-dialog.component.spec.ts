import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { CtTestModule } from '../../../test.module';
import { ButtonTrackingDeleteDialogComponent } from 'app/entities/button-tracking/button-tracking-delete-dialog.component';
import { ButtonTrackingService } from 'app/entities/button-tracking/button-tracking.service';

describe('Component Tests', () => {
  describe('ButtonTracking Management Delete Component', () => {
    let comp: ButtonTrackingDeleteDialogComponent;
    let fixture: ComponentFixture<ButtonTrackingDeleteDialogComponent>;
    let service: ButtonTrackingService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [ButtonTrackingDeleteDialogComponent]
      })
        .overrideTemplate(ButtonTrackingDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ButtonTrackingDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ButtonTrackingService);
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
