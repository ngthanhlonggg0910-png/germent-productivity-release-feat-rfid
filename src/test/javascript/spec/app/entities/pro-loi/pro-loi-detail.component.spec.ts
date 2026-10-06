import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CtTestModule } from '../../../test.module';
import { ProLoiDetailComponent } from 'app/entities/pro-loi/pro-loi-detail.component';
import { ProLoi } from 'app/shared/model/pro-loi.model';

describe('Component Tests', () => {
  describe('ProLoi Management Detail Component', () => {
    let comp: ProLoiDetailComponent;
    let fixture: ComponentFixture<ProLoiDetailComponent>;
    const route = ({ data: of({ proLoi: new ProLoi(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [ProLoiDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ProLoiDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ProLoiDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.proLoi).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
