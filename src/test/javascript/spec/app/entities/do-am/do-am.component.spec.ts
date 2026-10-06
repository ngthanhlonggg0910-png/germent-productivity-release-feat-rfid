import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { CtTestModule } from '../../../test.module';
import { DoAmComponent } from 'app/entities/do-am/do-am.component';
import { DoAmService } from 'app/entities/do-am/do-am.service';
import { DoAm } from 'app/shared/model/do-am.model';

describe('Component Tests', () => {
  describe('DoAm Management Component', () => {
    let comp: DoAmComponent;
    let fixture: ComponentFixture<DoAmComponent>;
    let service: DoAmService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [DoAmComponent],
        providers: []
      })
        .overrideTemplate(DoAmComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(DoAmComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(DoAmService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new DoAm(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.doAms[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
