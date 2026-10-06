import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CtTestModule } from '../../../test.module';
import { NVButtonDetailComponent } from 'app/entities/nv-button/nv-button-detail.component';
import { NVButton } from 'app/shared/model/nv-button.model';

describe('Component Tests', () => {
  describe('NVButton Management Detail Component', () => {
    let comp: NVButtonDetailComponent;
    let fixture: ComponentFixture<NVButtonDetailComponent>;
    const route = ({ data: of({ nVButton: new NVButton(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [NVButtonDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(NVButtonDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(NVButtonDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.nVButton).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
