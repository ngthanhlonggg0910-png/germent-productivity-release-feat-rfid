import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { CtTestModule } from '../../../test.module';
import { ProRefinalChiTietLoiDeleteDialogComponent } from 'app/entities/pro-refinal-chi-tiet-loi/pro-refinal-chi-tiet-loi-delete-dialog.component';
import { ProRefinalChiTietLoiService } from 'app/entities/pro-refinal-chi-tiet-loi/pro-refinal-chi-tiet-loi.service';

describe('Component Tests', () => {
  describe('ProRefinalChiTietLoi Management Delete Component', () => {
    let comp: ProRefinalChiTietLoiDeleteDialogComponent;
    let fixture: ComponentFixture<ProRefinalChiTietLoiDeleteDialogComponent>;
    let service: ProRefinalChiTietLoiService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [ProRefinalChiTietLoiDeleteDialogComponent]
      })
        .overrideTemplate(ProRefinalChiTietLoiDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ProRefinalChiTietLoiDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProRefinalChiTietLoiService);
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
