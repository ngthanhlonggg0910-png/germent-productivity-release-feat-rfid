import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { CtTestModule } from '../../../test.module';
import { SysFileDeleteDialogComponent } from 'app/entities/sys-file/sys-file-delete-dialog.component';
import { SysFileService } from 'app/entities/sys-file/sys-file.service';

describe('Component Tests', () => {
  describe('SysFile Management Delete Component', () => {
    let comp: SysFileDeleteDialogComponent;
    let fixture: ComponentFixture<SysFileDeleteDialogComponent>;
    let service: SysFileService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [SysFileDeleteDialogComponent]
      })
        .overrideTemplate(SysFileDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(SysFileDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SysFileService);
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
