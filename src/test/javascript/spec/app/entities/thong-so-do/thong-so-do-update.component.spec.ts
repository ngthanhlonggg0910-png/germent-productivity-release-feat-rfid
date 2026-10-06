import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CtTestModule } from '../../../test.module';
import { ThongSoDoUpdateComponent } from 'app/entities/thong-so-do/thong-so-do-update.component';
import { ThongSoDoService } from 'app/entities/thong-so-do/thong-so-do.service';
import { ThongSoDo } from 'app/shared/model/thong-so-do.model';

describe('Component Tests', () => {
  describe('ThongSoDo Management Update Component', () => {
    let comp: ThongSoDoUpdateComponent;
    let fixture: ComponentFixture<ThongSoDoUpdateComponent>;
    let service: ThongSoDoService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [ThongSoDoUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ThongSoDoUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ThongSoDoUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ThongSoDoService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ThongSoDo(123);
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
        const entity = new ThongSoDo();
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
