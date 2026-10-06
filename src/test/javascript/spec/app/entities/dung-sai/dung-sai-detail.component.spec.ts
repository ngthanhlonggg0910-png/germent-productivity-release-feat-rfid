import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CtTestModule } from '../../../test.module';
import { DungSaiDetailComponent } from 'app/entities/dung-sai/dung-sai-detail.component';
import { DungSai } from 'app/shared/model/dung-sai.model';

describe('Component Tests', () => {
  describe('DungSai Management Detail Component', () => {
    let comp: DungSaiDetailComponent;
    let fixture: ComponentFixture<DungSaiDetailComponent>;
    const route = ({ data: of({ dungSai: new DungSai(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [DungSaiDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(DungSaiDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(DungSaiDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.dungSai).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
