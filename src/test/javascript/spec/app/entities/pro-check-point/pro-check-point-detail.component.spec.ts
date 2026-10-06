import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CtTestModule } from '../../../test.module';
import { ProCheckPointDetailComponent } from 'app/entities/pro-check-point/pro-check-point-detail.component';
import { ProCheckPoint } from 'app/shared/model/pro-check-point.model';

describe('Component Tests', () => {
  describe('ProCheckPoint Management Detail Component', () => {
    let comp: ProCheckPointDetailComponent;
    let fixture: ComponentFixture<ProCheckPointDetailComponent>;
    const route = ({ data: of({ proCheckPoint: new ProCheckPoint(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [ProCheckPointDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ProCheckPointDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ProCheckPointDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.proCheckPoint).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
