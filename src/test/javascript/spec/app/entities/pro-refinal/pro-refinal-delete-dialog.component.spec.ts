import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { CtTestModule } from '../../../test.module';
import { ProRefinalDeleteDialogComponent } from 'app/entities/pro-refinal/pro-refinal-delete-dialog.component';
import { ProRefinalService } from 'app/entities/pro-refinal/pro-refinal.service';

describe('Component Tests', () => {
  describe('ProRefinal Management Delete Component', () => {
    let comp: ProRefinalDeleteDialogComponent;
    let fixture: ComponentFixture<ProRefinalDeleteDialogComponent>;
    let service: ProRefinalService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [ProRefinalDeleteDialogComponent]
      })
        .overrideTemplate(ProRefinalDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ProRefinalDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProRefinalService);
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
