import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CtTestModule } from '../../../test.module';
import { ProCumLoiDetailComponent } from 'app/entities/pro-cum-loi/pro-cum-loi-detail.component';
import { ProCumLoi } from 'app/shared/model/pro-cum-loi.model';

describe('Component Tests', () => {
  describe('ProCumLoi Management Detail Component', () => {
    let comp: ProCumLoiDetailComponent;
    let fixture: ComponentFixture<ProCumLoiDetailComponent>;
    const route = ({ data: of({ proCumLoi: new ProCumLoi(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [ProCumLoiDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ProCumLoiDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ProCumLoiDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.proCumLoi).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
