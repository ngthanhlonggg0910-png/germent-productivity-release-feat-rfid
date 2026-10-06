import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CtTestModule } from '../../../test.module';
import { ThongSoDoDetailComponent } from 'app/entities/thong-so-do/thong-so-do-detail.component';
import { ThongSoDo } from 'app/shared/model/thong-so-do.model';

describe('Component Tests', () => {
  describe('ThongSoDo Management Detail Component', () => {
    let comp: ThongSoDoDetailComponent;
    let fixture: ComponentFixture<ThongSoDoDetailComponent>;
    const route = ({ data: of({ thongSoDo: new ThongSoDo(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [ThongSoDoDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ThongSoDoDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ThongSoDoDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.thongSoDo).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
