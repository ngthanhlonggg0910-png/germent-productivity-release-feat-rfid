import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { CtTestModule } from '../../../test.module';
import { DungSaiDeleteDialogComponent } from 'app/entities/dung-sai/dung-sai-delete-dialog.component';
import { DungSaiService } from 'app/entities/dung-sai/dung-sai.service';

describe('Component Tests', () => {
  describe('DungSai Management Delete Component', () => {
    let comp: DungSaiDeleteDialogComponent;
    let fixture: ComponentFixture<DungSaiDeleteDialogComponent>;
    let service: DungSaiService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [DungSaiDeleteDialogComponent]
      })
        .overrideTemplate(DungSaiDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(DungSaiDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(DungSaiService);
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
