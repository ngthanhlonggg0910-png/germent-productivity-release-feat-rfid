import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CtTestModule } from '../../../test.module';
import { ThongSoDoSPSizeUpdateComponent } from 'app/entities/thong-so-do-sp-size/thong-so-do-sp-size-update.component';
import { ThongSoDoSPSizeService } from 'app/entities/thong-so-do-sp-size/thong-so-do-sp-size.service';
import { ThongSoDoSPSize } from 'app/shared/model/thong-so-do-sp-size.model';

describe('Component Tests', () => {
  describe('ThongSoDoSPSize Management Update Component', () => {
    let comp: ThongSoDoSPSizeUpdateComponent;
    let fixture: ComponentFixture<ThongSoDoSPSizeUpdateComponent>;
    let service: ThongSoDoSPSizeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [ThongSoDoSPSizeUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ThongSoDoSPSizeUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ThongSoDoSPSizeUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ThongSoDoSPSizeService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ThongSoDoSPSize(123);
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
        const entity = new ThongSoDoSPSize();
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
