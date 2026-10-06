export interface IProKhachHang {
  id?: number;
  ten?: string;
  nhom?: string;
  diaChi?: string;
}

export class ProKhachHang implements IProKhachHang {
  constructor(public id?: number, public ten?: string, public nhom?: string, public diaChi?: string) {}
}
