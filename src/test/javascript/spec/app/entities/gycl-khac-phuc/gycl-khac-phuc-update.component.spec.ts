import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CtTestModule } from '../../../test.module';
import { GYCLKhacPhucUpdateComponent } from 'app/entities/gycl-khac-phuc/gycl-khac-phuc-update.component';
import { GYCLKhacPhucService } from 'app/entities/gycl-khac-phuc/gycl-khac-phuc.service';
import { GYCLKhacPhuc } from 'app/shared/model/gycl-khac-phuc.model';

describe('Component Tests', () => {
  describe('GYCLKhacPhuc Management Update Component', () => {
    let comp: GYCLKhacPhucUpdateComponent;
    let fixture: ComponentFixture<GYCLKhacPhucUpdateComponent>;
    let service: GYCLKhacPhucService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [GYCLKhacPhucUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(GYCLKhacPhucUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(GYCLKhacPhucUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(GYCLKhacPhucService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new GYCLKhacPhuc(123);
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
        const entity = new GYCLKhacPhuc();
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
