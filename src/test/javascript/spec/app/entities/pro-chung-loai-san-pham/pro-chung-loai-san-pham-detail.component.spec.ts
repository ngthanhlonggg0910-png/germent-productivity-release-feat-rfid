import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CtTestModule } from '../../../test.module';
import { ProChungLoaiSanPhamDetailComponent } from 'app/entities/pro-chung-loai-san-pham/pro-chung-loai-san-pham-detail.component';
import { ProChungLoaiSanPham } from 'app/shared/model/pro-chung-loai-san-pham.model';

describe('Component Tests', () => {
  describe('ProChungLoaiSanPham Management Detail Component', () => {
    let comp: ProChungLoaiSanPhamDetailComponent;
    let fixture: ComponentFixture<ProChungLoaiSanPhamDetailComponent>;
    const route = ({ data: of({ proChungLoaiSanPham: new ProChungLoaiSanPham(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [ProChungLoaiSanPhamDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ProChungLoaiSanPhamDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ProChungLoaiSanPhamDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.proChungLoaiSanPham).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
