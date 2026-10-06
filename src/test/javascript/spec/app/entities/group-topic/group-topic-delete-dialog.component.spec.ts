import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { CtTestModule } from '../../../test.module';
import { GroupTopicDeleteDialogComponent } from 'app/entities/group-topic/group-topic-delete-dialog.component';
import { GroupTopicService } from 'app/entities/group-topic/group-topic.service';

describe('Component Tests', () => {
  describe('GroupTopic Management Delete Component', () => {
    let comp: GroupTopicDeleteDialogComponent;
    let fixture: ComponentFixture<GroupTopicDeleteDialogComponent>;
    let service: GroupTopicService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [GroupTopicDeleteDialogComponent]
      })
        .overrideTemplate(GroupTopicDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(GroupTopicDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(GroupTopicService);
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
