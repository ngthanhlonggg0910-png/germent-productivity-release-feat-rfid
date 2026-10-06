import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDungSai } from 'app/shared/model/dung-sai.model';

@Component({
  selector: 'jhi-dung-sai-detail',
  templateUrl: './dung-sai-detail.component.html'
})
export class DungSaiDetailComponent implements OnInit {
  dungSai: IDungSai;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ dungSai }) => {
      this.dungSai = dungSai;
    });
  }

  previousState() {
    window.history.back();
  }
}
