import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CtTestModule } from '../../../test.module';
import { ThongSoDoSPUpdateComponent } from 'app/entities/thong-so-do-sp/thong-so-do-sp-update.component';
import { ThongSoDoSPService } from 'app/entities/thong-so-do-sp/thong-so-do-sp.service';
import { ThongSoDoSP } from 'app/shared/model/thong-so-do-sp.model';

describe('Component Tests', () => {
  describe('ThongSoDoSP Management Update Component', () => {
    let comp: ThongSoDoSPUpdateComponent;
    let fixture: ComponentFixture<ThongSoDoSPUpdateComponent>;
    let service: ThongSoDoSPService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [ThongSoDoSPUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ThongSoDoSPUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ThongSoDoSPUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ThongSoDoSPService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ThongSoDoSP(123);
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
        const entity = new ThongSoDoSP();
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
