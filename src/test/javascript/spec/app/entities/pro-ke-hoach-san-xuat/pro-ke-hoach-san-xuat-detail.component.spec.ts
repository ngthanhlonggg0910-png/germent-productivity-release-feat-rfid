import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CtTestModule } from '../../../test.module';
import { ProKeHoachSanXuatDetailComponent } from 'app/entities/pro-ke-hoach-san-xuat/pro-ke-hoach-san-xuat-detail.component';
import { ProKeHoachSanXuat } from 'app/shared/model/pro-ke-hoach-san-xuat.model';

describe('Component Tests', () => {
  describe('ProKeHoachSanXuat Management Detail Component', () => {
    let comp: ProKeHoachSanXuatDetailComponent;
    let fixture: ComponentFixture<ProKeHoachSanXuatDetailComponent>;
    const route = ({ data: of({ proKeHoachSanXuat: new ProKeHoachSanXuat(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [ProKeHoachSanXuatDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ProKeHoachSanXuatDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ProKeHoachSanXuatDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.proKeHoachSanXuat).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
