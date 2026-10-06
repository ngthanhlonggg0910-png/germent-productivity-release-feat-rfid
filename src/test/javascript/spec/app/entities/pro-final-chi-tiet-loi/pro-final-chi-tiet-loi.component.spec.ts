import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { CtTestModule } from '../../../test.module';
import { ProFinalChiTietLoiComponent } from 'app/entities/pro-final-chi-tiet-loi/pro-final-chi-tiet-loi.component';
import { ProFinalChiTietLoiService } from 'app/entities/pro-final-chi-tiet-loi/pro-final-chi-tiet-loi.service';
import { ProFinalChiTietLoi } from 'app/shared/model/pro-final-chi-tiet-loi.model';

describe('Component Tests', () => {
  describe('ProFinalChiTietLoi Management Component', () => {
    let comp: ProFinalChiTietLoiComponent;
    let fixture: ComponentFixture<ProFinalChiTietLoiComponent>;
    let service: ProFinalChiTietLoiService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [ProFinalChiTietLoiComponent],
        providers: []
      })
        .overrideTemplate(ProFinalChiTietLoiComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ProFinalChiTietLoiComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProFinalChiTietLoiService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ProFinalChiTietLoi(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.proFinalChiTietLois[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
