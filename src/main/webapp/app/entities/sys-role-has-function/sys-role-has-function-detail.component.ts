import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISysRoleHasFunction } from 'app/shared/model/sys-role-has-function.model';

@Component({
  selector: 'jhi-sys-role-has-function-detail',
  templateUrl: './sys-role-has-function-detail.component.html'
})
export class SysRoleHasFunctionDetailComponent implements OnInit {
  sysRoleHasFunction: ISysRoleHasFunction;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ sysRoleHasFunction }) => {
      this.sysRoleHasFunction = sysRoleHasFunction;
    });
  }

  previousState() {
    window.history.back();
  }
}
