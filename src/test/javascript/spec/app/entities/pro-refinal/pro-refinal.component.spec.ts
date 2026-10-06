import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { CtTestModule } from '../../../test.module';
import { ProRefinalComponent } from 'app/entities/pro-refinal/pro-refinal.component';
import { ProRefinalService } from 'app/entities/pro-refinal/pro-refinal.service';
import { ProRefinal } from 'app/shared/model/pro-refinal.model';

describe('Component Tests', () => {
  describe('ProRefinal Management Component', () => {
    let comp: ProRefinalComponent;
    let fixture: ComponentFixture<ProRefinalComponent>;
    let service: ProRefinalService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [ProRefinalComponent],
        providers: []
      })
        .overrideTemplate(ProRefinalComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ProRefinalComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProRefinalService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ProRefinal(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.proRefinals[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
