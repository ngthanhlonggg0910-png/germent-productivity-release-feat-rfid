import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { CtTestModule } from '../../../test.module';
import { ProTaiKhoanCheckPointDeleteDialogComponent } from 'app/entities/pro-tai-khoan-check-point/pro-tai-khoan-check-point-delete-dialog.component';
import { ProTaiKhoanCheckPointService } from 'app/entities/pro-tai-khoan-check-point/pro-tai-khoan-check-point.service';

describe('Component Tests', () => {
  describe('ProTaiKhoanCheckPoint Management Delete Component', () => {
    let comp: ProTaiKhoanCheckPointDeleteDialogComponent;
    let fixture: ComponentFixture<ProTaiKhoanCheckPointDeleteDialogComponent>;
    let service: ProTaiKhoanCheckPointService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [ProTaiKhoanCheckPointDeleteDialogComponent]
      })
        .overrideTemplate(ProTaiKhoanCheckPointDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ProTaiKhoanCheckPointDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProTaiKhoanCheckPointService);
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
