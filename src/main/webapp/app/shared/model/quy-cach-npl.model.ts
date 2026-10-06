export interface IQuyCachNPL {
  id?: number;
  tenNPL?: string;
  maNPL?: string;
  nguoiTao?: string;
  ngayTao?: string;
  userId?: number;
}

export class QuyCachNPL implements IQuyCachNPL {
  constructor(
    public id?: number,
    public tenNPL?: string,
    public maNPL?: string,
    public nguoiTao?: string,
    public ngayTao?: string,
    public userId?: number
  ) {}
}
