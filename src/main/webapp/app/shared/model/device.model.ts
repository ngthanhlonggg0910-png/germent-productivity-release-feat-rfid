import { Moment } from 'moment';
import { LWT } from 'app/shared/model/enumerations/lwt.model';

export interface IDevice {
  id?: number;
  name?: string;
  devIds?: string;
  stateTopic?: string;
  lwtTopic?: string;
  sw?: string;
  module?: string;
  restartReason?: string;
  time?: Moment;
  uptime?: string;
  hostName?: string;
  ipAddress?: string;
  lwt?: LWT;
}

export class Device implements IDevice {
  constructor(
    public id?: number,
    public name?: string,
    public devIds?: string,
    public stateTopic?: string,
    public lwtTopic?: string,
    public sw?: string,
    public module?: string,
    public restartReason?: string,
    public time?: Moment,
    public uptime?: string,
    public hostName?: string,
    public ipAddress?: string,
    public lwt?: LWT
  ) {}
}
