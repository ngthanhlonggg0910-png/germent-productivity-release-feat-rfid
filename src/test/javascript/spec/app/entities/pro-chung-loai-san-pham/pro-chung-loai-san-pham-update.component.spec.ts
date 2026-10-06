import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CtTestModule } from '../../../test.module';
import { ProChungLoaiSanPhamUpdateComponent } from 'app/entities/pro-chung-loai-san-pham/pro-chung-loai-san-pham-update.component';
import { ProChungLoaiSanPhamService } from 'app/entities/pro-chung-loai-san-pham/pro-chung-loai-san-pham.service';
import { ProChungLoaiSanPham } from 'app/shared/model/pro-chung-loai-san-pham.model';

describe('Component Tests', () => {
  describe('ProChungLoaiSanPham Management Update Component', () => {
    let comp: ProChungLoaiSanPhamUpdateComponent;
    let fixture: ComponentFixture<ProChungLoaiSanPhamUpdateComponent>;
    let service: ProChungLoaiSanPhamService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [ProChungLoaiSanPhamUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ProChungLoaiSanPhamUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ProChungLoaiSanPhamUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProChungLoaiSanPhamService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ProChungLoaiSanPham(123);
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
        const entity = new ProChungLoaiSanPham();
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
