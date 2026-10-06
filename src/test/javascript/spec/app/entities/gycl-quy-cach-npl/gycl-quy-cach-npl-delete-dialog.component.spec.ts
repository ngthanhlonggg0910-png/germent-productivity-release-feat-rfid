import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { CtTestModule } from '../../../test.module';
import { GYCLQuyCachNPLDeleteDialogComponent } from 'app/entities/gycl-quy-cach-npl/gycl-quy-cach-npl-delete-dialog.component';
import { GYCLQuyCachNPLService } from 'app/entities/gycl-quy-cach-npl/gycl-quy-cach-npl.service';

describe('Component Tests', () => {
  describe('GYCLQuyCachNPL Management Delete Component', () => {
    let comp: GYCLQuyCachNPLDeleteDialogComponent;
    let fixture: ComponentFixture<GYCLQuyCachNPLDeleteDialogComponent>;
    let service: GYCLQuyCachNPLService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [GYCLQuyCachNPLDeleteDialogComponent]
      })
        .overrideTemplate(GYCLQuyCachNPLDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(GYCLQuyCachNPLDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(GYCLQuyCachNPLService);
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
