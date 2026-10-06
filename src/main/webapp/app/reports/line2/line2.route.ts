import { Route } from '@angular/router';
import { Line2Component } from './line2.component';

export const LINE1_ROUTE: Route = {
  path: '',
  component: Line2Component,
  data: {
    authorities: ['ROLE_USER'],
    pageTitle: 'line2.title'
  }
};
