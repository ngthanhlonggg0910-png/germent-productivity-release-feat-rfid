import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CtTestModule } from '../../../test.module';
import { ProTenMuaUpdateComponent } from 'app/entities/pro-ten-mua/pro-ten-mua-update.component';
import { ProTenMuaService } from 'app/entities/pro-ten-mua/pro-ten-mua.service';
import { ProTenMua } from 'app/shared/model/pro-ten-mua.model';

describe('Component Tests', () => {
  describe('ProTenMua Management Update Component', () => {
    let comp: ProTenMuaUpdateComponent;
    let fixture: ComponentFixture<ProTenMuaUpdateComponent>;
    let service: ProTenMuaService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [ProTenMuaUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ProTenMuaUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ProTenMuaUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProTenMuaService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ProTenMua(123);
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
        const entity = new ProTenMua();
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
