export interface INVButtonTrackingType {
  id?: number;
  name?: string;
  numberOfState?: number;
}

export class NVButtonTrackingType implements INVButtonTrackingType {
  constructor(public id?: number, public name?: string, public numberOfState?: number) {}
}
