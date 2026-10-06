import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CtTestModule } from '../../../test.module';
import { ProSanPhamUpdateComponent } from 'app/entities/pro-san-pham/pro-san-pham-update.component';
import { ProSanPhamService } from 'app/entities/pro-san-pham/pro-san-pham.service';
import { ProSanPham } from 'app/shared/model/pro-san-pham.model';

describe('Component Tests', () => {
  describe('ProSanPham Management Update Component', () => {
    let comp: ProSanPhamUpdateComponent;
    let fixture: ComponentFixture<ProSanPhamUpdateComponent>;
    let service: ProSanPhamService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [ProSanPhamUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ProSanPhamUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ProSanPhamUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProSanPhamService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ProSanPham(123);
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
        const entity = new ProSanPham();
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
