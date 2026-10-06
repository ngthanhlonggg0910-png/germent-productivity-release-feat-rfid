import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CtTestModule } from '../../../test.module';
import { ProCongDoanDetailComponent } from 'app/entities/pro-cong-doan/pro-cong-doan-detail.component';
import { ProCongDoan } from 'app/shared/model/pro-cong-doan.model';

describe('Component Tests', () => {
  describe('ProCongDoan Management Detail Component', () => {
    let comp: ProCongDoanDetailComponent;
    let fixture: ComponentFixture<ProCongDoanDetailComponent>;
    const route = ({ data: of({ proCongDoan: new ProCongDoan(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [ProCongDoanDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ProCongDoanDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ProCongDoanDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.proCongDoan).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
