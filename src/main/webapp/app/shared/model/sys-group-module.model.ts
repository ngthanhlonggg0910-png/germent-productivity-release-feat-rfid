export interface ISysGroupModule {
  id?: number;
  name?: string;
  caption?: string;
  primaryImageUri?: string;
  secondaryImageUri?: string;
}

export class SysGroupModule implements ISysGroupModule {
  constructor(
    public id?: number,
    public name?: string,
    public caption?: string,
    public primaryImageUri?: string,
    public secondaryImageUri?: string
  ) {}
}
