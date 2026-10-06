import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CtTestModule } from '../../../test.module';
import { LibSoLuongPoKiemUpdateComponent } from 'app/entities/lib-so-luong-po-kiem/lib-so-luong-po-kiem-update.component';
import { LibSoLuongPoKiemService } from 'app/entities/lib-so-luong-po-kiem/lib-so-luong-po-kiem.service';
import { LibSoLuongPoKiem } from 'app/shared/model/lib-so-luong-po-kiem.model';

describe('Component Tests', () => {
  describe('LibSoLuongPoKiem Management Update Component', () => {
    let comp: LibSoLuongPoKiemUpdateComponent;
    let fixture: ComponentFixture<LibSoLuongPoKiemUpdateComponent>;
    let service: LibSoLuongPoKiemService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [LibSoLuongPoKiemUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(LibSoLuongPoKiemUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(LibSoLuongPoKiemUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(LibSoLuongPoKiemService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new LibSoLuongPoKiem(123);
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
        const entity = new LibSoLuongPoKiem();
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
