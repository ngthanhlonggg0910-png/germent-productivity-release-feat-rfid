import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISysFile } from 'app/shared/model/sys-file.model';

@Component({
  selector: 'jhi-sys-file-detail',
  templateUrl: './sys-file-detail.component.html'
})
export class SysFileDetailComponent implements OnInit {
  sysFile: ISysFile;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ sysFile }) => {
      this.sysFile = sysFile;
    });
  }

  previousState() {
    window.history.back();
  }
}
