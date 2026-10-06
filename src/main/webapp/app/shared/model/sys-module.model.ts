export interface ISysModule {
  id?: number;
  name?: string;
  caption?: string;
  imageUri?: string;
  sysGroupModuleId?: number;
}

export class SysModule implements ISysModule {
  constructor(
    public id?: number,
    public name?: string,
    public caption?: string,
    public imageUri?: string,
    public sysGroupModuleId?: number
  ) {}
}
