import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISysGroupModule } from 'app/shared/model/sys-group-module.model';

@Component({
  selector: 'jhi-sys-group-module-detail',
  templateUrl: './sys-group-module-detail.component.html'
})
export class SysGroupModuleDetailComponent implements OnInit {
  sysGroupModule: ISysGroupModule;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ sysGroupModule }) => {
      this.sysGroupModule = sysGroupModule;
    });
  }

  previousState() {
    window.history.back();
  }
}
