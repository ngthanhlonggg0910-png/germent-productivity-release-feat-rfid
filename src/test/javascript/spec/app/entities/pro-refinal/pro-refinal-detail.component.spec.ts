import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CtTestModule } from '../../../test.module';
import { ProRefinalDetailComponent } from 'app/entities/pro-refinal/pro-refinal-detail.component';
import { ProRefinal } from 'app/shared/model/pro-refinal.model';

describe('Component Tests', () => {
  describe('ProRefinal Management Detail Component', () => {
    let comp: ProRefinalDetailComponent;
    let fixture: ComponentFixture<ProRefinalDetailComponent>;
    const route = ({ data: of({ proRefinal: new ProRefinal(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [ProRefinalDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ProRefinalDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ProRefinalDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.proRefinal).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
