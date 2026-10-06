import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IProNSCL } from 'app/shared/model/pro-nscl.model';

@Component({
  selector: 'jhi-pro-nscl-detail',
  templateUrl: './pro-nscl-detail.component.html'
})
export class ProNSCLDetailComponent implements OnInit {
  proNSCL: IProNSCL | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ proNSCL }) => (this.proNSCL = proNSCL));
  }

  previousState(): void {
    window.history.back();
  }
}
