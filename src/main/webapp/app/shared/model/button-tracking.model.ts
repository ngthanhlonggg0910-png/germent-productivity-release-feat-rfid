import { Moment } from 'moment';

export interface IButtonTracking {
  id?: number;
  updateDate?: Moment;
  currentAliasState?: number;
  trackingId?: number;
  nVButtonId?: number;
}

export class ButtonTracking implements IButtonTracking {
  constructor(
    public id?: number,
    public updateDate?: Moment,
    public currentAliasState?: number,
    public trackingId?: number,
    public nVButtonId?: number
  ) {}
}
