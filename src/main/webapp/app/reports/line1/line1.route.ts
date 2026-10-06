import { Route } from '@angular/router';
import { Line1Component } from './line1.component';

export const LINE1_ROUTE: Route = {
  path: '',
  component: Line1Component,
  data: {
    authorities: ['ROLE_USER'],
    pageTitle: 'line1.title'
  }
};
