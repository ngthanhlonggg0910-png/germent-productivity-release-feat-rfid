import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'line-1',
        loadChildren: () => import('./line1/line1.module').then(m => m.CtLine1Module)
      }
    ])
  ]
})
export class CtReportsModule {}
