import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CtTestModule } from '../../../test.module';
import { DungSaiUpdateComponent } from 'app/entities/dung-sai/dung-sai-update.component';
import { DungSaiService } from 'app/entities/dung-sai/dung-sai.service';
import { DungSai } from 'app/shared/model/dung-sai.model';

describe('Component Tests', () => {
  describe('DungSai Management Update Component', () => {
    let comp: DungSaiUpdateComponent;
    let fixture: ComponentFixture<DungSaiUpdateComponent>;
    let service: DungSaiService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [DungSaiUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(DungSaiUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(DungSaiUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(DungSaiService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new DungSai(123);
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
        const entity = new DungSai();
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
