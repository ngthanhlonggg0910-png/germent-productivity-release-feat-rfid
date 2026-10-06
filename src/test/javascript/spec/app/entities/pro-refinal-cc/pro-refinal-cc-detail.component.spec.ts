import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CtTestModule } from '../../../test.module';
import { ProRefinalCCDetailComponent } from 'app/entities/pro-refinal-cc/pro-refinal-cc-detail.component';
import { ProRefinalCC } from 'app/shared/model/pro-refinal-cc.model';

describe('Component Tests', () => {
  describe('ProRefinalCC Management Detail Component', () => {
    let comp: ProRefinalCCDetailComponent;
    let fixture: ComponentFixture<ProRefinalCCDetailComponent>;
    const route = ({ data: of({ proRefinalCC: new ProRefinalCC(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [ProRefinalCCDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ProRefinalCCDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ProRefinalCCDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.proRefinalCC).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
