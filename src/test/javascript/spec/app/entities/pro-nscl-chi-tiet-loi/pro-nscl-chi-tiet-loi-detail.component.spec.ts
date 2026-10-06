import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CtTestModule } from '../../../test.module';
import { ProNSCLChiTietLoiDetailComponent } from 'app/entities/pro-nscl-chi-tiet-loi/pro-nscl-chi-tiet-loi-detail.component';
import { ProNSCLChiTietLoi } from 'app/shared/model/pro-nscl-chi-tiet-loi.model';

describe('Component Tests', () => {
  describe('ProNSCLChiTietLoi Management Detail Component', () => {
    let comp: ProNSCLChiTietLoiDetailComponent;
    let fixture: ComponentFixture<ProNSCLChiTietLoiDetailComponent>;
    const route = ({ data: of({ proNSCLChiTietLoi: new ProNSCLChiTietLoi(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [ProNSCLChiTietLoiDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ProNSCLChiTietLoiDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ProNSCLChiTietLoiDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.proNSCLChiTietLoi).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
