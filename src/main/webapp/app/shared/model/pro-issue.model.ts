import { Moment } from 'moment';

export interface IProIssue {
  id?: number;
  maHang?: string;
  pono?: string;
  tenChuyen?: string;
  ngayGioBatDau?: Moment;
  ngayGioKetThuc?: Moment;
  maLoi?: string;
  moTaLoi?: string;
  maMay?: string;
  soTram?: string;
  proChuyenId?: number;
  proSanPhamId?: number;
  nguoiTao?: string;
  checkPointType?: string;
  checkPointId?: number;
  checkPointName?: string;
}

export class ProIssue implements IProIssue {
  constructor(
    public id?: number,
    public maHang?: string,
    public pono?: string,
    public tenChuyen?: string,
    public ngayGioBatDau?: Moment,
    public ngayGioKetThuc?: Moment,
    public maLoi?: string,
    public moTaLoi?: string,
    public maMay?: string,
    public soTram?: string,
    public proChuyenId?: number,
    public proSanPhamId?: number,
    public nguoiTao?: string,
    public checkPointType?: string,
    public checkPointId?: number,
    public checkPointName?: string
  ) {}
}
