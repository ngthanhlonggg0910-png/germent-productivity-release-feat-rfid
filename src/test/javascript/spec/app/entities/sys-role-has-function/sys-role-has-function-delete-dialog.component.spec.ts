import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { CtTestModule } from '../../../test.module';
import { SysRoleHasFunctionDeleteDialogComponent } from 'app/entities/sys-role-has-function/sys-role-has-function-delete-dialog.component';
import { SysRoleHasFunctionService } from 'app/entities/sys-role-has-function/sys-role-has-function.service';

describe('Component Tests', () => {
  describe('SysRoleHasFunction Management Delete Component', () => {
    let comp: SysRoleHasFunctionDeleteDialogComponent;
    let fixture: ComponentFixture<SysRoleHasFunctionDeleteDialogComponent>;
    let service: SysRoleHasFunctionService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [SysRoleHasFunctionDeleteDialogComponent]
      })
        .overrideTemplate(SysRoleHasFunctionDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(SysRoleHasFunctionDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SysRoleHasFunctionService);
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
