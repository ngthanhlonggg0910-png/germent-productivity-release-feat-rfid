import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CtTestModule } from '../../../test.module';
import { ProKhachHangUpdateComponent } from 'app/entities/pro-khach-hang/pro-khach-hang-update.component';
import { ProKhachHangService } from 'app/entities/pro-khach-hang/pro-khach-hang.service';
import { ProKhachHang } from 'app/shared/model/pro-khach-hang.model';

describe('Component Tests', () => {
  describe('ProKhachHang Management Update Component', () => {
    let comp: ProKhachHangUpdateComponent;
    let fixture: ComponentFixture<ProKhachHangUpdateComponent>;
    let service: ProKhachHangService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [ProKhachHangUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ProKhachHangUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ProKhachHangUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProKhachHangService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ProKhachHang(123);
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
        const entity = new ProKhachHang();
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
