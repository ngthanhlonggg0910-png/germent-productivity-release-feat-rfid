import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { CtTestModule } from '../../../test.module';
import { ProCumLoiDeleteDialogComponent } from 'app/entities/pro-cum-loi/pro-cum-loi-delete-dialog.component';
import { ProCumLoiService } from 'app/entities/pro-cum-loi/pro-cum-loi.service';

describe('Component Tests', () => {
  describe('ProCumLoi Management Delete Component', () => {
    let comp: ProCumLoiDeleteDialogComponent;
    let fixture: ComponentFixture<ProCumLoiDeleteDialogComponent>;
    let service: ProCumLoiService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [ProCumLoiDeleteDialogComponent]
      })
        .overrideTemplate(ProCumLoiDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ProCumLoiDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProCumLoiService);
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
