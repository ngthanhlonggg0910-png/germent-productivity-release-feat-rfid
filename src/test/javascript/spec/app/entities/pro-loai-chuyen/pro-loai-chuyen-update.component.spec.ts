import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CtTestModule } from '../../../test.module';
import { ProLoaiChuyenUpdateComponent } from 'app/entities/pro-loai-chuyen/pro-loai-chuyen-update.component';
import { ProLoaiChuyenService } from 'app/entities/pro-loai-chuyen/pro-loai-chuyen.service';
import { ProLoaiChuyen } from 'app/shared/model/pro-loai-chuyen.model';

describe('Component Tests', () => {
  describe('ProLoaiChuyen Management Update Component', () => {
    let comp: ProLoaiChuyenUpdateComponent;
    let fixture: ComponentFixture<ProLoaiChuyenUpdateComponent>;
    let service: ProLoaiChuyenService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [ProLoaiChuyenUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ProLoaiChuyenUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ProLoaiChuyenUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProLoaiChuyenService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ProLoaiChuyen(123);
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
        const entity = new ProLoaiChuyen();
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
