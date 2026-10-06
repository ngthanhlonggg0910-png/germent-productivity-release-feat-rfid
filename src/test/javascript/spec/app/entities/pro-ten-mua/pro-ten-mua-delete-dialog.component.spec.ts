import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { CtTestModule } from '../../../test.module';
import { ProTenMuaDeleteDialogComponent } from 'app/entities/pro-ten-mua/pro-ten-mua-delete-dialog.component';
import { ProTenMuaService } from 'app/entities/pro-ten-mua/pro-ten-mua.service';

describe('Component Tests', () => {
  describe('ProTenMua Management Delete Component', () => {
    let comp: ProTenMuaDeleteDialogComponent;
    let fixture: ComponentFixture<ProTenMuaDeleteDialogComponent>;
    let service: ProTenMuaService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [ProTenMuaDeleteDialogComponent]
      })
        .overrideTemplate(ProTenMuaDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ProTenMuaDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProTenMuaService);
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
