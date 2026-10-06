export interface IMapControlButton {
  id?: number;
  sourceButtonCmndTopic?: string;
  sourceButtonId?: number;
  targetButtonCmndTopic?: string;
  targetButtonId?: number;
}

export class MapControlButton implements IMapControlButton {
  constructor(
    public id?: number,
    public sourceButtonCmndTopic?: string,
    public sourceButtonId?: number,
    public targetButtonCmndTopic?: string,
    public targetButtonId?: number
  ) {}
}
