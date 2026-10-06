export interface ISysRoleHasFunction {
  id?: number;
  activated?: boolean;
  sysFunctionId?: number;
  authorityName?: string;
  authorityId?: number;
}

export class SysRoleHasFunction implements ISysRoleHasFunction {
  constructor(
    public id?: number,
    public activated?: boolean,
    public sysFunctionId?: number,
    public authorityName?: string,
    public authorityId?: number
  ) {
    this.activated = this.activated || false;
  }
}
