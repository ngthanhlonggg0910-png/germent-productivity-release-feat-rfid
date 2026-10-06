import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CtTestModule } from '../../../test.module';
import { SysFunctionUpdateComponent } from 'app/entities/sys-function/sys-function-update.component';
import { SysFunctionService } from 'app/entities/sys-function/sys-function.service';
import { SysFunction } from 'app/shared/model/sys-function.model';

describe('Component Tests', () => {
  describe('SysFunction Management Update Component', () => {
    let comp: SysFunctionUpdateComponent;
    let fixture: ComponentFixture<SysFunctionUpdateComponent>;
    let service: SysFunctionService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [SysFunctionUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(SysFunctionUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SysFunctionUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SysFunctionService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new SysFunction(123);
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
        const entity = new SysFunction();
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
