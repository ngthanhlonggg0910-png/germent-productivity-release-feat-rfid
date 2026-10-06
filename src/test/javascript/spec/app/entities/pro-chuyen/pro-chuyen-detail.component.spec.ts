import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CtTestModule } from '../../../test.module';
import { ProChuyenDetailComponent } from 'app/entities/pro-chuyen/pro-chuyen-detail.component';
import { ProChuyen } from 'app/shared/model/pro-chuyen.model';

describe('Component Tests', () => {
  describe('ProChuyen Management Detail Component', () => {
    let comp: ProChuyenDetailComponent;
    let fixture: ComponentFixture<ProChuyenDetailComponent>;
    const route = ({ data: of({ proChuyen: new ProChuyen(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [ProChuyenDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ProChuyenDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ProChuyenDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.proChuyen).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
