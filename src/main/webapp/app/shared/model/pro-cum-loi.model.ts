export interface IProCumLoi {
  id?: number;
  ten?: string;
  mau?: string;
}

export class ProCumLoi implements IProCumLoi {
  constructor(public id?: number, public ten?: string, public mau?: string) {}
}
