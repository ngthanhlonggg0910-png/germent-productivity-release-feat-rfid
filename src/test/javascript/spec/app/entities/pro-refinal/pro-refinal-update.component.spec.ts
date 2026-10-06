import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CtTestModule } from '../../../test.module';
import { ProRefinalUpdateComponent } from 'app/entities/pro-refinal/pro-refinal-update.component';
import { ProRefinalService } from 'app/entities/pro-refinal/pro-refinal.service';
import { ProRefinal } from 'app/shared/model/pro-refinal.model';

describe('Component Tests', () => {
  describe('ProRefinal Management Update Component', () => {
    let comp: ProRefinalUpdateComponent;
    let fixture: ComponentFixture<ProRefinalUpdateComponent>;
    let service: ProRefinalService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [ProRefinalUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ProRefinalUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ProRefinalUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProRefinalService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ProRefinal(123);
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
        const entity = new ProRefinal();
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
