import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CtTestModule } from '../../../test.module';
import { GroupTopicUpdateComponent } from 'app/entities/group-topic/group-topic-update.component';
import { GroupTopicService } from 'app/entities/group-topic/group-topic.service';
import { GroupTopic } from 'app/shared/model/group-topic.model';

describe('Component Tests', () => {
  describe('GroupTopic Management Update Component', () => {
    let comp: GroupTopicUpdateComponent;
    let fixture: ComponentFixture<GroupTopicUpdateComponent>;
    let service: GroupTopicService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [GroupTopicUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(GroupTopicUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(GroupTopicUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(GroupTopicService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new GroupTopic(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new GroupTopic();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
