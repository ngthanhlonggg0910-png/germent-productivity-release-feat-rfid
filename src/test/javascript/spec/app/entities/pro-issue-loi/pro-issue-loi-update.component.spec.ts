import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CtTestModule } from '../../../test.module';
import { ProIssueLoiUpdateComponent } from 'app/entities/pro-issue-loi/pro-issue-loi-update.component';
import { ProIssueLoiService } from 'app/entities/pro-issue-loi/pro-issue-loi.service';
import { ProIssueLoi } from 'app/shared/model/pro-issue-loi.model';

describe('Component Tests', () => {
  describe('ProIssueLoi Management Update Component', () => {
    let comp: ProIssueLoiUpdateComponent;
    let fixture: ComponentFixture<ProIssueLoiUpdateComponent>;
    let service: ProIssueLoiService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [ProIssueLoiUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ProIssueLoiUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ProIssueLoiUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProIssueLoiService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ProIssueLoi(123);
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
        const entity = new ProIssueLoi();
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
