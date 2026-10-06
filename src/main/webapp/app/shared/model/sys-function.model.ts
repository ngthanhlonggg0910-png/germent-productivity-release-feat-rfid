export interface ISysFunction {
  id?: number;
  name?: string;
  caption?: string;
  level?: number;
  imageUri?: string;
  eventName?: string;
  hotkey?: string;
  sysPermissionId?: number;
  sysModuleId?: number;
}

export class SysFunction implements ISysFunction {
  constructor(
    public id?: number,
    public name?: string,
    public caption?: string,
    public level?: number,
    public imageUri?: string,
    public eventName?: string,
    public hotkey?: string,
    public sysPermissionId?: number,
    public sysModuleId?: number
  ) {}
}
