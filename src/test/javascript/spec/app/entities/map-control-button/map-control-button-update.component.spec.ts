import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CtTestModule } from '../../../test.module';
import { MapControlButtonUpdateComponent } from 'app/entities/map-control-button/map-control-button-update.component';
import { MapControlButtonService } from 'app/entities/map-control-button/map-control-button.service';
import { MapControlButton } from 'app/shared/model/map-control-button.model';

describe('Component Tests', () => {
  describe('MapControlButton Management Update Component', () => {
    let comp: MapControlButtonUpdateComponent;
    let fixture: ComponentFixture<MapControlButtonUpdateComponent>;
    let service: MapControlButtonService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [MapControlButtonUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MapControlButtonUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MapControlButtonUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MapControlButtonService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MapControlButton(123);
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
        const entity = new MapControlButton();
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
