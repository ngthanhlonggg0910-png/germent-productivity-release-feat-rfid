export interface IProCongDoan {
  id?: number;
  ten?: string;
  nhomCongDoan?: string;
  proNhomCongDoanTen?: string;
  proNhomCongDoanId?: number;
}

export class ProCongDoan implements IProCongDoan {
  constructor(
    public id?: number,
    public ten?: string,
    public nhomCongDoan?: string,
    public proNhomCongDoanTen?: string,
    public proNhomCongDoanId?: number
  ) {}
}
