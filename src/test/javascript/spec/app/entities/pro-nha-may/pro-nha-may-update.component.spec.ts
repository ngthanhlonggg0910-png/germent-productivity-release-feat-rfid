import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CtTestModule } from '../../../test.module';
import { ProNhaMayUpdateComponent } from 'app/entities/pro-nha-may/pro-nha-may-update.component';
import { ProNhaMayService } from 'app/entities/pro-nha-may/pro-nha-may.service';
import { ProNhaMay } from 'app/shared/model/pro-nha-may.model';

describe('Component Tests', () => {
  describe('ProNhaMay Management Update Component', () => {
    let comp: ProNhaMayUpdateComponent;
    let fixture: ComponentFixture<ProNhaMayUpdateComponent>;
    let service: ProNhaMayService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [ProNhaMayUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ProNhaMayUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ProNhaMayUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProNhaMayService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ProNhaMay(123);
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
        const entity = new ProNhaMay();
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
