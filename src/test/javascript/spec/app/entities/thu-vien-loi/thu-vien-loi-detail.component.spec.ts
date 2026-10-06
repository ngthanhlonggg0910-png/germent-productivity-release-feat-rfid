import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CtTestModule } from '../../../test.module';
import { ThuVienLoiDetailComponent } from 'app/entities/thu-vien-loi/thu-vien-loi-detail.component';
import { ThuVienLoi } from 'app/shared/model/thu-vien-loi.model';

describe('Component Tests', () => {
  describe('ThuVienLoi Management Detail Component', () => {
    let comp: ThuVienLoiDetailComponent;
    let fixture: ComponentFixture<ThuVienLoiDetailComponent>;
    const route = ({ data: of({ thuVienLoi: new ThuVienLoi(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [ThuVienLoiDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ThuVienLoiDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ThuVienLoiDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.thuVienLoi).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
