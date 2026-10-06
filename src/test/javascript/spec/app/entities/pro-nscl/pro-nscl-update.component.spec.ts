import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CtTestModule } from '../../../test.module';
import { ProNSCLUpdateComponent } from 'app/entities/pro-nscl/pro-nscl-update.component';
import { ProNSCLService } from 'app/entities/pro-nscl/pro-nscl.service';
import { ProNSCL } from 'app/shared/model/pro-nscl.model';

describe('Component Tests', () => {
  describe('ProNSCL Management Update Component', () => {
    let comp: ProNSCLUpdateComponent;
    let fixture: ComponentFixture<ProNSCLUpdateComponent>;
    let service: ProNSCLService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [ProNSCLUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ProNSCLUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ProNSCLUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProNSCLService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ProNSCL(123);
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
        const entity = new ProNSCL();
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
