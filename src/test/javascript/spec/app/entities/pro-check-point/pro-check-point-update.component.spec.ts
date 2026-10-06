import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CtTestModule } from '../../../test.module';
import { ProCheckPointUpdateComponent } from 'app/entities/pro-check-point/pro-check-point-update.component';
import { ProCheckPointService } from 'app/entities/pro-check-point/pro-check-point.service';
import { ProCheckPoint } from 'app/shared/model/pro-check-point.model';

describe('Component Tests', () => {
  describe('ProCheckPoint Management Update Component', () => {
    let comp: ProCheckPointUpdateComponent;
    let fixture: ComponentFixture<ProCheckPointUpdateComponent>;
    let service: ProCheckPointService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [ProCheckPointUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ProCheckPointUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ProCheckPointUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProCheckPointService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ProCheckPoint(123);
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
        const entity = new ProCheckPoint();
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
