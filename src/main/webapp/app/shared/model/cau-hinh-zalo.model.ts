import { Moment } from 'moment';

export interface ICauHinhZalo {
  id?: number;
  accessToken?: string;
  refreshToken?: string;
  zaloOA?: string;
  secretKey?: string;
  appId?: number;
  expireTime?: Moment;
}

export class CauHinhZalo implements ICauHinhZalo {
  constructor(
    public id?: number,
    public accessToken?: string,
    public refreshToken?: string,
    public zaloOA?: string,
    public secretKey?: string,
    public appId?: number,
    public expireTime?: Moment
  ) {}
}
