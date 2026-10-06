import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CtTestModule } from '../../../test.module';
import { CauHinhZaloDetailComponent } from 'app/entities/cau-hinh-zalo/cau-hinh-zalo-detail.component';
import { CauHinhZalo } from 'app/shared/model/cau-hinh-zalo.model';

describe('Component Tests', () => {
  describe('CauHinhZalo Management Detail Component', () => {
    let comp: CauHinhZaloDetailComponent;
    let fixture: ComponentFixture<CauHinhZaloDetailComponent>;
    const route = ({ data: of({ cauHinhZalo: new CauHinhZalo(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [CauHinhZaloDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(CauHinhZaloDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CauHinhZaloDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.cauHinhZalo).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
