import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CtTestModule } from '../../../test.module';
import { LibSoLuongPoKiemDetailComponent } from 'app/entities/lib-so-luong-po-kiem/lib-so-luong-po-kiem-detail.component';
import { LibSoLuongPoKiem } from 'app/shared/model/lib-so-luong-po-kiem.model';

describe('Component Tests', () => {
  describe('LibSoLuongPoKiem Management Detail Component', () => {
    let comp: LibSoLuongPoKiemDetailComponent;
    let fixture: ComponentFixture<LibSoLuongPoKiemDetailComponent>;
    const route = ({ data: of({ libSoLuongPoKiem: new LibSoLuongPoKiem(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [LibSoLuongPoKiemDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(LibSoLuongPoKiemDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(LibSoLuongPoKiemDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.libSoLuongPoKiem).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
