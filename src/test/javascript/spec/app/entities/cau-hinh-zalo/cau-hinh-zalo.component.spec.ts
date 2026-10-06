import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { CtTestModule } from '../../../test.module';
import { CauHinhZaloComponent } from 'app/entities/cau-hinh-zalo/cau-hinh-zalo.component';
import { CauHinhZaloService } from 'app/entities/cau-hinh-zalo/cau-hinh-zalo.service';
import { CauHinhZalo } from 'app/shared/model/cau-hinh-zalo.model';

describe('Component Tests', () => {
  describe('CauHinhZalo Management Component', () => {
    let comp: CauHinhZaloComponent;
    let fixture: ComponentFixture<CauHinhZaloComponent>;
    let service: CauHinhZaloService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [CauHinhZaloComponent],
        providers: []
      })
        .overrideTemplate(CauHinhZaloComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CauHinhZaloComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CauHinhZaloService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new CauHinhZalo(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.cauHinhZalos[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
