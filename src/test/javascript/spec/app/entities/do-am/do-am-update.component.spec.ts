import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CtTestModule } from '../../../test.module';
import { DoAmUpdateComponent } from 'app/entities/do-am/do-am-update.component';
import { DoAmService } from 'app/entities/do-am/do-am.service';
import { DoAm } from 'app/shared/model/do-am.model';

describe('Component Tests', () => {
  describe('DoAm Management Update Component', () => {
    let comp: DoAmUpdateComponent;
    let fixture: ComponentFixture<DoAmUpdateComponent>;
    let service: DoAmService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [DoAmUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(DoAmUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(DoAmUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(DoAmService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new DoAm(123);
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
        const entity = new DoAm();
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
