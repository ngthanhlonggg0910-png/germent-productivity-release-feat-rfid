import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CtTestModule } from '../../../test.module';
import { GYCLKhacPhucDetailComponent } from 'app/entities/gycl-khac-phuc/gycl-khac-phuc-detail.component';
import { GYCLKhacPhuc } from 'app/shared/model/gycl-khac-phuc.model';

describe('Component Tests', () => {
  describe('GYCLKhacPhuc Management Detail Component', () => {
    let comp: GYCLKhacPhucDetailComponent;
    let fixture: ComponentFixture<GYCLKhacPhucDetailComponent>;
    const route = ({ data: of({ gYCLKhacPhuc: new GYCLKhacPhuc(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [GYCLKhacPhucDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(GYCLKhacPhucDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(GYCLKhacPhucDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.gYCLKhacPhuc).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
