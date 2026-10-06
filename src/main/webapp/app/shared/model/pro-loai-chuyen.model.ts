export interface IProLoaiChuyen {
  id?: number;
  ten?: string;
}

export class ProLoaiChuyen implements IProLoaiChuyen {
  constructor(public id?: number, public ten?: string) {}
}
