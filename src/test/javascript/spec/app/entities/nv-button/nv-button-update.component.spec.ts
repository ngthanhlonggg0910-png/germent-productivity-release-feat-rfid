import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CtTestModule } from '../../../test.module';
import { NVButtonUpdateComponent } from 'app/entities/nv-button/nv-button-update.component';
import { NVButtonService } from 'app/entities/nv-button/nv-button.service';
import { NVButton } from 'app/shared/model/nv-button.model';

describe('Component Tests', () => {
  describe('NVButton Management Update Component', () => {
    let comp: NVButtonUpdateComponent;
    let fixture: ComponentFixture<NVButtonUpdateComponent>;
    let service: NVButtonService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [NVButtonUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(NVButtonUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(NVButtonUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(NVButtonService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new NVButton(123);
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
        const entity = new NVButton();
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
