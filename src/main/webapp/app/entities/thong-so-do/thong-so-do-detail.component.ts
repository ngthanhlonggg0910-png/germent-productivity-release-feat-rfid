import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IThongSoDo } from 'app/shared/model/thong-so-do.model';

@Component({
  selector: 'jhi-thong-so-do-detail',
  templateUrl: './thong-so-do-detail.component.html'
})
export class ThongSoDoDetailComponent implements OnInit {
  thongSoDo: IThongSoDo;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ thongSoDo }) => {
      this.thongSoDo = thongSoDo;
    });
  }

  previousState() {
    window.history.back();
  }
}
