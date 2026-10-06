import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CtTestModule } from '../../../test.module';
import { GopYChatLuongDetailComponent } from 'app/entities/gop-y-chat-luong/gop-y-chat-luong-detail.component';
import { GopYChatLuong } from 'app/shared/model/gop-y-chat-luong.model';

describe('Component Tests', () => {
  describe('GopYChatLuong Management Detail Component', () => {
    let comp: GopYChatLuongDetailComponent;
    let fixture: ComponentFixture<GopYChatLuongDetailComponent>;
    const route = ({ data: of({ gopYChatLuong: new GopYChatLuong(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [GopYChatLuongDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(GopYChatLuongDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(GopYChatLuongDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.gopYChatLuong).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
