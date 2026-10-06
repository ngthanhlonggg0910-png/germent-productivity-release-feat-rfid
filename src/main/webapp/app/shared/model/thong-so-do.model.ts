export interface IThongSoDo {
  id?: number;
  code?: string;
  ten?: string;
  dungSaiInchLabel?: string;
  dungSaiId?: number;
}

export class ThongSoDo implements IThongSoDo {
  constructor(public id?: number, public code?: string, public ten?: string, public dungSaiInchLabel?: string, public dungSaiId?: number) {}
}
