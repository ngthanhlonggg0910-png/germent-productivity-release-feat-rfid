import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CtTestModule } from '../../../test.module';
import { ThongSoThucThiDetailComponent } from 'app/entities/thong-so-thuc-thi/thong-so-thuc-thi-detail.component';
import { ThongSoThucThi } from 'app/shared/model/thong-so-thuc-thi.model';

describe('Component Tests', () => {
  describe('ThongSoThucThi Management Detail Component', () => {
    let comp: ThongSoThucThiDetailComponent;
    let fixture: ComponentFixture<ThongSoThucThiDetailComponent>;
    const route = ({ data: of({ thongSoThucThi: new ThongSoThucThi(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [ThongSoThucThiDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ThongSoThucThiDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ThongSoThucThiDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.thongSoThucThi).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
