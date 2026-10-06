import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { CtTestModule } from '../../../test.module';
import { ProRefinalCCChiTietLoiComponent } from 'app/entities/pro-refinal-cc-chi-tiet-loi/pro-refinal-cc-chi-tiet-loi.component';
import { ProRefinalCCChiTietLoiService } from 'app/entities/pro-refinal-cc-chi-tiet-loi/pro-refinal-cc-chi-tiet-loi.service';
import { ProRefinalCCChiTietLoi } from 'app/shared/model/pro-refinal-cc-chi-tiet-loi.model';

describe('Component Tests', () => {
  describe('ProRefinalCCChiTietLoi Management Component', () => {
    let comp: ProRefinalCCChiTietLoiComponent;
    let fixture: ComponentFixture<ProRefinalCCChiTietLoiComponent>;
    let service: ProRefinalCCChiTietLoiService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [ProRefinalCCChiTietLoiComponent],
        providers: []
      })
        .overrideTemplate(ProRefinalCCChiTietLoiComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ProRefinalCCChiTietLoiComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProRefinalCCChiTietLoiService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ProRefinalCCChiTietLoi(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.proRefinalCCChiTietLois[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
