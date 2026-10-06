export interface IDoKimMd {
  id?: number;
  value?: number;
  display?: string;
}

export class DoKimMd implements IDoKimMd {
  constructor(public id?: number, public value?: number, public display?: string) {}
}
