import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CtTestModule } from '../../../test.module';
import { ThongSoDoSPSizeDetailComponent } from 'app/entities/thong-so-do-sp-size/thong-so-do-sp-size-detail.component';
import { ThongSoDoSPSize } from 'app/shared/model/thong-so-do-sp-size.model';

describe('Component Tests', () => {
  describe('ThongSoDoSPSize Management Detail Component', () => {
    let comp: ThongSoDoSPSizeDetailComponent;
    let fixture: ComponentFixture<ThongSoDoSPSizeDetailComponent>;
    const route = ({ data: of({ thongSoDoSPSize: new ThongSoDoSPSize(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [ThongSoDoSPSizeDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ThongSoDoSPSizeDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ThongSoDoSPSizeDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.thongSoDoSPSize).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
