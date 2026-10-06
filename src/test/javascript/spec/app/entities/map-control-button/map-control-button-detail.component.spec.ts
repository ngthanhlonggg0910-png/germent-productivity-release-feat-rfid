import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CtTestModule } from '../../../test.module';
import { MapControlButtonDetailComponent } from 'app/entities/map-control-button/map-control-button-detail.component';
import { MapControlButton } from 'app/shared/model/map-control-button.model';

describe('Component Tests', () => {
  describe('MapControlButton Management Detail Component', () => {
    let comp: MapControlButtonDetailComponent;
    let fixture: ComponentFixture<MapControlButtonDetailComponent>;
    const route = ({ data: of({ mapControlButton: new MapControlButton(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [MapControlButtonDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MapControlButtonDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MapControlButtonDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mapControlButton).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
