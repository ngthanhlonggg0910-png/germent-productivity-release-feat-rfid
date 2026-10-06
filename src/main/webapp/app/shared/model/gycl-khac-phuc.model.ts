import { Moment } from 'moment';

export interface IGYCLKhacPhuc {
  id?: number;
  cumId?: number;
  tenCum?: string;
  gopY?: string;
  ketQua?: string;
  nguoiTao?: string;
  userId?: Moment;
  ngayTao?: Moment;
  proGopYChatLuongId?: number;
  thuVienLoiId?: number;
  khacPhucLoiId?: number;
}

export class GYCLKhacPhuc implements IGYCLKhacPhuc {
  constructor(
    public id?: number,
    public cumId?: number,
    public tenCum?: string,
    public gopY?: string,
    public ketQua?: string,
    public nguoiTao?: string,
    public userId?: Moment,
    public ngayTao?: Moment,
    public proGopYChatLuongId?: number,
    public thuVienLoiId?: number,
    public khacPhucLoiId?: number
  ) {}
}
