import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CtTestModule } from '../../../test.module';
import { SoLuongHangKiemDetailComponent } from 'app/entities/so-luong-hang-kiem/so-luong-hang-kiem-detail.component';
import { SoLuongHangKiem } from 'app/shared/model/so-luong-hang-kiem.model';

describe('Component Tests', () => {
  describe('SoLuongHangKiem Management Detail Component', () => {
    let comp: SoLuongHangKiemDetailComponent;
    let fixture: ComponentFixture<SoLuongHangKiemDetailComponent>;
    const route = ({ data: of({ soLuongHangKiem: new SoLuongHangKiem(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [SoLuongHangKiemDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(SoLuongHangKiemDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(SoLuongHangKiemDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.soLuongHangKiem).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
