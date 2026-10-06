import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CtTestModule } from '../../../test.module';
import { ButtonTrackingDetailComponent } from 'app/entities/button-tracking/button-tracking-detail.component';
import { ButtonTracking } from 'app/shared/model/button-tracking.model';

describe('Component Tests', () => {
  describe('ButtonTracking Management Detail Component', () => {
    let comp: ButtonTrackingDetailComponent;
    let fixture: ComponentFixture<ButtonTrackingDetailComponent>;
    const route = ({ data: of({ buttonTracking: new ButtonTracking(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [ButtonTrackingDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ButtonTrackingDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ButtonTrackingDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.buttonTracking).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
