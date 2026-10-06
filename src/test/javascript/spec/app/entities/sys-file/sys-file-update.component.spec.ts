import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CtTestModule } from '../../../test.module';
import { SysFileUpdateComponent } from 'app/entities/sys-file/sys-file-update.component';
import { SysFileService } from 'app/entities/sys-file/sys-file.service';
import { SysFile } from 'app/shared/model/sys-file.model';

describe('Component Tests', () => {
  describe('SysFile Management Update Component', () => {
    let comp: SysFileUpdateComponent;
    let fixture: ComponentFixture<SysFileUpdateComponent>;
    let service: SysFileService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [SysFileUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(SysFileUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SysFileUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SysFileService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new SysFile(123);
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
        const entity = new SysFile();
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
