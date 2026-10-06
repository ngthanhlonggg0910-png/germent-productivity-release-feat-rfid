import { Moment } from 'moment';

export interface IGYCLQuyCachNPL {
  id?: number;
  tenNPL?: string;
  nguoiTao?: string;
  ngayTao?: Moment;
  userId?: number;
  yesNo?: boolean;
  nA?: string;
  proGopYChatLuongId?: number;
}

export class GYCLQuyCachNPL implements IGYCLQuyCachNPL {
  constructor(
    public id?: number,
    public tenNPL?: string,
    public nguoiTao?: string,
    public ngayTao?: Moment,
    public userId?: number,
    public yesNo?: boolean,
    public nA?: string,
    public proGopYChatLuongId?: number
  ) {
    this.yesNo = this.yesNo || false;
  }
}
