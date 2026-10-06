import { Moment } from 'moment';

export interface IGopYChatLuong {
  id?: number;
  cusId?: number;
  cusName?: string;
  chuyenId?: number;
  chuyenName?: string;
  maHang?: string;
  po?: string;
  poId?: number;
  color?: string;
  size?: string;
  firstCut?: boolean;
  inline?: boolean;
  lanBanHanh?: number;
  ngayBaoCao?: Moment;
  nguoiTao?: string;
  userId?: Moment;
  ngayTao?: Moment;
  tenQuanLyXuong?: string;
  tenKiThuat?: string;
  tenQanLyCat?: string;
  tenQA?: string;
}

export class GopYChatLuong implements IGopYChatLuong {
  constructor(
    public id?: number,
    public cusId?: number,
    public cusName?: string,
    public chuyenId?: number,
    public chuyenName?: string,
    public maHang?: string,
    public po?: string,
    public poId?: number,
    public color?: string,
    public size?: string,
    public firstCut?: boolean,
    public inline?: boolean,
    public lanBanHanh?: number,
    public ngayBaoCao?: Moment,
    public nguoiTao?: string,
    public userId?: Moment,
    public ngayTao?: Moment,
    public tenQuanLyXuong?: string,
    public tenKiThuat?: string,
    public tenQanLyCat?: string,
    public tenQA?: string
  ) {
    this.firstCut = this.firstCut || false;
    this.inline = this.inline || false;
  }
}
