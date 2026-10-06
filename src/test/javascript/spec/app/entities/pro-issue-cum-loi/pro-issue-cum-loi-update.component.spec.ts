import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CtTestModule } from '../../../test.module';
import { ProIssueCumLoiUpdateComponent } from 'app/entities/pro-issue-cum-loi/pro-issue-cum-loi-update.component';
import { ProIssueCumLoiService } from 'app/entities/pro-issue-cum-loi/pro-issue-cum-loi.service';
import { ProIssueCumLoi } from 'app/shared/model/pro-issue-cum-loi.model';

describe('Component Tests', () => {
  describe('ProIssueCumLoi Management Update Component', () => {
    let comp: ProIssueCumLoiUpdateComponent;
    let fixture: ComponentFixture<ProIssueCumLoiUpdateComponent>;
    let service: ProIssueCumLoiService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [ProIssueCumLoiUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ProIssueCumLoiUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ProIssueCumLoiUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProIssueCumLoiService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ProIssueCumLoi(123);
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
        const entity = new ProIssueCumLoi();
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
