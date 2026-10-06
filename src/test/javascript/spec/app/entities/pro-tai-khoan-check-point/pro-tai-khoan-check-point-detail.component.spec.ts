import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CtTestModule } from '../../../test.module';
import { ProTaiKhoanCheckPointDetailComponent } from 'app/entities/pro-tai-khoan-check-point/pro-tai-khoan-check-point-detail.component';
import { ProTaiKhoanCheckPoint } from 'app/shared/model/pro-tai-khoan-check-point.model';

describe('Component Tests', () => {
  describe('ProTaiKhoanCheckPoint Management Detail Component', () => {
    let comp: ProTaiKhoanCheckPointDetailComponent;
    let fixture: ComponentFixture<ProTaiKhoanCheckPointDetailComponent>;
    const route = ({ data: of({ proTaiKhoanCheckPoint: new ProTaiKhoanCheckPoint(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [ProTaiKhoanCheckPointDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ProTaiKhoanCheckPointDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ProTaiKhoanCheckPointDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.proTaiKhoanCheckPoint).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
