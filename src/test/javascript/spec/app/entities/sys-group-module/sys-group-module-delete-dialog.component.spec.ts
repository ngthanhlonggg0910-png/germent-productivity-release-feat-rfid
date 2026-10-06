import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { CtTestModule } from '../../../test.module';
import { SysGroupModuleDeleteDialogComponent } from 'app/entities/sys-group-module/sys-group-module-delete-dialog.component';
import { SysGroupModuleService } from 'app/entities/sys-group-module/sys-group-module.service';

describe('Component Tests', () => {
  describe('SysGroupModule Management Delete Component', () => {
    let comp: SysGroupModuleDeleteDialogComponent;
    let fixture: ComponentFixture<SysGroupModuleDeleteDialogComponent>;
    let service: SysGroupModuleService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [SysGroupModuleDeleteDialogComponent]
      })
        .overrideTemplate(SysGroupModuleDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(SysGroupModuleDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SysGroupModuleService);
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
