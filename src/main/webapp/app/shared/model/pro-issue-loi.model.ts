export interface IProIssueLoi {
  id?: number;
  maLoi?: string;
  cusCode?: string;
  tenLoi?: string;
  tenTiengAnh?: string;
  label?: string;
  stt?: number;
  mauSac?: string;
  ghiChu?: string;
  proIssueCumLoiTen?: string;
  proIssueCumLoiId?: number;
}

export class ProIssueLoi implements IProIssueLoi {
  constructor(
    public id?: number,
    public maLoi?: string,
    public cusCode?: string,
    public tenLoi?: string,
    public tenTiengAnh?: string,
    public label?: string,
    public stt?: number,
    public mauSac?: string,
    public ghiChu?: string,
    public proIssueCumLoiTen?: string,
    public proIssueCumLoiId?: number
  ) {}
}
