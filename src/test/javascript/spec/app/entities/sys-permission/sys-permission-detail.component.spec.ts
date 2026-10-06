import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CtTestModule } from '../../../test.module';
import { SysPermissionDetailComponent } from 'app/entities/sys-permission/sys-permission-detail.component';
import { SysPermission } from 'app/shared/model/sys-permission.model';

describe('Component Tests', () => {
  describe('SysPermission Management Detail Component', () => {
    let comp: SysPermissionDetailComponent;
    let fixture: ComponentFixture<SysPermissionDetailComponent>;
    const route = ({ data: of({ sysPermission: new SysPermission(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [SysPermissionDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(SysPermissionDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(SysPermissionDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.sysPermission).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
