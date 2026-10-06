import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CtTestModule } from '../../../test.module';
import { ThongSoThucThiUpdateComponent } from 'app/entities/thong-so-thuc-thi/thong-so-thuc-thi-update.component';
import { ThongSoThucThiService } from 'app/entities/thong-so-thuc-thi/thong-so-thuc-thi.service';
import { ThongSoThucThi } from 'app/shared/model/thong-so-thuc-thi.model';

describe('Component Tests', () => {
  describe('ThongSoThucThi Management Update Component', () => {
    let comp: ThongSoThucThiUpdateComponent;
    let fixture: ComponentFixture<ThongSoThucThiUpdateComponent>;
    let service: ThongSoThucThiService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [ThongSoThucThiUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ThongSoThucThiUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ThongSoThucThiUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ThongSoThucThiService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ThongSoThucThi(123);
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
        const entity = new ThongSoThucThi();
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
