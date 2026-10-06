import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { CtTestModule } from '../../../test.module';
import { ProRefinalCCDeleteDialogComponent } from 'app/entities/pro-refinal-cc/pro-refinal-cc-delete-dialog.component';
import { ProRefinalCCService } from 'app/entities/pro-refinal-cc/pro-refinal-cc.service';

describe('Component Tests', () => {
  describe('ProRefinalCC Management Delete Component', () => {
    let comp: ProRefinalCCDeleteDialogComponent;
    let fixture: ComponentFixture<ProRefinalCCDeleteDialogComponent>;
    let service: ProRefinalCCService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [ProRefinalCCDeleteDialogComponent]
      })
        .overrideTemplate(ProRefinalCCDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ProRefinalCCDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProRefinalCCService);
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
