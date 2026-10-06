import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { CtTestModule } from '../../../test.module';
import { ThongSoDoDeleteDialogComponent } from 'app/entities/thong-so-do/thong-so-do-delete-dialog.component';
import { ThongSoDoService } from 'app/entities/thong-so-do/thong-so-do.service';

describe('Component Tests', () => {
  describe('ThongSoDo Management Delete Component', () => {
    let comp: ThongSoDoDeleteDialogComponent;
    let fixture: ComponentFixture<ThongSoDoDeleteDialogComponent>;
    let service: ThongSoDoService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [ThongSoDoDeleteDialogComponent]
      })
        .overrideTemplate(ThongSoDoDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ThongSoDoDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ThongSoDoService);
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
