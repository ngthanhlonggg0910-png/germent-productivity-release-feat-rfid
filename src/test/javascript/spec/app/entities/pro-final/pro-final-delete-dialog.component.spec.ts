import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { CtTestModule } from '../../../test.module';
import { ProFinalDeleteDialogComponent } from 'app/entities/pro-final/pro-final-delete-dialog.component';
import { ProFinalService } from 'app/entities/pro-final/pro-final.service';

describe('Component Tests', () => {
  describe('ProFinal Management Delete Component', () => {
    let comp: ProFinalDeleteDialogComponent;
    let fixture: ComponentFixture<ProFinalDeleteDialogComponent>;
    let service: ProFinalService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [ProFinalDeleteDialogComponent]
      })
        .overrideTemplate(ProFinalDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ProFinalDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProFinalService);
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
