import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { CtTestModule } from '../../../test.module';
import { LibSoLuongPoKiemDeleteDialogComponent } from 'app/entities/lib-so-luong-po-kiem/lib-so-luong-po-kiem-delete-dialog.component';
import { LibSoLuongPoKiemService } from 'app/entities/lib-so-luong-po-kiem/lib-so-luong-po-kiem.service';

describe('Component Tests', () => {
  describe('LibSoLuongPoKiem Management Delete Component', () => {
    let comp: LibSoLuongPoKiemDeleteDialogComponent;
    let fixture: ComponentFixture<LibSoLuongPoKiemDeleteDialogComponent>;
    let service: LibSoLuongPoKiemService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [LibSoLuongPoKiemDeleteDialogComponent]
      })
        .overrideTemplate(LibSoLuongPoKiemDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(LibSoLuongPoKiemDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(LibSoLuongPoKiemService);
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
