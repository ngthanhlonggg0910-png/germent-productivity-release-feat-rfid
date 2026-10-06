import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CtTestModule } from '../../../test.module';
import { ProFinalChiTietLoiUpdateComponent } from 'app/entities/pro-final-chi-tiet-loi/pro-final-chi-tiet-loi-update.component';
import { ProFinalChiTietLoiService } from 'app/entities/pro-final-chi-tiet-loi/pro-final-chi-tiet-loi.service';
import { ProFinalChiTietLoi } from 'app/shared/model/pro-final-chi-tiet-loi.model';

describe('Component Tests', () => {
  describe('ProFinalChiTietLoi Management Update Component', () => {
    let comp: ProFinalChiTietLoiUpdateComponent;
    let fixture: ComponentFixture<ProFinalChiTietLoiUpdateComponent>;
    let service: ProFinalChiTietLoiService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [ProFinalChiTietLoiUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ProFinalChiTietLoiUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ProFinalChiTietLoiUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProFinalChiTietLoiService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ProFinalChiTietLoi(123);
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
        const entity = new ProFinalChiTietLoi();
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
