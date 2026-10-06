import { Moment } from 'moment';

export interface IProRefinalCCChiTietLoi {
  id?: number;
  maLoi?: string;
  thoiGian?: Moment;
  dx?: number;
  dy?: number;
  isFront?: boolean;
  isInside?: boolean;
  warning?: boolean;
  nguoiKiem?: string;
  proRefinalCCId?: number;
  proLoiId?: number;
}

export class ProRefinalCCChiTietLoi implements IProRefinalCCChiTietLoi {
  constructor(
    public id?: number,
    public maLoi?: string,
    public thoiGian?: Moment,
    public dx?: number,
    public dy?: number,
    public isFront?: boolean,
    public isInside?: boolean,
    public warning?: boolean,
    public nguoiKiem?: string,
    public proRefinalCCId?: number,
    public proLoiId?: number
  ) {
    this.isFront = this.isFront || false;
    this.isInside = this.isInside || false;
    this.warning = this.warning || false;
  }
}
