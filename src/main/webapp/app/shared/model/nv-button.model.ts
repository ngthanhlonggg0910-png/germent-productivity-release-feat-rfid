import { Moment } from 'moment';
import { NVButtonState } from 'app/shared/model/enumerations/nv-button-state.model';
import { LWT } from 'app/shared/model/enumerations/lwt.model';
import { GroupButtonType } from 'app/shared/model/enumerations/group-button-type.model';

export interface INVButton {
  id?: number;
  name?: string;
  discoveredOnTopic?: string;
  cmndTopic?: string;
  plOff?: string;
  plOn?: string;
  uniqId?: string;
  devIds?: string;
  teleTopic?: string;
  statTopic?: string;
  avtyTopic?: string;
  plAvail?: string;
  plNotAvail?: string;
  state?: NVButtonState;
  lwt?: LWT;
  lastUpdateState?: Moment;
  groupType?: GroupButtonType;
  buttonTrackingId?: number;
  deviceName?: string;
  deviceId?: number;
  trackingTypeName?: string;
  trackingTypeId?: number;
}

export class NVButton implements INVButton {
  constructor(
    public id?: number,
    public name?: string,
    public discoveredOnTopic?: string,
    public cmndTopic?: string,
    public plOff?: string,
    public plOn?: string,
    public uniqId?: string,
    public devIds?: string,
    public teleTopic?: string,
    public statTopic?: string,
    public avtyTopic?: string,
    public plAvail?: string,
    public plNotAvail?: string,
    public state?: NVButtonState,
    public lwt?: LWT,
    public lastUpdateState?: Moment,
    public groupType?: GroupButtonType,
    public buttonTrackingId?: number,
    public deviceName?: string,
    public deviceId?: number,
    public trackingTypeName?: string,
    public trackingTypeId?: number
  ) {}
}
