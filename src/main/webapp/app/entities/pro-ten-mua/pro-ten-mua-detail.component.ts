import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IProTenMua } from 'app/shared/model/pro-ten-mua.model';

@Component({
  selector: 'jhi-pro-ten-mua-detail',
  templateUrl: './pro-ten-mua-detail.component.html'
})
export class ProTenMuaDetailComponent implements OnInit {
  proTenMua: IProTenMua;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ proTenMua }) => {
      this.proTenMua = proTenMua;
    });
  }

  previousState() {
    window.history.back();
  }
}
