export interface IProChungLoaiSanPham {
  id?: number;
  tenChungLoai?: string;
}

export class ProChungLoaiSanPham implements IProChungLoaiSanPham {
  constructor(public id?: number, public tenChungLoai?: string) {}
}
