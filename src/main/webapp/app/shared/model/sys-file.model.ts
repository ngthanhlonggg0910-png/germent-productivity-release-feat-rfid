import { Moment } from 'moment';

export interface ISysFile {
  id?: number;
  fileName?: string;
  fileUri?: string;
  fileExtension?: string;
  size?: number;
  uploadedAt?: Moment;
}

export class SysFile implements ISysFile {
  constructor(
    public id?: number,
    public fileName?: string,
    public fileUri?: string,
    public fileExtension?: string,
    public size?: number,
    public uploadedAt?: Moment
  ) {}
}
