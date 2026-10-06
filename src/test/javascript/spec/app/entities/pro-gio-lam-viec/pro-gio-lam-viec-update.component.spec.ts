import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CtTestModule } from '../../../test.module';
import { ProGioLamViecUpdateComponent } from 'app/entities/pro-gio-lam-viec/pro-gio-lam-viec-update.component';
import { ProGioLamViecService } from 'app/entities/pro-gio-lam-viec/pro-gio-lam-viec.service';
import { ProGioLamViec } from 'app/shared/model/pro-gio-lam-viec.model';

describe('Component Tests', () => {
  describe('ProGioLamViec Management Update Component', () => {
    let comp: ProGioLamViecUpdateComponent;
    let fixture: ComponentFixture<ProGioLamViecUpdateComponent>;
    let service: ProGioLamViecService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [ProGioLamViecUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ProGioLamViecUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ProGioLamViecUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProGioLamViecService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ProGioLamViec(123);
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
        const entity = new ProGioLamViec();
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
