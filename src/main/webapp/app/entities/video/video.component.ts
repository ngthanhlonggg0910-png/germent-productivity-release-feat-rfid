import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { IVideo } from 'app/shared/model/video.model';
import { VideoService } from './video.service';

@Component({
  selector: 'jhi-video',
  templateUrl: './video.component.html'
})
export class VideoComponent implements OnInit, OnDestroy {
  videos: IVideo[];
  eventSubscriber: Subscription;

  constructor(protected videoService: VideoService, protected eventManager: JhiEventManager) {}

  loadAll() {
    this.videoService.query().subscribe((res: HttpResponse<IVideo[]>) => {
      this.videos = res.body;
    });
  }

  ngOnInit() {
    this.loadAll();
    this.registerChangeInVideos();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IVideo) {
    return item.id;
  }

  registerChangeInVideos() {
    this.eventSubscriber = this.eventManager.subscribe('videoListModification', () => this.loadAll());
  }
}
