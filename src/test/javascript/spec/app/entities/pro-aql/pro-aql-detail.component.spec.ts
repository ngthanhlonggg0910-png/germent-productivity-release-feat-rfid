import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CtTestModule } from '../../../test.module';
import { ProAqlDetailComponent } from 'app/entities/pro-aql/pro-aql-detail.component';
import { ProAql } from 'app/shared/model/pro-aql.model';

describe('Component Tests', () => {
  describe('ProAql Management Detail Component', () => {
    let comp: ProAqlDetailComponent;
    let fixture: ComponentFixture<ProAqlDetailComponent>;
    const route = ({ data: of({ proAql: new ProAql(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [ProAqlDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ProAqlDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ProAqlDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.proAql).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
