import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { CtTestModule } from '../../../test.module';
import { SysFunctionDeleteDialogComponent } from 'app/entities/sys-function/sys-function-delete-dialog.component';
import { SysFunctionService } from 'app/entities/sys-function/sys-function.service';

describe('Component Tests', () => {
  describe('SysFunction Management Delete Component', () => {
    let comp: SysFunctionDeleteDialogComponent;
    let fixture: ComponentFixture<SysFunctionDeleteDialogComponent>;
    let service: SysFunctionService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [SysFunctionDeleteDialogComponent]
      })
        .overrideTemplate(SysFunctionDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(SysFunctionDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SysFunctionService);
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
