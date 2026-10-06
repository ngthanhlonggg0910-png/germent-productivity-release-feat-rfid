import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CtTestModule } from '../../../test.module';
import { ProCongDoanUpdateComponent } from 'app/entities/pro-cong-doan/pro-cong-doan-update.component';
import { ProCongDoanService } from 'app/entities/pro-cong-doan/pro-cong-doan.service';
import { ProCongDoan } from 'app/shared/model/pro-cong-doan.model';

describe('Component Tests', () => {
  describe('ProCongDoan Management Update Component', () => {
    let comp: ProCongDoanUpdateComponent;
    let fixture: ComponentFixture<ProCongDoanUpdateComponent>;
    let service: ProCongDoanService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [ProCongDoanUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ProCongDoanUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ProCongDoanUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProCongDoanService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ProCongDoan(123);
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
        const entity = new ProCongDoan();
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
