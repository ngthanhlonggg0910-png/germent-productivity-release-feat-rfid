export interface IProChuyen {
  id?: number;
  ten?: string;
  stt?: number;
  tenLoaiChuyen?: string;
  proLoaiChuyenTen?: string;
  proLoaiChuyenId?: number;
  proNhaMayTen?: string;
  proNhaMayId?: number;
}

export class ProChuyen implements IProChuyen {
  constructor(
    public id?: number,
    public ten?: string,
    public stt?: number,
    public tenLoaiChuyen?: string,
    public proLoaiChuyenTen?: string,
    public proLoaiChuyenId?: number,
    public proNhaMayTen?: string,
    public proNhaMayId?: number
  ) {}
}
