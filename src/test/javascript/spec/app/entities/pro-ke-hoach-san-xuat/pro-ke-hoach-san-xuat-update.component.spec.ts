import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CtTestModule } from '../../../test.module';
import { ProKeHoachSanXuatUpdateComponent } from 'app/entities/pro-ke-hoach-san-xuat/pro-ke-hoach-san-xuat-update.component';
import { ProKeHoachSanXuatService } from 'app/entities/pro-ke-hoach-san-xuat/pro-ke-hoach-san-xuat.service';
import { ProKeHoachSanXuat } from 'app/shared/model/pro-ke-hoach-san-xuat.model';

describe('Component Tests', () => {
  describe('ProKeHoachSanXuat Management Update Component', () => {
    let comp: ProKeHoachSanXuatUpdateComponent;
    let fixture: ComponentFixture<ProKeHoachSanXuatUpdateComponent>;
    let service: ProKeHoachSanXuatService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [ProKeHoachSanXuatUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ProKeHoachSanXuatUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ProKeHoachSanXuatUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProKeHoachSanXuatService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ProKeHoachSanXuat(123);
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
        const entity = new ProKeHoachSanXuat();
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
