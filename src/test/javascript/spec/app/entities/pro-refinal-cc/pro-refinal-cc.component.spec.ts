import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { CtTestModule } from '../../../test.module';
import { ProRefinalCCComponent } from 'app/entities/pro-refinal-cc/pro-refinal-cc.component';
import { ProRefinalCCService } from 'app/entities/pro-refinal-cc/pro-refinal-cc.service';
import { ProRefinalCC } from 'app/shared/model/pro-refinal-cc.model';

describe('Component Tests', () => {
  describe('ProRefinalCC Management Component', () => {
    let comp: ProRefinalCCComponent;
    let fixture: ComponentFixture<ProRefinalCCComponent>;
    let service: ProRefinalCCService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [ProRefinalCCComponent],
        providers: []
      })
        .overrideTemplate(ProRefinalCCComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ProRefinalCCComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProRefinalCCService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ProRefinalCC(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.proRefinalCCS[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
