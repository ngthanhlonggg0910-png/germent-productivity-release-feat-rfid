import { IThongSoDoSPSize } from 'app/shared/model/thong-so-do-sp-size.model';

export interface IThongSoDoSP {
  id?: number;
  note?: string;
  thongSoDoSPSizes?: IThongSoDoSPSize[];
  thongSoDoCode?: string;
  thongSoDoId?: number;
  dungSaiInchLabel?: string;
  dungSaiId?: number;
  proSanPhamMaSanPham?: string;
  proSanPhamId?: number;
}

export class ThongSoDoSP implements IThongSoDoSP {
  constructor(
    public id?: number,
    public note?: string,
    public thongSoDoSPSizes?: IThongSoDoSPSize[],
    public thongSoDoCode?: string,
    public thongSoDoId?: number,
    public dungSaiInchLabel?: string,
    public dungSaiId?: number,
    public proSanPhamMaSanPham?: string,
    public proSanPhamId?: number
  ) {}
}
