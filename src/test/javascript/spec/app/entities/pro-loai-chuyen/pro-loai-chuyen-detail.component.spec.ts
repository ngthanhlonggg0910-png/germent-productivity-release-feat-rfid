import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CtTestModule } from '../../../test.module';
import { ProLoaiChuyenDetailComponent } from 'app/entities/pro-loai-chuyen/pro-loai-chuyen-detail.component';
import { ProLoaiChuyen } from 'app/shared/model/pro-loai-chuyen.model';

describe('Component Tests', () => {
  describe('ProLoaiChuyen Management Detail Component', () => {
    let comp: ProLoaiChuyenDetailComponent;
    let fixture: ComponentFixture<ProLoaiChuyenDetailComponent>;
    const route = ({ data: of({ proLoaiChuyen: new ProLoaiChuyen(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [ProLoaiChuyenDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ProLoaiChuyenDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ProLoaiChuyenDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.proLoaiChuyen).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
