import { Moment } from 'moment';
import { GioLam } from 'app/shared/model/enumerations/gio-lam.model';

export interface IProNSCLChiTietLoi {
  id?: number;
  maLoi?: string;
  thoiGian?: Moment;
  gioLam?: GioLam;
  dx?: number;
  dy?: number;
  isFront?: boolean;
  isInside?: boolean;
  proNSCLId?: number;
  proLoiMaLoi?: string;
  proLoiId?: number;
}

export class ProNSCLChiTietLoi implements IProNSCLChiTietLoi {
  constructor(
    public id?: number,
    public maLoi?: string,
    public thoiGian?: Moment,
    public gioLam?: GioLam,
    public dx?: number,
    public dy?: number,
    public isFront?: boolean,
    public isInside?: boolean,
    public proNSCLId?: number,
    public proLoiMaLoi?: string,
    public proLoiId?: number
  ) {
    this.isFront = this.isFront || false;
    this.isInside = this.isInside || false;
  }
}
