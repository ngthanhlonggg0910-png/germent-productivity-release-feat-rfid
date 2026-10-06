import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { CtTestModule } from '../../../test.module';
import { ProCheckPointDeleteDialogComponent } from 'app/entities/pro-check-point/pro-check-point-delete-dialog.component';
import { ProCheckPointService } from 'app/entities/pro-check-point/pro-check-point.service';

describe('Component Tests', () => {
  describe('ProCheckPoint Management Delete Component', () => {
    let comp: ProCheckPointDeleteDialogComponent;
    let fixture: ComponentFixture<ProCheckPointDeleteDialogComponent>;
    let service: ProCheckPointService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [ProCheckPointDeleteDialogComponent]
      })
        .overrideTemplate(ProCheckPointDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ProCheckPointDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProCheckPointService);
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
