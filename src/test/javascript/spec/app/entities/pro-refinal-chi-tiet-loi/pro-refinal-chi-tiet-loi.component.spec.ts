import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { CtTestModule } from '../../../test.module';
import { ProRefinalChiTietLoiComponent } from 'app/entities/pro-refinal-chi-tiet-loi/pro-refinal-chi-tiet-loi.component';
import { ProRefinalChiTietLoiService } from 'app/entities/pro-refinal-chi-tiet-loi/pro-refinal-chi-tiet-loi.service';
import { ProRefinalChiTietLoi } from 'app/shared/model/pro-refinal-chi-tiet-loi.model';

describe('Component Tests', () => {
  describe('ProRefinalChiTietLoi Management Component', () => {
    let comp: ProRefinalChiTietLoiComponent;
    let fixture: ComponentFixture<ProRefinalChiTietLoiComponent>;
    let service: ProRefinalChiTietLoiService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [ProRefinalChiTietLoiComponent],
        providers: []
      })
        .overrideTemplate(ProRefinalChiTietLoiComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ProRefinalChiTietLoiComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProRefinalChiTietLoiService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ProRefinalChiTietLoi(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.proRefinalChiTietLois[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
