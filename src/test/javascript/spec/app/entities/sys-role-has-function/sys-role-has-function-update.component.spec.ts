import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CtTestModule } from '../../../test.module';
import { SysRoleHasFunctionUpdateComponent } from 'app/entities/sys-role-has-function/sys-role-has-function-update.component';
import { SysRoleHasFunctionService } from 'app/entities/sys-role-has-function/sys-role-has-function.service';
import { SysRoleHasFunction } from 'app/shared/model/sys-role-has-function.model';

describe('Component Tests', () => {
  describe('SysRoleHasFunction Management Update Component', () => {
    let comp: SysRoleHasFunctionUpdateComponent;
    let fixture: ComponentFixture<SysRoleHasFunctionUpdateComponent>;
    let service: SysRoleHasFunctionService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [SysRoleHasFunctionUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(SysRoleHasFunctionUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SysRoleHasFunctionUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SysRoleHasFunctionService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new SysRoleHasFunction(123);
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
        const entity = new SysRoleHasFunction();
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
