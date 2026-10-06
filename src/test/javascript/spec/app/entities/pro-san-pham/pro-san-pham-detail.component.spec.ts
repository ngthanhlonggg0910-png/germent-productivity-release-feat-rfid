import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CtTestModule } from '../../../test.module';
import { ProSanPhamDetailComponent } from 'app/entities/pro-san-pham/pro-san-pham-detail.component';
import { ProSanPham } from 'app/shared/model/pro-san-pham.model';

describe('Component Tests', () => {
  describe('ProSanPham Management Detail Component', () => {
    let comp: ProSanPhamDetailComponent;
    let fixture: ComponentFixture<ProSanPhamDetailComponent>;
    const route = ({ data: of({ proSanPham: new ProSanPham(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [ProSanPhamDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ProSanPhamDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ProSanPhamDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.proSanPham).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
