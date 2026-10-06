import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CtTestModule } from '../../../test.module';
import { DoKimMdUpdateComponent } from 'app/entities/do-kim-md/do-kim-md-update.component';
import { DoKimMdService } from 'app/entities/do-kim-md/do-kim-md.service';
import { DoKimMd } from 'app/shared/model/do-kim-md.model';

describe('Component Tests', () => {
  describe('DoKimMd Management Update Component', () => {
    let comp: DoKimMdUpdateComponent;
    let fixture: ComponentFixture<DoKimMdUpdateComponent>;
    let service: DoKimMdService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [DoKimMdUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(DoKimMdUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(DoKimMdUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(DoKimMdService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new DoKimMd(123);
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
        const entity = new DoKimMd();
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
