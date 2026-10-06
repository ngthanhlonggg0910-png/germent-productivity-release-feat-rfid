import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { CtTestModule } from '../../../test.module';
import { ProAqlComponent } from 'app/entities/pro-aql/pro-aql.component';
import { ProAqlService } from 'app/entities/pro-aql/pro-aql.service';
import { ProAql } from 'app/shared/model/pro-aql.model';

describe('Component Tests', () => {
  describe('ProAql Management Component', () => {
    let comp: ProAqlComponent;
    let fixture: ComponentFixture<ProAqlComponent>;
    let service: ProAqlService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [ProAqlComponent],
        providers: []
      })
        .overrideTemplate(ProAqlComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ProAqlComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProAqlService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ProAql(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.proAqls[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
