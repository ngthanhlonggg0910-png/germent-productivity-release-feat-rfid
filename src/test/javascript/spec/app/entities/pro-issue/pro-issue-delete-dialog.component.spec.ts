import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { CtTestModule } from '../../../test.module';
import { ProIssueDeleteDialogComponent } from 'app/entities/pro-issue/pro-issue-delete-dialog.component';
import { ProIssueService } from 'app/entities/pro-issue/pro-issue.service';

describe('Component Tests', () => {
  describe('ProIssue Management Delete Component', () => {
    let comp: ProIssueDeleteDialogComponent;
    let fixture: ComponentFixture<ProIssueDeleteDialogComponent>;
    let service: ProIssueService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [ProIssueDeleteDialogComponent]
      })
        .overrideTemplate(ProIssueDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ProIssueDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProIssueService);
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
