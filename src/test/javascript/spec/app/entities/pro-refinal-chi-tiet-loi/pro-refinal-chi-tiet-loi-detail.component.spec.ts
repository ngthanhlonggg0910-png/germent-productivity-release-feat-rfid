import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CtTestModule } from '../../../test.module';
import { ProRefinalChiTietLoiDetailComponent } from 'app/entities/pro-refinal-chi-tiet-loi/pro-refinal-chi-tiet-loi-detail.component';
import { ProRefinalChiTietLoi } from 'app/shared/model/pro-refinal-chi-tiet-loi.model';

describe('Component Tests', () => {
  describe('ProRefinalChiTietLoi Management Detail Component', () => {
    let comp: ProRefinalChiTietLoiDetailComponent;
    let fixture: ComponentFixture<ProRefinalChiTietLoiDetailComponent>;
    const route = ({ data: of({ proRefinalChiTietLoi: new ProRefinalChiTietLoi(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [ProRefinalChiTietLoiDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ProRefinalChiTietLoiDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ProRefinalChiTietLoiDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.proRefinalChiTietLoi).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
