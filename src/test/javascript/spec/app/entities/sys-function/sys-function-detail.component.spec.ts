import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CtTestModule } from '../../../test.module';
import { SysFunctionDetailComponent } from 'app/entities/sys-function/sys-function-detail.component';
import { SysFunction } from 'app/shared/model/sys-function.model';

describe('Component Tests', () => {
  describe('SysFunction Management Detail Component', () => {
    let comp: SysFunctionDetailComponent;
    let fixture: ComponentFixture<SysFunctionDetailComponent>;
    const route = ({ data: of({ sysFunction: new SysFunction(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [SysFunctionDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(SysFunctionDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(SysFunctionDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.sysFunction).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
