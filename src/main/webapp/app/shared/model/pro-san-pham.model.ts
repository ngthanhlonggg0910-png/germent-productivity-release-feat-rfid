export interface IProSanPham {
  id?: number;
  maSanPham?: string;
  maDonHang?: string;
  po?: string;
  soLuongSanXuat?: number;
  thongSoValue1?: string;
  thongSoCaption1?: string;
  thongSoCheck1?: boolean;
  thongSoValue2?: string;
  thongSoCaption2?: string;
  thongSoCheck2?: boolean;
  thongSoValue3?: string;
  thongSoCaption3?: string;
  thongSoCheck3?: boolean;
  thongSoValue4?: string;
  thongSoCaption4?: string;
  thongSoCheck4?: boolean;
  thongSoValue5?: string;
  thongSoCaption5?: string;
  thongSoCheck5?: boolean;
  thongSoValue6?: string;
  thongSoCaption6?: string;
  thongSoCheck6?: boolean;
  thongSoValue7?: string;
  thongSoCaption7?: string;
  thongSoCheck7?: boolean;
  thongSoValue8?: string;
  thongSoCaption8?: string;
  thongSoCheck8?: boolean;
  thongSoValue9?: string;
  thongSoCaption9?: string;
  thongSoCheck9?: boolean;
  thongSoValue10?: string;
  thongSoCaption10?: string;
  thongSoCheck10?: boolean;
  color?: string;
  hinhMatTruocURL?: string;
  hinhMatSauURL?: string;
  proKhachHangTen?: string;
  proKhachHangId?: number;
  proChungLoaiSanPhamTenChungLoai?: string;
  proChungLoaiSanPhamId?: number;
  proTenMuaTenMua?: string;
  proTenMuaId?: number;
}

export class ProSanPham implements IProSanPham {
  constructor(
    public id?: number,
    public maSanPham?: string,
    public maDonHang?: string,
    public po?: string,
    public soLuongSanXuat?: number,
    public thongSoValue1?: string,
    public thongSoCaption1?: string,
    public thongSoCheck1?: boolean,
    public thongSoValue2?: string,
    public thongSoCaption2?: string,
    public thongSoCheck2?: boolean,
    public thongSoValue3?: string,
    public thongSoCaption3?: string,
    public thongSoCheck3?: boolean,
    public thongSoValue4?: string,
    public thongSoCaption4?: string,
    public thongSoCheck4?: boolean,
    public thongSoValue5?: string,
    public thongSoCaption5?: string,
    public thongSoCheck5?: boolean,
    public thongSoValue6?: string,
    public thongSoCaption6?: string,
    public thongSoCheck6?: boolean,
    public thongSoValue7?: string,
    public thongSoCaption7?: string,
    public thongSoCheck7?: boolean,
    public thongSoValue8?: string,
    public thongSoCaption8?: string,
    public thongSoCheck8?: boolean,
    public thongSoValue9?: string,
    public thongSoCaption9?: string,
    public thongSoCheck9?: boolean,
    public thongSoValue10?: string,
    public thongSoCaption10?: string,
    public thongSoCheck10?: boolean,
    public color?: string,
    public hinhMatTruocURL?: string,
    public hinhMatSauURL?: string,
    public proKhachHangTen?: string,
    public proKhachHangId?: number,
    public proChungLoaiSanPhamTenChungLoai?: string,
    public proChungLoaiSanPhamId?: number,
    public proTenMuaTenMua?: string,
    public proTenMuaId?: number
  ) {
    this.thongSoCheck1 = this.thongSoCheck1 || false;
    this.thongSoCheck2 = this.thongSoCheck2 || false;
    this.thongSoCheck3 = this.thongSoCheck3 || false;
    this.thongSoCheck4 = this.thongSoCheck4 || false;
    this.thongSoCheck5 = this.thongSoCheck5 || false;
    this.thongSoCheck6 = this.thongSoCheck6 || false;
    this.thongSoCheck7 = this.thongSoCheck7 || false;
    this.thongSoCheck8 = this.thongSoCheck8 || false;
    this.thongSoCheck9 = this.thongSoCheck9 || false;
    this.thongSoCheck10 = this.thongSoCheck10 || false;
  }
}
