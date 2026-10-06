import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CtTestModule } from '../../../test.module';
import { ProNSCLDetailComponent } from 'app/entities/pro-nscl/pro-nscl-detail.component';
import { ProNSCL } from 'app/shared/model/pro-nscl.model';

describe('Component Tests', () => {
  describe('ProNSCL Management Detail Component', () => {
    let comp: ProNSCLDetailComponent;
    let fixture: ComponentFixture<ProNSCLDetailComponent>;
    const route = ({ data: of({ proNSCL: new ProNSCL(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [ProNSCLDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ProNSCLDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ProNSCLDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load proNSCL on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.proNSCL).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
