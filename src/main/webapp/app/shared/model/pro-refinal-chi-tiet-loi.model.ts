import { Moment } from 'moment';

export interface IProRefinalChiTietLoi {
  id?: number;
  maLoi?: string;
  thoiGian?: Moment;
  dx?: number;
  dy?: number;
  isFront?: boolean;
  isInside?: boolean;
  warning?: boolean;
  proRefinalId?: number;
  proLoiId?: number;
}

export class ProRefinalChiTietLoi implements IProRefinalChiTietLoi {
  constructor(
    public id?: number,
    public maLoi?: string,
    public thoiGian?: Moment,
    public dx?: number,
    public dy?: number,
    public isFront?: boolean,
    public isInside?: boolean,
    public warning?: boolean,
    public proRefinalId?: number,
    public proLoiId?: number
  ) {
    this.isFront = this.isFront || false;
    this.isInside = this.isInside || false;
    this.warning = this.warning || false;
  }
}
