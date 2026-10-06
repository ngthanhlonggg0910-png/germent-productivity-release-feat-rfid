export interface IThongSoThucThi {
  id?: number;
  spIndex?: string;
  thongSoDoSPSizeId?: number;
  dungSaiId?: number;
  proKeHoachSanXuatId?: number;
}

export class ThongSoThucThi implements IThongSoThucThi {
  constructor(
    public id?: number,
    public spIndex?: string,
    public thongSoDoSPSizeId?: number,
    public dungSaiId?: number,
    public proKeHoachSanXuatId?: number
  ) {}
}
