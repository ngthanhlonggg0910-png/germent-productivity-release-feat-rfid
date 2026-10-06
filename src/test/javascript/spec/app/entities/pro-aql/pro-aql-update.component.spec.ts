import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CtTestModule } from '../../../test.module';
import { ProAqlUpdateComponent } from 'app/entities/pro-aql/pro-aql-update.component';
import { ProAqlService } from 'app/entities/pro-aql/pro-aql.service';
import { ProAql } from 'app/shared/model/pro-aql.model';

describe('Component Tests', () => {
  describe('ProAql Management Update Component', () => {
    let comp: ProAqlUpdateComponent;
    let fixture: ComponentFixture<ProAqlUpdateComponent>;
    let service: ProAqlService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [ProAqlUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ProAqlUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ProAqlUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProAqlService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ProAql(123);
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
        const entity = new ProAql();
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
