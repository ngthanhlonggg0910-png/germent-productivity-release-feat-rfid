import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CtTestModule } from '../../../test.module';
import { ProTenMuaDetailComponent } from 'app/entities/pro-ten-mua/pro-ten-mua-detail.component';
import { ProTenMua } from 'app/shared/model/pro-ten-mua.model';

describe('Component Tests', () => {
  describe('ProTenMua Management Detail Component', () => {
    let comp: ProTenMuaDetailComponent;
    let fixture: ComponentFixture<ProTenMuaDetailComponent>;
    const route = ({ data: of({ proTenMua: new ProTenMua(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [ProTenMuaDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ProTenMuaDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ProTenMuaDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.proTenMua).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
