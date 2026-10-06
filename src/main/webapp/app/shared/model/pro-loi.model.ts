export interface IProLoi {
  id?: number;
  maLoi?: string;
  cusCode?: string;
  tenLoi?: string;
  tenTiengAnh?: string;
  label?: string;
  stt?: number;
  mauSac?: string;
  qc?: boolean;
  qa?: boolean;
  checked?: boolean;
  ghiChu?: string;
  proCumLoiTen?: string;
  proCumLoiId?: number;
}

export class ProLoi implements IProLoi {
  constructor(
    public id?: number,
    public maLoi?: string,
    public cusCode?: string,
    public tenLoi?: string,
    public tenTiengAnh?: string,
    public label?: string,
    public stt?: number,
    public mauSac?: string,
    public qc?: boolean,
    public qa?: boolean,
    public checked?: boolean,
    public ghiChu?: string,
    public proCumLoiTen?: string,
    public proCumLoiId?: number
  ) {
    this.qc = this.qc || false;
    this.qa = this.qa || false;
    this.checked = this.checked || false;
  }
}
