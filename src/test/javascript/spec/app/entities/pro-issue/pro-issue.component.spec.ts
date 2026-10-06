import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { CtTestModule } from '../../../test.module';
import { ProIssueComponent } from 'app/entities/pro-issue/pro-issue.component';
import { ProIssueService } from 'app/entities/pro-issue/pro-issue.service';
import { ProIssue } from 'app/shared/model/pro-issue.model';

describe('Component Tests', () => {
  describe('ProIssue Management Component', () => {
    let comp: ProIssueComponent;
    let fixture: ComponentFixture<ProIssueComponent>;
    let service: ProIssueService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [ProIssueComponent],
        providers: []
      })
        .overrideTemplate(ProIssueComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ProIssueComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProIssueService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ProIssue(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.proIssues[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
