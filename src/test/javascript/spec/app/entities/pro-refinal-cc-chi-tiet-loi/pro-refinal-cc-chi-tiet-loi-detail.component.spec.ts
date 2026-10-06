import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CtTestModule } from '../../../test.module';
import { ProRefinalCCChiTietLoiDetailComponent } from 'app/entities/pro-refinal-cc-chi-tiet-loi/pro-refinal-cc-chi-tiet-loi-detail.component';
import { ProRefinalCCChiTietLoi } from 'app/shared/model/pro-refinal-cc-chi-tiet-loi.model';

describe('Component Tests', () => {
  describe('ProRefinalCCChiTietLoi Management Detail Component', () => {
    let comp: ProRefinalCCChiTietLoiDetailComponent;
    let fixture: ComponentFixture<ProRefinalCCChiTietLoiDetailComponent>;
    const route = ({ data: of({ proRefinalCCChiTietLoi: new ProRefinalCCChiTietLoi(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [ProRefinalCCChiTietLoiDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ProRefinalCCChiTietLoiDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ProRefinalCCChiTietLoiDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.proRefinalCCChiTietLoi).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
