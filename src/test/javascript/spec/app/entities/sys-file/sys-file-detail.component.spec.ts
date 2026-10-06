import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CtTestModule } from '../../../test.module';
import { SysFileDetailComponent } from 'app/entities/sys-file/sys-file-detail.component';
import { SysFile } from 'app/shared/model/sys-file.model';

describe('Component Tests', () => {
  describe('SysFile Management Detail Component', () => {
    let comp: SysFileDetailComponent;
    let fixture: ComponentFixture<SysFileDetailComponent>;
    const route = ({ data: of({ sysFile: new SysFile(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [SysFileDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(SysFileDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(SysFileDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.sysFile).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
