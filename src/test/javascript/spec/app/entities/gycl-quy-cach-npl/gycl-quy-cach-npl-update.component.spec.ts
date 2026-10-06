import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CtTestModule } from '../../../test.module';
import { GYCLQuyCachNPLUpdateComponent } from 'app/entities/gycl-quy-cach-npl/gycl-quy-cach-npl-update.component';
import { GYCLQuyCachNPLService } from 'app/entities/gycl-quy-cach-npl/gycl-quy-cach-npl.service';
import { GYCLQuyCachNPL } from 'app/shared/model/gycl-quy-cach-npl.model';

describe('Component Tests', () => {
  describe('GYCLQuyCachNPL Management Update Component', () => {
    let comp: GYCLQuyCachNPLUpdateComponent;
    let fixture: ComponentFixture<GYCLQuyCachNPLUpdateComponent>;
    let service: GYCLQuyCachNPLService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [GYCLQuyCachNPLUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(GYCLQuyCachNPLUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(GYCLQuyCachNPLUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(GYCLQuyCachNPLService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new GYCLQuyCachNPL(123);
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
        const entity = new GYCLQuyCachNPL();
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
