export interface IDoAm {
  id?: number;
  value?: number;
  display?: string;
}

export class DoAm implements IDoAm {
  constructor(public id?: number, public value?: number, public display?: string) {}
}
