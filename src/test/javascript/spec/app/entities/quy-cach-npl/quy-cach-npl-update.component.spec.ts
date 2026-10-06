import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CtTestModule } from '../../../test.module';
import { QuyCachNPLUpdateComponent } from 'app/entities/quy-cach-npl/quy-cach-npl-update.component';
import { QuyCachNPLService } from 'app/entities/quy-cach-npl/quy-cach-npl.service';
import { QuyCachNPL } from 'app/shared/model/quy-cach-npl.model';

describe('Component Tests', () => {
  describe('QuyCachNPL Management Update Component', () => {
    let comp: QuyCachNPLUpdateComponent;
    let fixture: ComponentFixture<QuyCachNPLUpdateComponent>;
    let service: QuyCachNPLService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [QuyCachNPLUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(QuyCachNPLUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(QuyCachNPLUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(QuyCachNPLService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new QuyCachNPL(123);
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
        const entity = new QuyCachNPL();
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
