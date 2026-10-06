export interface ISysPermission {
  id?: number;
  name?: string;
  caption?: string;
  level?: number;
  imageUri?: string;
  eventName?: string;
  hotkey?: string;
}

export class SysPermission implements ISysPermission {
  constructor(
    public id?: number,
    public name?: string,
    public caption?: string,
    public level?: number,
    public imageUri?: string,
    public eventName?: string,
    public hotkey?: string
  ) {}
}
