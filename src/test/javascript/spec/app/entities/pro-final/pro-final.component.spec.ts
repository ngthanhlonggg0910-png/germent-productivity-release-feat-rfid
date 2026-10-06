import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { CtTestModule } from '../../../test.module';
import { ProFinalComponent } from 'app/entities/pro-final/pro-final.component';
import { ProFinalService } from 'app/entities/pro-final/pro-final.service';
import { ProFinal } from 'app/shared/model/pro-final.model';

describe('Component Tests', () => {
  describe('ProFinal Management Component', () => {
    let comp: ProFinalComponent;
    let fixture: ComponentFixture<ProFinalComponent>;
    let service: ProFinalService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [ProFinalComponent],
        providers: []
      })
        .overrideTemplate(ProFinalComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ProFinalComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProFinalService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ProFinal(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.proFinals[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
