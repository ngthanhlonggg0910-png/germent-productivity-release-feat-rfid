import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CtTestModule } from '../../../test.module';
import { MapButtonActionDetailComponent } from 'app/entities/map-button-action/map-button-action-detail.component';
import { MapButtonAction } from 'app/shared/model/map-button-action.model';

describe('Component Tests', () => {
  describe('MapButtonAction Management Detail Component', () => {
    let comp: MapButtonActionDetailComponent;
    let fixture: ComponentFixture<MapButtonActionDetailComponent>;
    const route = ({ data: of({ mapButtonAction: new MapButtonAction(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [MapButtonActionDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MapButtonActionDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MapButtonActionDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mapButtonAction).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
