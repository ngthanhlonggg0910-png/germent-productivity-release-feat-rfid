import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CtTestModule } from '../../../test.module';
import { NVButtonTrackingTypeUpdateComponent } from 'app/entities/nv-button-tracking-type/nv-button-tracking-type-update.component';
import { NVButtonTrackingTypeService } from 'app/entities/nv-button-tracking-type/nv-button-tracking-type.service';
import { NVButtonTrackingType } from 'app/shared/model/nv-button-tracking-type.model';

describe('Component Tests', () => {
  describe('NVButtonTrackingType Management Update Component', () => {
    let comp: NVButtonTrackingTypeUpdateComponent;
    let fixture: ComponentFixture<NVButtonTrackingTypeUpdateComponent>;
    let service: NVButtonTrackingTypeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [NVButtonTrackingTypeUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(NVButtonTrackingTypeUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(NVButtonTrackingTypeUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(NVButtonTrackingTypeService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new NVButtonTrackingType(123);
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
        const entity = new NVButtonTrackingType();
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
