export interface IKhacPhucLoi {
  id?: number;
  maKP?: string;
  tenKP?: string;
  nguoiTao?: string;
  ngayTao?: string;
  userId?: number;
}

export class KhacPhucLoi implements IKhacPhucLoi {
  constructor(
    public id?: number,
    public maKP?: string,
    public tenKP?: string,
    public nguoiTao?: string,
    public ngayTao?: string,
    public userId?: number
  ) {}
}
