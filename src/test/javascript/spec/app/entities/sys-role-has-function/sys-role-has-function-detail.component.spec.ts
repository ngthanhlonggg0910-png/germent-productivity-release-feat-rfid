import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CtTestModule } from '../../../test.module';
import { SysRoleHasFunctionDetailComponent } from 'app/entities/sys-role-has-function/sys-role-has-function-detail.component';
import { SysRoleHasFunction } from 'app/shared/model/sys-role-has-function.model';

describe('Component Tests', () => {
  describe('SysRoleHasFunction Management Detail Component', () => {
    let comp: SysRoleHasFunctionDetailComponent;
    let fixture: ComponentFixture<SysRoleHasFunctionDetailComponent>;
    const route = ({ data: of({ sysRoleHasFunction: new SysRoleHasFunction(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [SysRoleHasFunctionDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(SysRoleHasFunctionDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(SysRoleHasFunctionDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.sysRoleHasFunction).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
