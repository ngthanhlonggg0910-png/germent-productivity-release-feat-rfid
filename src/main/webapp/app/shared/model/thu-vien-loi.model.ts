import { Moment } from 'moment';

export interface IThuVienLoi {
  id?: number;
  maLoi?: string;
  tenLoi?: string;
  nguoiTao?: string;
  ngayTao?: Moment;
  userId?: number;
}

export class ThuVienLoi implements IThuVienLoi {
  constructor(
    public id?: number,
    public maLoi?: string,
    public tenLoi?: string,
    public nguoiTao?: string,
    public ngayTao?: Moment,
    public userId?: number
  ) {}
}
