import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CtTestModule } from '../../../test.module';
import { ProKhachHangDetailComponent } from 'app/entities/pro-khach-hang/pro-khach-hang-detail.component';
import { ProKhachHang } from 'app/shared/model/pro-khach-hang.model';

describe('Component Tests', () => {
  describe('ProKhachHang Management Detail Component', () => {
    let comp: ProKhachHangDetailComponent;
    let fixture: ComponentFixture<ProKhachHangDetailComponent>;
    const route = ({ data: of({ proKhachHang: new ProKhachHang(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [ProKhachHangDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ProKhachHangDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ProKhachHangDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.proKhachHang).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
