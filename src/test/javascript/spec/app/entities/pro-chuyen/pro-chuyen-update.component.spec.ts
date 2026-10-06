import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CtTestModule } from '../../../test.module';
import { ProChuyenUpdateComponent } from 'app/entities/pro-chuyen/pro-chuyen-update.component';
import { ProChuyenService } from 'app/entities/pro-chuyen/pro-chuyen.service';
import { ProChuyen } from 'app/shared/model/pro-chuyen.model';

describe('Component Tests', () => {
  describe('ProChuyen Management Update Component', () => {
    let comp: ProChuyenUpdateComponent;
    let fixture: ComponentFixture<ProChuyenUpdateComponent>;
    let service: ProChuyenService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [ProChuyenUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ProChuyenUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ProChuyenUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProChuyenService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ProChuyen(123);
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
        const entity = new ProChuyen();
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
