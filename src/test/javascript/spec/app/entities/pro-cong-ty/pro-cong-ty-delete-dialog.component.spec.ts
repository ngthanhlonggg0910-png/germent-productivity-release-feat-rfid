import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { CtTestModule } from '../../../test.module';
import { ProCongTyDeleteDialogComponent } from 'app/entities/pro-cong-ty/pro-cong-ty-delete-dialog.component';
import { ProCongTyService } from 'app/entities/pro-cong-ty/pro-cong-ty.service';

describe('Component Tests', () => {
  describe('ProCongTy Management Delete Component', () => {
    let comp: ProCongTyDeleteDialogComponent;
    let fixture: ComponentFixture<ProCongTyDeleteDialogComponent>;
    let service: ProCongTyService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [ProCongTyDeleteDialogComponent]
      })
        .overrideTemplate(ProCongTyDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ProCongTyDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProCongTyService);
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
