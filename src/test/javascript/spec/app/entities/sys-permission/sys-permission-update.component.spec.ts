import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CtTestModule } from '../../../test.module';
import { SysPermissionUpdateComponent } from 'app/entities/sys-permission/sys-permission-update.component';
import { SysPermissionService } from 'app/entities/sys-permission/sys-permission.service';
import { SysPermission } from 'app/shared/model/sys-permission.model';

describe('Component Tests', () => {
  describe('SysPermission Management Update Component', () => {
    let comp: SysPermissionUpdateComponent;
    let fixture: ComponentFixture<SysPermissionUpdateComponent>;
    let service: SysPermissionService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [SysPermissionUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(SysPermissionUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SysPermissionUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SysPermissionService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new SysPermission(123);
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
        const entity = new SysPermission();
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
