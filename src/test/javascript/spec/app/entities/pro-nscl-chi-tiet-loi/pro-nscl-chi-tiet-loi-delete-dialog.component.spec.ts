import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { CtTestModule } from '../../../test.module';
import { ProNSCLChiTietLoiDeleteDialogComponent } from 'app/entities/pro-nscl-chi-tiet-loi/pro-nscl-chi-tiet-loi-delete-dialog.component';
import { ProNSCLChiTietLoiService } from 'app/entities/pro-nscl-chi-tiet-loi/pro-nscl-chi-tiet-loi.service';

describe('Component Tests', () => {
  describe('ProNSCLChiTietLoi Management Delete Component', () => {
    let comp: ProNSCLChiTietLoiDeleteDialogComponent;
    let fixture: ComponentFixture<ProNSCLChiTietLoiDeleteDialogComponent>;
    let service: ProNSCLChiTietLoiService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [ProNSCLChiTietLoiDeleteDialogComponent]
      })
        .overrideTemplate(ProNSCLChiTietLoiDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ProNSCLChiTietLoiDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProNSCLChiTietLoiService);
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
