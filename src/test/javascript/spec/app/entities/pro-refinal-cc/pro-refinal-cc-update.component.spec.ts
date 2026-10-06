import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CtTestModule } from '../../../test.module';
import { ProRefinalCCUpdateComponent } from 'app/entities/pro-refinal-cc/pro-refinal-cc-update.component';
import { ProRefinalCCService } from 'app/entities/pro-refinal-cc/pro-refinal-cc.service';
import { ProRefinalCC } from 'app/shared/model/pro-refinal-cc.model';

describe('Component Tests', () => {
  describe('ProRefinalCC Management Update Component', () => {
    let comp: ProRefinalCCUpdateComponent;
    let fixture: ComponentFixture<ProRefinalCCUpdateComponent>;
    let service: ProRefinalCCService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [ProRefinalCCUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ProRefinalCCUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ProRefinalCCUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProRefinalCCService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ProRefinalCC(123);
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
        const entity = new ProRefinalCC();
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
