export interface IProNhomCongDoan {
  id?: number;
  ten?: string;
  checkNangSuat?: boolean;
}

export class ProNhomCongDoan implements IProNhomCongDoan {
  constructor(public id?: number, public ten?: string, public checkNangSuat?: boolean) {
    this.checkNangSuat = this.checkNangSuat || false;
  }
}
