import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CtTestModule } from '../../../test.module';
import { ProIssueLoiDetailComponent } from 'app/entities/pro-issue-loi/pro-issue-loi-detail.component';
import { ProIssueLoi } from 'app/shared/model/pro-issue-loi.model';

describe('Component Tests', () => {
  describe('ProIssueLoi Management Detail Component', () => {
    let comp: ProIssueLoiDetailComponent;
    let fixture: ComponentFixture<ProIssueLoiDetailComponent>;
    const route = ({ data: of({ proIssueLoi: new ProIssueLoi(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [ProIssueLoiDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ProIssueLoiDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ProIssueLoiDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.proIssueLoi).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
