import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CtTestModule } from '../../../test.module';
import { ProFinalChiTietLoiDetailComponent } from 'app/entities/pro-final-chi-tiet-loi/pro-final-chi-tiet-loi-detail.component';
import { ProFinalChiTietLoi } from 'app/shared/model/pro-final-chi-tiet-loi.model';

describe('Component Tests', () => {
  describe('ProFinalChiTietLoi Management Detail Component', () => {
    let comp: ProFinalChiTietLoiDetailComponent;
    let fixture: ComponentFixture<ProFinalChiTietLoiDetailComponent>;
    const route = ({ data: of({ proFinalChiTietLoi: new ProFinalChiTietLoi(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [ProFinalChiTietLoiDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ProFinalChiTietLoiDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ProFinalChiTietLoiDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.proFinalChiTietLoi).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
