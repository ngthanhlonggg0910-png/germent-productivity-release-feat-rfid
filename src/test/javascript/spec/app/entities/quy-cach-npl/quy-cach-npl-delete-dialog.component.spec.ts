import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { CtTestModule } from '../../../test.module';
import { QuyCachNPLDeleteDialogComponent } from 'app/entities/quy-cach-npl/quy-cach-npl-delete-dialog.component';
import { QuyCachNPLService } from 'app/entities/quy-cach-npl/quy-cach-npl.service';

describe('Component Tests', () => {
  describe('QuyCachNPL Management Delete Component', () => {
    let comp: QuyCachNPLDeleteDialogComponent;
    let fixture: ComponentFixture<QuyCachNPLDeleteDialogComponent>;
    let service: QuyCachNPLService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [QuyCachNPLDeleteDialogComponent]
      })
        .overrideTemplate(QuyCachNPLDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(QuyCachNPLDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(QuyCachNPLService);
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
