export interface IProAql {
  id?: number;
  aql?: string;
  soLuongDonHangTu?: number;
  soLuongDonHangDen?: number;
  soLuongKiem?: number;
  soLuongSeFail?: number;
  soLuongSePass?: number;
}

export class ProAql implements IProAql {
  constructor(
    public id?: number,
    public aql?: string,
    public soLuongDonHangTu?: number,
    public soLuongDonHangDen?: number,
    public soLuongKiem?: number,
    public soLuongSeFail?: number,
    public soLuongSePass?: number
  ) {}
}
