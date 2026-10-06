import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CtTestModule } from '../../../test.module';
import { GroupTopicDetailComponent } from 'app/entities/group-topic/group-topic-detail.component';
import { GroupTopic } from 'app/shared/model/group-topic.model';

describe('Component Tests', () => {
  describe('GroupTopic Management Detail Component', () => {
    let comp: GroupTopicDetailComponent;
    let fixture: ComponentFixture<GroupTopicDetailComponent>;
    const route = ({ data: of({ groupTopic: new GroupTopic(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CtTestModule],
        declarations: [GroupTopicDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(GroupTopicDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(GroupTopicDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.groupTopic).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
