import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { CtTestModule } from '../../../test.module';
import { ProIssueLoiComponent } from 'app/entities/pro-issue-loi/pro-issue-loi.component';
import { ProIssueLoiService } from 'app/entities/pro-issue-loi/pro-issue-loi.service';
import { ProIssueLoi } from 'app/shared/model/pro-issue-loi.model';

describe('Component Tests', () => {
  describe('ProIssueLoi Management Component', () => {
    let comp: ProIssueLoiComponent;
    let fixture: ComponentFixture<ProIssueLoiComponent>;
    let service: ProIssueLoiService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [ProIssueLoiComponent],
        providers: []
      })
        .overrideTemplate(ProIssueLoiComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ProIssueLoiComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProIssueLoiService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ProIssueLoi(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.proIssueLois[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
