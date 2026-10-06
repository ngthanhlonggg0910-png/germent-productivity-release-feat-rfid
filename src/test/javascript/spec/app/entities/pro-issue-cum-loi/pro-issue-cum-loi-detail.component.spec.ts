import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CtTestModule } from '../../../test.module';
import { ProIssueCumLoiDetailComponent } from 'app/entities/pro-issue-cum-loi/pro-issue-cum-loi-detail.component';
import { ProIssueCumLoi } from 'app/shared/model/pro-issue-cum-loi.model';

describe('Component Tests', () => {
  describe('ProIssueCumLoi Management Detail Component', () => {
    let comp: ProIssueCumLoiDetailComponent;
    let fixture: ComponentFixture<ProIssueCumLoiDetailComponent>;
    const route = ({ data: of({ proIssueCumLoi: new ProIssueCumLoi(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [ProIssueCumLoiDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ProIssueCumLoiDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ProIssueCumLoiDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.proIssueCumLoi).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
