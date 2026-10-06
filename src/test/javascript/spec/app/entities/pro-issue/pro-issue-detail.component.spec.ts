import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CtTestModule } from '../../../test.module';
import { ProIssueDetailComponent } from 'app/entities/pro-issue/pro-issue-detail.component';
import { ProIssue } from 'app/shared/model/pro-issue.model';

describe('Component Tests', () => {
  describe('ProIssue Management Detail Component', () => {
    let comp: ProIssueDetailComponent;
    let fixture: ComponentFixture<ProIssueDetailComponent>;
    const route = ({ data: of({ proIssue: new ProIssue(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [ProIssueDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ProIssueDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ProIssueDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.proIssue).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
