export interface ISoLuongHangKiem {
  id?: number;
  soLuong?: number;
}

export class SoLuongHangKiem implements ISoLuongHangKiem {
  constructor(public id?: number, public soLuong?: number) {}
}
