import { Moment } from 'moment';

export interface IProRefinalCC {
  id?: number;
  khxhId?: number;
  maHang?: string;
  poNo?: string;
  ngayKiemFinal?: Moment;
  tenChuyen?: string;
  ngayGioKiem?: Moment;
  soLuongKiemAql?: number;
  soLuong?: number;
  doAm?: number;
  tongSoLoi?: number;
  soThuTuThung?: string;
  doKimMd?: string;
  aql?: string;
  maxLoiChoPhep?: number;
  completed?: boolean;
  nguoiKiem?: string;
}

export class ProRefinalCC implements IProRefinalCC {
  constructor(
    public id?: number,
    public khxhId?: number,
    public maHang?: string,
    public poNo?: string,
    public ngayKiemFinal?: Moment,
    public tenChuyen?: string,
    public ngayGioKiem?: Moment,
    public soLuongKiemAql?: number,
    public soLuong?: number,
    public doAm?: number,
    public tongSoLoi?: number,
    public soThuTuThung?: string,
    public doKimMd?: string,
    public aql?: string,
    public maxLoiChoPhep?: number,
    public completed?: boolean,
    public nguoiKiem?: string
  ) {
    this.completed = this.completed || false;
  }
}
