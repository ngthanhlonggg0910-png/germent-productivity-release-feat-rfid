import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CtTestModule } from '../../../test.module';
import { ProFinalUpdateComponent } from 'app/entities/pro-final/pro-final-update.component';
import { ProFinalService } from 'app/entities/pro-final/pro-final.service';
import { ProFinal } from 'app/shared/model/pro-final.model';

describe('Component Tests', () => {
  describe('ProFinal Management Update Component', () => {
    let comp: ProFinalUpdateComponent;
    let fixture: ComponentFixture<ProFinalUpdateComponent>;
    let service: ProFinalService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [ProFinalUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ProFinalUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ProFinalUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProFinalService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ProFinal(123);
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
        const entity = new ProFinal();
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
