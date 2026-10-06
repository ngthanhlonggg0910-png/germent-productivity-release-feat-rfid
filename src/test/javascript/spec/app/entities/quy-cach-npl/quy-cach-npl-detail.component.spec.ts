import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CtTestModule } from '../../../test.module';
import { QuyCachNPLDetailComponent } from 'app/entities/quy-cach-npl/quy-cach-npl-detail.component';
import { QuyCachNPL } from 'app/shared/model/quy-cach-npl.model';

describe('Component Tests', () => {
  describe('QuyCachNPL Management Detail Component', () => {
    let comp: QuyCachNPLDetailComponent;
    let fixture: ComponentFixture<QuyCachNPLDetailComponent>;
    const route = ({ data: of({ quyCachNPL: new QuyCachNPL(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [QuyCachNPLDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(QuyCachNPLDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(QuyCachNPLDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.quyCachNPL).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
