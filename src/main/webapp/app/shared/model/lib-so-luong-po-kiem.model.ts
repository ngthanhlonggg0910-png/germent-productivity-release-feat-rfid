export interface ILibSoLuongPoKiem {
  id?: number;
  value?: number;
}

export class LibSoLuongPoKiem implements ILibSoLuongPoKiem {
  constructor(public id?: number, public value?: number) {}
}
