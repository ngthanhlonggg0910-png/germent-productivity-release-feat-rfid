import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { CtTestModule } from '../../../test.module';
import { SoLuongHangKiemDeleteDialogComponent } from 'app/entities/so-luong-hang-kiem/so-luong-hang-kiem-delete-dialog.component';
import { SoLuongHangKiemService } from 'app/entities/so-luong-hang-kiem/so-luong-hang-kiem.service';

describe('Component Tests', () => {
  describe('SoLuongHangKiem Management Delete Component', () => {
    let comp: SoLuongHangKiemDeleteDialogComponent;
    let fixture: ComponentFixture<SoLuongHangKiemDeleteDialogComponent>;
    let service: SoLuongHangKiemService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [SoLuongHangKiemDeleteDialogComponent]
      })
        .overrideTemplate(SoLuongHangKiemDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(SoLuongHangKiemDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SoLuongHangKiemService);
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
