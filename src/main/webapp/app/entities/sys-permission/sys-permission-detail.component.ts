import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISysPermission } from 'app/shared/model/sys-permission.model';

@Component({
  selector: 'jhi-sys-permission-detail',
  templateUrl: './sys-permission-detail.component.html'
})
export class SysPermissionDetailComponent implements OnInit {
  sysPermission: ISysPermission;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ sysPermission }) => {
      this.sysPermission = sysPermission;
    });
  }

  previousState() {
    window.history.back();
  }
}
