import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CtTestModule } from '../../../test.module';
import { NVButtonTrackingTypeDetailComponent } from 'app/entities/nv-button-tracking-type/nv-button-tracking-type-detail.component';
import { NVButtonTrackingType } from 'app/shared/model/nv-button-tracking-type.model';

describe('Component Tests', () => {
  describe('NVButtonTrackingType Management Detail Component', () => {
    let comp: NVButtonTrackingTypeDetailComponent;
    let fixture: ComponentFixture<NVButtonTrackingTypeDetailComponent>;
    const route = ({ data: of({ nVButtonTrackingType: new NVButtonTrackingType(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [NVButtonTrackingTypeDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(NVButtonTrackingTypeDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(NVButtonTrackingTypeDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.nVButtonTrackingType).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
