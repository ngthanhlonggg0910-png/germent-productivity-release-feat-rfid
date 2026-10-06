import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { CtTestModule } from '../../../test.module';
import { ProIssueCumLoiComponent } from 'app/entities/pro-issue-cum-loi/pro-issue-cum-loi.component';
import { ProIssueCumLoiService } from 'app/entities/pro-issue-cum-loi/pro-issue-cum-loi.service';
import { ProIssueCumLoi } from 'app/shared/model/pro-issue-cum-loi.model';

describe('Component Tests', () => {
  describe('ProIssueCumLoi Management Component', () => {
    let comp: ProIssueCumLoiComponent;
    let fixture: ComponentFixture<ProIssueCumLoiComponent>;
    let service: ProIssueCumLoiService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [ProIssueCumLoiComponent],
        providers: []
      })
        .overrideTemplate(ProIssueCumLoiComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ProIssueCumLoiComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProIssueCumLoiService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ProIssueCumLoi(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.proIssueCumLois[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
