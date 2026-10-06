export interface IVideo {
  id?: number;
  name?: string;
  timePlay?: string;
}

export class Video implements IVideo {
  constructor(public id?: number, public name?: string, public timePlay?: string) {}
}
