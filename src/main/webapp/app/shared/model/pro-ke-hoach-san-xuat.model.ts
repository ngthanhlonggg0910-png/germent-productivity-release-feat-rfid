import { Moment } from 'moment';

export interface IProKeHoachSanXuat {
  id?: number;
  ngaySanXuat?: Moment;
  soLuongYeuCau?: number;
  tinhTrang?: number;
  ghiChu?: string;
  sam?: number;
  lotNo?: number;
  proChuyenTen?: string;
  proChuyenId?: number;
  proSanPhamMaSanPham?: string;
  proSanPhamId?: number;
}

export class ProKeHoachSanXuat implements IProKeHoachSanXuat {
  constructor(
    public id?: number,
    public ngaySanXuat?: Moment,
    public soLuongYeuCau?: number,
    public tinhTrang?: number,
    public ghiChu?: string,
    public sam?: number,
    public lotNo?: number,
    public proChuyenTen?: string,
    public proChuyenId?: number,
    public proSanPhamMaSanPham?: string,
    public proSanPhamId?: number
  ) {}
}
