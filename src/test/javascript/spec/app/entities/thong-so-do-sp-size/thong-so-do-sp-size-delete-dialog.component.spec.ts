import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { CtTestModule } from '../../../test.module';
import { ThongSoDoSPSizeDeleteDialogComponent } from 'app/entities/thong-so-do-sp-size/thong-so-do-sp-size-delete-dialog.component';
import { ThongSoDoSPSizeService } from 'app/entities/thong-so-do-sp-size/thong-so-do-sp-size.service';

describe('Component Tests', () => {
  describe('ThongSoDoSPSize Management Delete Component', () => {
    let comp: ThongSoDoSPSizeDeleteDialogComponent;
    let fixture: ComponentFixture<ThongSoDoSPSizeDeleteDialogComponent>;
    let service: ThongSoDoSPSizeService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [ThongSoDoSPSizeDeleteDialogComponent]
      })
        .overrideTemplate(ThongSoDoSPSizeDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ThongSoDoSPSizeDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ThongSoDoSPSizeService);
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
