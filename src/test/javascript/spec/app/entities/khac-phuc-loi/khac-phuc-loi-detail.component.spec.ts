import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CtTestModule } from '../../../test.module';
import { KhacPhucLoiDetailComponent } from 'app/entities/khac-phuc-loi/khac-phuc-loi-detail.component';
import { KhacPhucLoi } from 'app/shared/model/khac-phuc-loi.model';

describe('Component Tests', () => {
  describe('KhacPhucLoi Management Detail Component', () => {
    let comp: KhacPhucLoiDetailComponent;
    let fixture: ComponentFixture<KhacPhucLoiDetailComponent>;
    const route = ({ data: of({ khacPhucLoi: new KhacPhucLoi(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [KhacPhucLoiDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(KhacPhucLoiDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(KhacPhucLoiDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.khacPhucLoi).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
