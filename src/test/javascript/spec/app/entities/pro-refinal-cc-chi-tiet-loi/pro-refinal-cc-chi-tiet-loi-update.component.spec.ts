import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CtTestModule } from '../../../test.module';
import { ProRefinalCCChiTietLoiUpdateComponent } from 'app/entities/pro-refinal-cc-chi-tiet-loi/pro-refinal-cc-chi-tiet-loi-update.component';
import { ProRefinalCCChiTietLoiService } from 'app/entities/pro-refinal-cc-chi-tiet-loi/pro-refinal-cc-chi-tiet-loi.service';
import { ProRefinalCCChiTietLoi } from 'app/shared/model/pro-refinal-cc-chi-tiet-loi.model';

describe('Component Tests', () => {
  describe('ProRefinalCCChiTietLoi Management Update Component', () => {
    let comp: ProRefinalCCChiTietLoiUpdateComponent;
    let fixture: ComponentFixture<ProRefinalCCChiTietLoiUpdateComponent>;
    let service: ProRefinalCCChiTietLoiService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [ProRefinalCCChiTietLoiUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ProRefinalCCChiTietLoiUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ProRefinalCCChiTietLoiUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProRefinalCCChiTietLoiService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ProRefinalCCChiTietLoi(123);
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
        const entity = new ProRefinalCCChiTietLoi();
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
