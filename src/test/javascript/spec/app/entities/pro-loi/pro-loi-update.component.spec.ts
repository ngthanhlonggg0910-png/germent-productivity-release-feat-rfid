import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CtTestModule } from '../../../test.module';
import { ProLoiUpdateComponent } from 'app/entities/pro-loi/pro-loi-update.component';
import { ProLoiService } from 'app/entities/pro-loi/pro-loi.service';
import { ProLoi } from 'app/shared/model/pro-loi.model';

describe('Component Tests', () => {
  describe('ProLoi Management Update Component', () => {
    let comp: ProLoiUpdateComponent;
    let fixture: ComponentFixture<ProLoiUpdateComponent>;
    let service: ProLoiService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [ProLoiUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ProLoiUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ProLoiUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProLoiService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ProLoi(123);
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
        const entity = new ProLoi();
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
