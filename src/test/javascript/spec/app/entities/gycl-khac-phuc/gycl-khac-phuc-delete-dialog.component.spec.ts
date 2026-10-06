import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { CtTestModule } from '../../../test.module';
import { GYCLKhacPhucDeleteDialogComponent } from 'app/entities/gycl-khac-phuc/gycl-khac-phuc-delete-dialog.component';
import { GYCLKhacPhucService } from 'app/entities/gycl-khac-phuc/gycl-khac-phuc.service';

describe('Component Tests', () => {
  describe('GYCLKhacPhuc Management Delete Component', () => {
    let comp: GYCLKhacPhucDeleteDialogComponent;
    let fixture: ComponentFixture<GYCLKhacPhucDeleteDialogComponent>;
    let service: GYCLKhacPhucService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [GYCLKhacPhucDeleteDialogComponent]
      })
        .overrideTemplate(GYCLKhacPhucDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(GYCLKhacPhucDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(GYCLKhacPhucService);
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
