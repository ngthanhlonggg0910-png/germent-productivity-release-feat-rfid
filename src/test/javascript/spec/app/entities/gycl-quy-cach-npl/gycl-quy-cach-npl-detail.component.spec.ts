import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CtTestModule } from '../../../test.module';
import { GYCLQuyCachNPLDetailComponent } from 'app/entities/gycl-quy-cach-npl/gycl-quy-cach-npl-detail.component';
import { GYCLQuyCachNPL } from 'app/shared/model/gycl-quy-cach-npl.model';

describe('Component Tests', () => {
  describe('GYCLQuyCachNPL Management Detail Component', () => {
    let comp: GYCLQuyCachNPLDetailComponent;
    let fixture: ComponentFixture<GYCLQuyCachNPLDetailComponent>;
    const route = ({ data: of({ gYCLQuyCachNPL: new GYCLQuyCachNPL(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [GYCLQuyCachNPLDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(GYCLQuyCachNPLDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(GYCLQuyCachNPLDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.gYCLQuyCachNPL).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
