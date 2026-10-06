import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CtTestModule } from '../../../test.module';
import { ButtonTrackingUpdateComponent } from 'app/entities/button-tracking/button-tracking-update.component';
import { ButtonTrackingService } from 'app/entities/button-tracking/button-tracking.service';
import { ButtonTracking } from 'app/shared/model/button-tracking.model';

describe('Component Tests', () => {
  describe('ButtonTracking Management Update Component', () => {
    let comp: ButtonTrackingUpdateComponent;
    let fixture: ComponentFixture<ButtonTrackingUpdateComponent>;
    let service: ButtonTrackingService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [ButtonTrackingUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ButtonTrackingUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ButtonTrackingUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ButtonTrackingService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ButtonTracking(123);
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
        const entity = new ButtonTracking();
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
