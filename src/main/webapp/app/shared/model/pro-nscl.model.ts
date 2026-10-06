import { Moment } from 'moment';

export interface IProNSCL {
  id?: number;
  taiKhoan?: string;
  ngaySanXuat?: Moment;
  stt?: number;
  mucTieuNgay?: number;
  thucHienNgay?: number;
  loiNgay?: number;
  suaNgay?: number;
  btpCap?: number;
  qty1h?: number;
  qty2h?: number;
  qty3h?: number;
  qty4h?: number;
  qty5h?: number;
  qty6h?: number;
  qty7h?: number;
  qty8h?: number;
  qty9h?: number;
  qty10h?: number;
  qty11h?: number;
  qty12h?: number;
  qty13h?: number;
  qty14h?: number;
  reject1h?: number;
  reject2h?: number;
  reject3h?: number;
  reject4h?: number;
  reject5h?: number;
  reject6h?: number;
  reject7h?: number;
  reject8h?: number;
  reject9h?: number;
  reject10h?: number;
  reject11h?: number;
  reject12h?: number;
  reject13h?: number;
  reject14h?: number;
  man1h?: number;
  man2h?: number;
  man3h?: number;
  man4h?: number;
  man5h?: number;
  man6h?: number;
  man7h?: number;
  man8h?: number;
  man9h?: number;
  man10h?: number;
  man11h?: number;
  man12h?: number;
  man13h?: number;
  man14h?: number;
  manOnly7Hours?: number;
  fix1h?: number;
  fix2h?: number;
  fix3h?: number;
  fix4h?: number;
  fix5h?: number;
  fix6h?: number;
  fix7h?: number;
  fix8h?: number;
  fix9h?: number;
  fix10h?: number;
  fix11h?: number;
  fix12h?: number;
  fix13h?: number;
  fix14h?: number;
  proKeHoachSanXuatId?: number;
  proCheckPointTen?: string;
  proCheckPointId?: number;
}

export class ProNSCL implements IProNSCL {
  constructor(
    public id?: number,
    public taiKhoan?: string,
    public ngaySanXuat?: Moment,
    public stt?: number,
    public mucTieuNgay?: number,
    public thucHienNgay?: number,
    public loiNgay?: number,
    public suaNgay?: number,
    public btpCap?: number,
    public qty1h?: number,
    public qty2h?: number,
    public qty3h?: number,
    public qty4h?: number,
    public qty5h?: number,
    public qty6h?: number,
    public qty7h?: number,
    public qty8h?: number,
    public qty9h?: number,
    public qty10h?: number,
    public qty11h?: number,
    public qty12h?: number,
    public qty13h?: number,
    public qty14h?: number,
    public reject1h?: number,
    public reject2h?: number,
    public reject3h?: number,
    public reject4h?: number,
    public reject5h?: number,
    public reject6h?: number,
    public reject7h?: number,
    public reject8h?: number,
    public reject9h?: number,
    public reject10h?: number,
    public reject11h?: number,
    public reject12h?: number,
    public reject13h?: number,
    public reject14h?: number,
    public man1h?: number,
    public man2h?: number,
    public man3h?: number,
    public man4h?: number,
    public man5h?: number,
    public man6h?: number,
    public man7h?: number,
    public man8h?: number,
    public man9h?: number,
    public man10h?: number,
    public man11h?: number,
    public man12h?: number,
    public man13h?: number,
    public man14h?: number,
    public manOnly7Hours?: number,
    public fix1h?: number,
    public fix2h?: number,
    public fix3h?: number,
    public fix4h?: number,
    public fix5h?: number,
    public fix6h?: number,
    public fix7h?: number,
    public fix8h?: number,
    public fix9h?: number,
    public fix10h?: number,
    public fix11h?: number,
    public fix12h?: number,
    public fix13h?: number,
    public fix14h?: number,
    public proKeHoachSanXuatId?: number,
    public proCheckPointTen?: string,
    public proCheckPointId?: number
  ) {}
}
