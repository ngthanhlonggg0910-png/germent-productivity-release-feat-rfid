import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CtTestModule } from '../../../test.module';
import { ProNhomCongDoanDetailComponent } from 'app/entities/pro-nhom-cong-doan/pro-nhom-cong-doan-detail.component';
import { ProNhomCongDoan } from 'app/shared/model/pro-nhom-cong-doan.model';

describe('Component Tests', () => {
  describe('ProNhomCongDoan Management Detail Component', () => {
    let comp: ProNhomCongDoanDetailComponent;
    let fixture: ComponentFixture<ProNhomCongDoanDetailComponent>;
    const route = ({ data: of({ proNhomCongDoan: new ProNhomCongDoan(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [ProNhomCongDoanDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ProNhomCongDoanDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ProNhomCongDoanDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.proNhomCongDoan).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
