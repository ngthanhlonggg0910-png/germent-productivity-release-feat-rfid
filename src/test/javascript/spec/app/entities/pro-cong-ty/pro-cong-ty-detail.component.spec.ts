import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CtTestModule } from '../../../test.module';
import { ProCongTyDetailComponent } from 'app/entities/pro-cong-ty/pro-cong-ty-detail.component';
import { ProCongTy } from 'app/shared/model/pro-cong-ty.model';

describe('Component Tests', () => {
  describe('ProCongTy Management Detail Component', () => {
    let comp: ProCongTyDetailComponent;
    let fixture: ComponentFixture<ProCongTyDetailComponent>;
    const route = ({ data: of({ proCongTy: new ProCongTy(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [ProCongTyDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ProCongTyDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ProCongTyDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.proCongTy).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
