import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDoKimMd } from 'app/shared/model/do-kim-md.model';

@Component({
  selector: 'jhi-do-kim-md-detail',
  templateUrl: './do-kim-md-detail.component.html'
})
export class DoKimMdDetailComponent implements OnInit {
  doKimMd: IDoKimMd;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ doKimMd }) => {
      this.doKimMd = doKimMd;
    });
  }

  previousState() {
    window.history.back();
  }
}
