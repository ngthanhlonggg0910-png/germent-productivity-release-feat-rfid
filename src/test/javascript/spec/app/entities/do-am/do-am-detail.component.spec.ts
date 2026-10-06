import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CtTestModule } from '../../../test.module';
import { DoAmDetailComponent } from 'app/entities/do-am/do-am-detail.component';
import { DoAm } from 'app/shared/model/do-am.model';

describe('Component Tests', () => {
  describe('DoAm Management Detail Component', () => {
    let comp: DoAmDetailComponent;
    let fixture: ComponentFixture<DoAmDetailComponent>;
    const route = ({ data: of({ doAm: new DoAm(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [DoAmDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(DoAmDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(DoAmDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.doAm).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
