import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CtTestModule } from '../../../test.module';
import { ProCumLoiUpdateComponent } from 'app/entities/pro-cum-loi/pro-cum-loi-update.component';
import { ProCumLoiService } from 'app/entities/pro-cum-loi/pro-cum-loi.service';
import { ProCumLoi } from 'app/shared/model/pro-cum-loi.model';

describe('Component Tests', () => {
  describe('ProCumLoi Management Update Component', () => {
    let comp: ProCumLoiUpdateComponent;
    let fixture: ComponentFixture<ProCumLoiUpdateComponent>;
    let service: ProCumLoiService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [ProCumLoiUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ProCumLoiUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ProCumLoiUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProCumLoiService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ProCumLoi(123);
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
        const entity = new ProCumLoi();
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
