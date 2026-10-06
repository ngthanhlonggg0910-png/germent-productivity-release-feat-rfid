import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { CtTestModule } from '../../../test.module';
import { ProNhaMayDeleteDialogComponent } from 'app/entities/pro-nha-may/pro-nha-may-delete-dialog.component';
import { ProNhaMayService } from 'app/entities/pro-nha-may/pro-nha-may.service';

describe('Component Tests', () => {
  describe('ProNhaMay Management Delete Component', () => {
    let comp: ProNhaMayDeleteDialogComponent;
    let fixture: ComponentFixture<ProNhaMayDeleteDialogComponent>;
    let service: ProNhaMayService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [ProNhaMayDeleteDialogComponent]
      })
        .overrideTemplate(ProNhaMayDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ProNhaMayDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProNhaMayService);
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
