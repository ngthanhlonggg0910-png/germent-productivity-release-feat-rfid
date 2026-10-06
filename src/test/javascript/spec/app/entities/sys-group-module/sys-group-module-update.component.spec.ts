import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CtTestModule } from '../../../test.module';
import { SysGroupModuleUpdateComponent } from 'app/entities/sys-group-module/sys-group-module-update.component';
import { SysGroupModuleService } from 'app/entities/sys-group-module/sys-group-module.service';
import { SysGroupModule } from 'app/shared/model/sys-group-module.model';

describe('Component Tests', () => {
  describe('SysGroupModule Management Update Component', () => {
    let comp: SysGroupModuleUpdateComponent;
    let fixture: ComponentFixture<SysGroupModuleUpdateComponent>;
    let service: SysGroupModuleService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [SysGroupModuleUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(SysGroupModuleUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SysGroupModuleUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SysGroupModuleService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new SysGroupModule(123);
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
        const entity = new SysGroupModule();
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
