import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CtTestModule } from '../../../test.module';
import { ProTaiKhoanCheckPointUpdateComponent } from 'app/entities/pro-tai-khoan-check-point/pro-tai-khoan-check-point-update.component';
import { ProTaiKhoanCheckPointService } from 'app/entities/pro-tai-khoan-check-point/pro-tai-khoan-check-point.service';
import { ProTaiKhoanCheckPoint } from 'app/shared/model/pro-tai-khoan-check-point.model';

describe('Component Tests', () => {
  describe('ProTaiKhoanCheckPoint Management Update Component', () => {
    let comp: ProTaiKhoanCheckPointUpdateComponent;
    let fixture: ComponentFixture<ProTaiKhoanCheckPointUpdateComponent>;
    let service: ProTaiKhoanCheckPointService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [ProTaiKhoanCheckPointUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ProTaiKhoanCheckPointUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ProTaiKhoanCheckPointUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProTaiKhoanCheckPointService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ProTaiKhoanCheckPoint(123);
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
        const entity = new ProTaiKhoanCheckPoint();
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
