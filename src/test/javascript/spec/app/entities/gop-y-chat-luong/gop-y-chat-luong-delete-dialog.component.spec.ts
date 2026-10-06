import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { CtTestModule } from '../../../test.module';
import { GopYChatLuongDeleteDialogComponent } from 'app/entities/gop-y-chat-luong/gop-y-chat-luong-delete-dialog.component';
import { GopYChatLuongService } from 'app/entities/gop-y-chat-luong/gop-y-chat-luong.service';

describe('Component Tests', () => {
  describe('GopYChatLuong Management Delete Component', () => {
    let comp: GopYChatLuongDeleteDialogComponent;
    let fixture: ComponentFixture<GopYChatLuongDeleteDialogComponent>;
    let service: GopYChatLuongService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [GopYChatLuongDeleteDialogComponent]
      })
        .overrideTemplate(GopYChatLuongDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(GopYChatLuongDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(GopYChatLuongService);
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
