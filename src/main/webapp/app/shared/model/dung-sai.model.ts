import { IThongSoThucThi } from 'app/shared/model/thong-so-thuc-thi.model';

export interface IDungSai {
  id?: number;
  cmLabel?: string;
  inchLabel?: string;
  cmValue?: number;
  inchValue?: number;
  thongSoThucThis?: IThongSoThucThi[];
}

export class DungSai implements IDungSai {
  constructor(
    public id?: number,
    public cmLabel?: string,
    public inchLabel?: string,
    public cmValue?: number,
    public inchValue?: number,
    public thongSoThucThis?: IThongSoThucThi[]
  ) {}
}
