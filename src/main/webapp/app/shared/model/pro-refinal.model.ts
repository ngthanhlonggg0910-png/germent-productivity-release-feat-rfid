import { Moment } from 'moment';

export interface IProRefinal {
  id?: number;
  proKHSXId?: number;
  checkPointID?: number;
  proNSCLId?: number;
  ngayGioKiem?: Moment;
  soLuongKiem?: number;
  hangDatHienTai?: number;
  doAm?: number;
}

export class ProRefinal implements IProRefinal {
  constructor(
    public id?: number,
    public proKHSXId?: number,
    public checkPointID?: number,
    public proNSCLId?: number,
    public ngayGioKiem?: Moment,
    public soLuongKiem?: number,
    public hangDatHienTai?: number,
    public doAm?: number
  ) {}
}
