import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CtTestModule } from '../../../test.module';
import { ProIssueUpdateComponent } from 'app/entities/pro-issue/pro-issue-update.component';
import { ProIssueService } from 'app/entities/pro-issue/pro-issue.service';
import { ProIssue } from 'app/shared/model/pro-issue.model';

describe('Component Tests', () => {
  describe('ProIssue Management Update Component', () => {
    let comp: ProIssueUpdateComponent;
    let fixture: ComponentFixture<ProIssueUpdateComponent>;
    let service: ProIssueService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [ProIssueUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ProIssueUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ProIssueUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProIssueService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ProIssue(123);
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
        const entity = new ProIssue();
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
