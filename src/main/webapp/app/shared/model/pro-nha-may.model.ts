export interface IProNhaMay {
  id?: number;
  ten?: string;
  stt?: number;
  proCongTyTen?: string;
  proCongTyId?: number;
}

export class ProNhaMay implements IProNhaMay {
  constructor(public id?: number, public ten?: string, public stt?: number, public proCongTyTen?: string, public proCongTyId?: number) {}
}
