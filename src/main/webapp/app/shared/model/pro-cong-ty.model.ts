export interface IProCongTy {
  id?: number;
  ten?: string;
  diaChi?: string;
  mst?: string;
}

export class ProCongTy implements IProCongTy {
  constructor(public id?: number, public ten?: string, public diaChi?: string, public mst?: string) {}
}
