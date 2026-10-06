export interface IProTenMua {
  id?: number;
  tenMua?: string;
}

export class ProTenMua implements IProTenMua {
  constructor(public id?: number, public tenMua?: string) {}
}
