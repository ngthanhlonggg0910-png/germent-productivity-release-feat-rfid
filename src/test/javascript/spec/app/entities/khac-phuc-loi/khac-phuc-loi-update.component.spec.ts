import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CtTestModule } from '../../../test.module';
import { KhacPhucLoiUpdateComponent } from 'app/entities/khac-phuc-loi/khac-phuc-loi-update.component';
import { KhacPhucLoiService } from 'app/entities/khac-phuc-loi/khac-phuc-loi.service';
import { KhacPhucLoi } from 'app/shared/model/khac-phuc-loi.model';

describe('Component Tests', () => {
  describe('KhacPhucLoi Management Update Component', () => {
    let comp: KhacPhucLoiUpdateComponent;
    let fixture: ComponentFixture<KhacPhucLoiUpdateComponent>;
    let service: KhacPhucLoiService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [KhacPhucLoiUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(KhacPhucLoiUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(KhacPhucLoiUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(KhacPhucLoiService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new KhacPhucLoi(123);
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
        const entity = new KhacPhucLoi();
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
