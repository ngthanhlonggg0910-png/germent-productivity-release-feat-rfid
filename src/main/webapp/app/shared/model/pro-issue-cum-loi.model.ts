export interface IProIssueCumLoi {
  id?: number;
  ten?: string;
  mau?: string;
  toTruong?: boolean;
  kyThuat?: boolean;
  qa?: boolean;
}

export class ProIssueCumLoi implements IProIssueCumLoi {
  constructor(
    public id?: number,
    public ten?: string,
    public mau?: string,
    public toTruong?: boolean,
    public kyThuat?: boolean,
    public qa?: boolean
  ) {
    this.toTruong = this.toTruong || false;
    this.kyThuat = this.kyThuat || false;
    this.qa = this.qa || false;
  }
}
