import { Moment } from 'moment';

export interface ITracking {
  id?: number;
  nvButtonId?: number;
  state1Time?: Moment;
  state2Time?: Moment;
  state3Time?: Moment;
  state4Time?: Moment;
  state5Time?: Moment;
  state6Time?: Moment;
  trackingTypeName?: string;
  trackingTypeId?: number;
}

export class Tracking implements ITracking {
  constructor(
    public id?: number,
    public nvButtonId?: number,
    public state1Time?: Moment,
    public state2Time?: Moment,
    public state3Time?: Moment,
    public state4Time?: Moment,
    public state5Time?: Moment,
    public state6Time?: Moment,
    public trackingTypeName?: string,
    public trackingTypeId?: number
  ) {}
}
