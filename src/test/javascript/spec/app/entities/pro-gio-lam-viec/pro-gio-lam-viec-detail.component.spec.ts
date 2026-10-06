import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CtTestModule } from '../../../test.module';
import { ProGioLamViecDetailComponent } from 'app/entities/pro-gio-lam-viec/pro-gio-lam-viec-detail.component';
import { ProGioLamViec } from 'app/shared/model/pro-gio-lam-viec.model';

describe('Component Tests', () => {
  describe('ProGioLamViec Management Detail Component', () => {
    let comp: ProGioLamViecDetailComponent;
    let fixture: ComponentFixture<ProGioLamViecDetailComponent>;
    const route = ({ data: of({ proGioLamViec: new ProGioLamViec(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [ProGioLamViecDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ProGioLamViecDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ProGioLamViecDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.proGioLamViec).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
