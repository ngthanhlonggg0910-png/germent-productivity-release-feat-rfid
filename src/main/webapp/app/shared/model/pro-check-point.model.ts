export interface IProCheckPoint {
  id?: number;
  ten?: string;
  proChuyenTen?: string;
  proChuyenId?: number;
  proCongDoanTen?: string;
  proCongDoanId?: number;
}

export class ProCheckPoint implements IProCheckPoint {
  constructor(
    public id?: number,
    public ten?: string,
    public proChuyenTen?: string,
    public proChuyenId?: number,
    public proCongDoanTen?: string,
    public proCongDoanId?: number
  ) {}
}
