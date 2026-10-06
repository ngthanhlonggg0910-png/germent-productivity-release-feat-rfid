import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { CtTestModule } from '../../../test.module';
import { ProChuyenDeleteDialogComponent } from 'app/entities/pro-chuyen/pro-chuyen-delete-dialog.component';
import { ProChuyenService } from 'app/entities/pro-chuyen/pro-chuyen.service';

describe('Component Tests', () => {
  describe('ProChuyen Management Delete Component', () => {
    let comp: ProChuyenDeleteDialogComponent;
    let fixture: ComponentFixture<ProChuyenDeleteDialogComponent>;
    let service: ProChuyenService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [ProChuyenDeleteDialogComponent]
      })
        .overrideTemplate(ProChuyenDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ProChuyenDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProChuyenService);
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
