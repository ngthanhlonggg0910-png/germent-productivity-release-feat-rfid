import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CtTestModule } from '../../../test.module';
import { DoKimMdDetailComponent } from 'app/entities/do-kim-md/do-kim-md-detail.component';
import { DoKimMd } from 'app/shared/model/do-kim-md.model';

describe('Component Tests', () => {
  describe('DoKimMd Management Detail Component', () => {
    let comp: DoKimMdDetailComponent;
    let fixture: ComponentFixture<DoKimMdDetailComponent>;
    const route = ({ data: of({ doKimMd: new DoKimMd(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [DoKimMdDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(DoKimMdDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(DoKimMdDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.doKimMd).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
