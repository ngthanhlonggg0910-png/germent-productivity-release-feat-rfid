import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CtTestModule } from '../../../test.module';
import { ThongSoDoSPDetailComponent } from 'app/entities/thong-so-do-sp/thong-so-do-sp-detail.component';
import { ThongSoDoSP } from 'app/shared/model/thong-so-do-sp.model';

describe('Component Tests', () => {
  describe('ThongSoDoSP Management Detail Component', () => {
    let comp: ThongSoDoSPDetailComponent;
    let fixture: ComponentFixture<ThongSoDoSPDetailComponent>;
    const route = ({ data: of({ thongSoDoSP: new ThongSoDoSP(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [ThongSoDoSPDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ThongSoDoSPDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ThongSoDoSPDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.thongSoDoSP).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
