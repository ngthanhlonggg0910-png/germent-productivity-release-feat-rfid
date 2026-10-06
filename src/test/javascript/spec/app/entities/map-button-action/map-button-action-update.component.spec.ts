import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CtTestModule } from '../../../test.module';
import { MapButtonActionUpdateComponent } from 'app/entities/map-button-action/map-button-action-update.component';
import { MapButtonActionService } from 'app/entities/map-button-action/map-button-action.service';
import { MapButtonAction } from 'app/shared/model/map-button-action.model';

describe('Component Tests', () => {
  describe('MapButtonAction Management Update Component', () => {
    let comp: MapButtonActionUpdateComponent;
    let fixture: ComponentFixture<MapButtonActionUpdateComponent>;
    let service: MapButtonActionService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [MapButtonActionUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MapButtonActionUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MapButtonActionUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MapButtonActionService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MapButtonAction(123);
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
        const entity = new MapButtonAction();
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
