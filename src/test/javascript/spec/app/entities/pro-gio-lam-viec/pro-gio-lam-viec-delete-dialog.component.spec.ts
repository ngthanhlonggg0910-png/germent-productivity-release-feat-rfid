import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { CtTestModule } from '../../../test.module';
import { ProGioLamViecDeleteDialogComponent } from 'app/entities/pro-gio-lam-viec/pro-gio-lam-viec-delete-dialog.component';
import { ProGioLamViecService } from 'app/entities/pro-gio-lam-viec/pro-gio-lam-viec.service';

describe('Component Tests', () => {
  describe('ProGioLamViec Management Delete Component', () => {
    let comp: ProGioLamViecDeleteDialogComponent;
    let fixture: ComponentFixture<ProGioLamViecDeleteDialogComponent>;
    let service: ProGioLamViecService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [ProGioLamViecDeleteDialogComponent]
      })
        .overrideTemplate(ProGioLamViecDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ProGioLamViecDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProGioLamViecService);
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
