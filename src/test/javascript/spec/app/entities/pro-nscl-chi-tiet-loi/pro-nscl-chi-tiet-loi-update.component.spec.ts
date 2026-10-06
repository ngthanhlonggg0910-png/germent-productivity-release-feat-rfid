import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CtTestModule } from '../../../test.module';
import { ProNSCLChiTietLoiUpdateComponent } from 'app/entities/pro-nscl-chi-tiet-loi/pro-nscl-chi-tiet-loi-update.component';
import { ProNSCLChiTietLoiService } from 'app/entities/pro-nscl-chi-tiet-loi/pro-nscl-chi-tiet-loi.service';
import { ProNSCLChiTietLoi } from 'app/shared/model/pro-nscl-chi-tiet-loi.model';

describe('Component Tests', () => {
  describe('ProNSCLChiTietLoi Management Update Component', () => {
    let comp: ProNSCLChiTietLoiUpdateComponent;
    let fixture: ComponentFixture<ProNSCLChiTietLoiUpdateComponent>;
    let service: ProNSCLChiTietLoiService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [ProNSCLChiTietLoiUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ProNSCLChiTietLoiUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ProNSCLChiTietLoiUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProNSCLChiTietLoiService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ProNSCLChiTietLoi(123);
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
        const entity = new ProNSCLChiTietLoi();
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
