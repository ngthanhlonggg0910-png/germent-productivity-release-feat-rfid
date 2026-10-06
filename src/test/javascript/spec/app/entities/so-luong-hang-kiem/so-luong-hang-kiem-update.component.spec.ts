import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CtTestModule } from '../../../test.module';
import { SoLuongHangKiemUpdateComponent } from 'app/entities/so-luong-hang-kiem/so-luong-hang-kiem-update.component';
import { SoLuongHangKiemService } from 'app/entities/so-luong-hang-kiem/so-luong-hang-kiem.service';
import { SoLuongHangKiem } from 'app/shared/model/so-luong-hang-kiem.model';

describe('Component Tests', () => {
  describe('SoLuongHangKiem Management Update Component', () => {
    let comp: SoLuongHangKiemUpdateComponent;
    let fixture: ComponentFixture<SoLuongHangKiemUpdateComponent>;
    let service: SoLuongHangKiemService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [SoLuongHangKiemUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(SoLuongHangKiemUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SoLuongHangKiemUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SoLuongHangKiemService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new SoLuongHangKiem(123);
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
        const entity = new SoLuongHangKiem();
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
