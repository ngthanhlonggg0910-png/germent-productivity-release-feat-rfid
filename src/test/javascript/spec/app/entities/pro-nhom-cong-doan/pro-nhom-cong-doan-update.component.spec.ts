import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CtTestModule } from '../../../test.module';
import { ProNhomCongDoanUpdateComponent } from 'app/entities/pro-nhom-cong-doan/pro-nhom-cong-doan-update.component';
import { ProNhomCongDoanService } from 'app/entities/pro-nhom-cong-doan/pro-nhom-cong-doan.service';
import { ProNhomCongDoan } from 'app/shared/model/pro-nhom-cong-doan.model';

describe('Component Tests', () => {
  describe('ProNhomCongDoan Management Update Component', () => {
    let comp: ProNhomCongDoanUpdateComponent;
    let fixture: ComponentFixture<ProNhomCongDoanUpdateComponent>;
    let service: ProNhomCongDoanService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [ProNhomCongDoanUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ProNhomCongDoanUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ProNhomCongDoanUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProNhomCongDoanService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ProNhomCongDoan(123);
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
        const entity = new ProNhomCongDoan();
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
