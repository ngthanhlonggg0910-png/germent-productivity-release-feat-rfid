import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { CtTestModule } from '../../../test.module';
import { ProRefinalCCChiTietLoiDeleteDialogComponent } from 'app/entities/pro-refinal-cc-chi-tiet-loi/pro-refinal-cc-chi-tiet-loi-delete-dialog.component';
import { ProRefinalCCChiTietLoiService } from 'app/entities/pro-refinal-cc-chi-tiet-loi/pro-refinal-cc-chi-tiet-loi.service';

describe('Component Tests', () => {
  describe('ProRefinalCCChiTietLoi Management Delete Component', () => {
    let comp: ProRefinalCCChiTietLoiDeleteDialogComponent;
    let fixture: ComponentFixture<ProRefinalCCChiTietLoiDeleteDialogComponent>;
    let service: ProRefinalCCChiTietLoiService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [ProRefinalCCChiTietLoiDeleteDialogComponent]
      })
        .overrideTemplate(ProRefinalCCChiTietLoiDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ProRefinalCCChiTietLoiDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProRefinalCCChiTietLoiService);
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
