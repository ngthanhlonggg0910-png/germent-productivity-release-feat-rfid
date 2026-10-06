import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CtTestModule } from '../../../test.module';
import { ProFinalDetailComponent } from 'app/entities/pro-final/pro-final-detail.component';
import { ProFinal } from 'app/shared/model/pro-final.model';

describe('Component Tests', () => {
  describe('ProFinal Management Detail Component', () => {
    let comp: ProFinalDetailComponent;
    let fixture: ComponentFixture<ProFinalDetailComponent>;
    const route = ({ data: of({ proFinal: new ProFinal(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [ProFinalDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ProFinalDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ProFinalDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.proFinal).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
