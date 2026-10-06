import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { CtTestModule } from '../../../test.module';
import { ThongSoDoSPDeleteDialogComponent } from 'app/entities/thong-so-do-sp/thong-so-do-sp-delete-dialog.component';
import { ThongSoDoSPService } from 'app/entities/thong-so-do-sp/thong-so-do-sp.service';

describe('Component Tests', () => {
  describe('ThongSoDoSP Management Delete Component', () => {
    let comp: ThongSoDoSPDeleteDialogComponent;
    let fixture: ComponentFixture<ThongSoDoSPDeleteDialogComponent>;
    let service: ThongSoDoSPService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [ThongSoDoSPDeleteDialogComponent]
      })
        .overrideTemplate(ThongSoDoSPDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ThongSoDoSPDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ThongSoDoSPService);
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
