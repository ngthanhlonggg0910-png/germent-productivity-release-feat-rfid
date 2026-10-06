import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CtTestModule } from '../../../test.module';
import { ProCongTyUpdateComponent } from 'app/entities/pro-cong-ty/pro-cong-ty-update.component';
import { ProCongTyService } from 'app/entities/pro-cong-ty/pro-cong-ty.service';
import { ProCongTy } from 'app/shared/model/pro-cong-ty.model';

describe('Component Tests', () => {
  describe('ProCongTy Management Update Component', () => {
    let comp: ProCongTyUpdateComponent;
    let fixture: ComponentFixture<ProCongTyUpdateComponent>;
    let service: ProCongTyService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [ProCongTyUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ProCongTyUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ProCongTyUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProCongTyService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ProCongTy(123);
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
        const entity = new ProCongTy();
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
