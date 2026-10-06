import { Moment } from 'moment';
import { GioLam } from 'app/shared/model/enumerations/gio-lam.model';

export interface IProGioLamViec {
  id?: number;
  gioBatDau?: Moment;
  gioKetThuc?: Moment;
  gioLam?: GioLam;
  thoiGian?: number;
  captionTV?: string;
}

export class ProGioLamViec implements IProGioLamViec {
  constructor(
    public id?: number,
    public gioBatDau?: Moment,
    public gioKetThuc?: Moment,
    public gioLam?: GioLam,
    public thoiGian?: number,
    public captionTV?: string
  ) {}
}
