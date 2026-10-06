import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { CtTestModule } from '../../../test.module';
import { ThongSoThucThiDeleteDialogComponent } from 'app/entities/thong-so-thuc-thi/thong-so-thuc-thi-delete-dialog.component';
import { ThongSoThucThiService } from 'app/entities/thong-so-thuc-thi/thong-so-thuc-thi.service';

describe('Component Tests', () => {
  describe('ThongSoThucThi Management Delete Component', () => {
    let comp: ThongSoThucThiDeleteDialogComponent;
    let fixture: ComponentFixture<ThongSoThucThiDeleteDialogComponent>;
    let service: ThongSoThucThiService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [ThongSoThucThiDeleteDialogComponent]
      })
        .overrideTemplate(ThongSoThucThiDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ThongSoThucThiDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ThongSoThucThiService);
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
