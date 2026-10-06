import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { CtTestModule } from '../../../test.module';
import { LibSoLuongPoKiemComponent } from 'app/entities/lib-so-luong-po-kiem/lib-so-luong-po-kiem.component';
import { LibSoLuongPoKiemService } from 'app/entities/lib-so-luong-po-kiem/lib-so-luong-po-kiem.service';
import { LibSoLuongPoKiem } from 'app/shared/model/lib-so-luong-po-kiem.model';

describe('Component Tests', () => {
  describe('LibSoLuongPoKiem Management Component', () => {
    let comp: LibSoLuongPoKiemComponent;
    let fixture: ComponentFixture<LibSoLuongPoKiemComponent>;
    let service: LibSoLuongPoKiemService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [LibSoLuongPoKiemComponent],
        providers: []
      })
        .overrideTemplate(LibSoLuongPoKiemComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(LibSoLuongPoKiemComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(LibSoLuongPoKiemService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new LibSoLuongPoKiem(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.libSoLuongPoKiems[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
