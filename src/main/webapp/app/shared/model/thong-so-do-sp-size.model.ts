import { IThongSoThucThi } from 'app/shared/model/thong-so-thuc-thi.model';

export interface IThongSoDoSPSize {
  id?: number;
  size?: string;
  cmThongSoChuan?: string;
  inchThongSoChuan?: string;
  cmThongSoChuanValue?: number;
  inchThongSoChuanValue?: number;
  thongSoThucThis?: IThongSoThucThi[];
  thongSoDoSPId?: number;
}

export class ThongSoDoSPSize implements IThongSoDoSPSize {
  constructor(
    public id?: number,
    public size?: string,
    public cmThongSoChuan?: string,
    public inchThongSoChuan?: string,
    public cmThongSoChuanValue?: number,
    public inchThongSoChuanValue?: number,
    public thongSoThucThis?: IThongSoThucThi[],
    public thongSoDoSPId?: number
  ) {}
}
