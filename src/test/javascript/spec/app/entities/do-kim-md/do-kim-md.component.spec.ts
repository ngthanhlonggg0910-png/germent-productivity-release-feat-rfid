import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { CtTestModule } from '../../../test.module';
import { DoKimMdComponent } from 'app/entities/do-kim-md/do-kim-md.component';
import { DoKimMdService } from 'app/entities/do-kim-md/do-kim-md.service';
import { DoKimMd } from 'app/shared/model/do-kim-md.model';

describe('Component Tests', () => {
  describe('DoKimMd Management Component', () => {
    let comp: DoKimMdComponent;
    let fixture: ComponentFixture<DoKimMdComponent>;
    let service: DoKimMdService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [DoKimMdComponent],
        providers: []
      })
        .overrideTemplate(DoKimMdComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(DoKimMdComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(DoKimMdService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new DoKimMd(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.doKimMds[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
