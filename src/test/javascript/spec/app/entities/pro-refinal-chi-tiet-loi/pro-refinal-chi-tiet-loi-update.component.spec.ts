import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CtTestModule } from '../../../test.module';
import { ProRefinalChiTietLoiUpdateComponent } from 'app/entities/pro-refinal-chi-tiet-loi/pro-refinal-chi-tiet-loi-update.component';
import { ProRefinalChiTietLoiService } from 'app/entities/pro-refinal-chi-tiet-loi/pro-refinal-chi-tiet-loi.service';
import { ProRefinalChiTietLoi } from 'app/shared/model/pro-refinal-chi-tiet-loi.model';

describe('Component Tests', () => {
  describe('ProRefinalChiTietLoi Management Update Component', () => {
    let comp: ProRefinalChiTietLoiUpdateComponent;
    let fixture: ComponentFixture<ProRefinalChiTietLoiUpdateComponent>;
    let service: ProRefinalChiTietLoiService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [ProRefinalChiTietLoiUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ProRefinalChiTietLoiUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ProRefinalChiTietLoiUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProRefinalChiTietLoiService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ProRefinalChiTietLoi(123);
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
        const entity = new ProRefinalChiTietLoi();
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
