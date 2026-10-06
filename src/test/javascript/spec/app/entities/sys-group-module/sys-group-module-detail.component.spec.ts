import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CtTestModule } from '../../../test.module';
import { SysGroupModuleDetailComponent } from 'app/entities/sys-group-module/sys-group-module-detail.component';
import { SysGroupModule } from 'app/shared/model/sys-group-module.model';

describe('Component Tests', () => {
  describe('SysGroupModule Management Detail Component', () => {
    let comp: SysGroupModuleDetailComponent;
    let fixture: ComponentFixture<SysGroupModuleDetailComponent>;
    const route = ({ data: of({ sysGroupModule: new SysGroupModule(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [SysGroupModuleDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(SysGroupModuleDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(SysGroupModuleDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.sysGroupModule).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
