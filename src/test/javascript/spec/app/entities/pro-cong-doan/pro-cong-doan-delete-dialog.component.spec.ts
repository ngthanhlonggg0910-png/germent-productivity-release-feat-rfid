import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { CtTestModule } from '../../../test.module';
import { ProCongDoanDeleteDialogComponent } from 'app/entities/pro-cong-doan/pro-cong-doan-delete-dialog.component';
import { ProCongDoanService } from 'app/entities/pro-cong-doan/pro-cong-doan.service';

describe('Component Tests', () => {
  describe('ProCongDoan Management Delete Component', () => {
    let comp: ProCongDoanDeleteDialogComponent;
    let fixture: ComponentFixture<ProCongDoanDeleteDialogComponent>;
    let service: ProCongDoanService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [ProCongDoanDeleteDialogComponent]
      })
        .overrideTemplate(ProCongDoanDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ProCongDoanDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProCongDoanService);
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
