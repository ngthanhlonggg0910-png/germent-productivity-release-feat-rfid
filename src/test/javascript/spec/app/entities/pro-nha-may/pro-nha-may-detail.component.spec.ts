import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CtTestModule } from '../../../test.module';
import { ProNhaMayDetailComponent } from 'app/entities/pro-nha-may/pro-nha-may-detail.component';
import { ProNhaMay } from 'app/shared/model/pro-nha-may.model';

describe('Component Tests', () => {
  describe('ProNhaMay Management Detail Component', () => {
    let comp: ProNhaMayDetailComponent;
    let fixture: ComponentFixture<ProNhaMayDetailComponent>;
    const route = ({ data: of({ proNhaMay: new ProNhaMay(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [ProNhaMayDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ProNhaMayDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ProNhaMayDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.proNhaMay).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
