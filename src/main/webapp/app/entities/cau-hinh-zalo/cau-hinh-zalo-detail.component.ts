import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICauHinhZalo } from 'app/shared/model/cau-hinh-zalo.model';

@Component({
  selector: 'jhi-cau-hinh-zalo-detail',
  templateUrl: './cau-hinh-zalo-detail.component.html'
})
export class CauHinhZaloDetailComponent implements OnInit {
  cauHinhZalo: ICauHinhZalo;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ cauHinhZalo }) => {
      this.cauHinhZalo = cauHinhZalo;
    });
  }

  previousState() {
    window.history.back();
  }
}
