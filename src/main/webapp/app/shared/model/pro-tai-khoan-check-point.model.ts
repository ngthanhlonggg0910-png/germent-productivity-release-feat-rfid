export interface IProTaiKhoanCheckPoint {
  id?: number;
  taiKhoan?: string;
  checked?: boolean;
  proCheckPointTen?: string;
  proCheckPointId?: number;
}

export class ProTaiKhoanCheckPoint implements IProTaiKhoanCheckPoint {
  constructor(
    public id?: number,
    public taiKhoan?: string,
    public checked?: boolean,
    public proCheckPointTen?: string,
    public proCheckPointId?: number
  ) {
    this.checked = this.checked || false;
  }
}
