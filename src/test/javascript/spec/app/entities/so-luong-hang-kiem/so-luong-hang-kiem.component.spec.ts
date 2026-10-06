import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { CtTestModule } from '../../../test.module';
import { SoLuongHangKiemComponent } from 'app/entities/so-luong-hang-kiem/so-luong-hang-kiem.component';
import { SoLuongHangKiemService } from 'app/entities/so-luong-hang-kiem/so-luong-hang-kiem.service';
import { SoLuongHangKiem } from 'app/shared/model/so-luong-hang-kiem.model';

describe('Component Tests', () => {
  describe('SoLuongHangKiem Management Component', () => {
    let comp: SoLuongHangKiemComponent;
    let fixture: ComponentFixture<SoLuongHangKiemComponent>;
    let service: SoLuongHangKiemService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [SoLuongHangKiemComponent],
        providers: []
      })
        .overrideTemplate(SoLuongHangKiemComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SoLuongHangKiemComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SoLuongHangKiemService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new SoLuongHangKiem(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.soLuongHangKiems[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
