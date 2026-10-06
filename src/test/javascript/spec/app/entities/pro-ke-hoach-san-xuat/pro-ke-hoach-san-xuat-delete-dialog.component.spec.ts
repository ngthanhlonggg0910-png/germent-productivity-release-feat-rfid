import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { CtTestModule } from '../../../test.module';
import { ProKeHoachSanXuatDeleteDialogComponent } from 'app/entities/pro-ke-hoach-san-xuat/pro-ke-hoach-san-xuat-delete-dialog.component';
import { ProKeHoachSanXuatService } from 'app/entities/pro-ke-hoach-san-xuat/pro-ke-hoach-san-xuat.service';

describe('Component Tests', () => {
  describe('ProKeHoachSanXuat Management Delete Component', () => {
    let comp: ProKeHoachSanXuatDeleteDialogComponent;
    let fixture: ComponentFixture<ProKeHoachSanXuatDeleteDialogComponent>;
    let service: ProKeHoachSanXuatService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [ProKeHoachSanXuatDeleteDialogComponent]
      })
        .overrideTemplate(ProKeHoachSanXuatDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ProKeHoachSanXuatDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProKeHoachSanXuatService);
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
