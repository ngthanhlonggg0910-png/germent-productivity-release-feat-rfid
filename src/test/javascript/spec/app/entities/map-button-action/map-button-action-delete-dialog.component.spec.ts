import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { CtTestModule } from '../../../test.module';
import { MapButtonActionDeleteDialogComponent } from 'app/entities/map-button-action/map-button-action-delete-dialog.component';
import { MapButtonActionService } from 'app/entities/map-button-action/map-button-action.service';

describe('Component Tests', () => {
  describe('MapButtonAction Management Delete Component', () => {
    let comp: MapButtonActionDeleteDialogComponent;
    let fixture: ComponentFixture<MapButtonActionDeleteDialogComponent>;
    let service: MapButtonActionService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [MapButtonActionDeleteDialogComponent]
      })
        .overrideTemplate(MapButtonActionDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MapButtonActionDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MapButtonActionService);
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
