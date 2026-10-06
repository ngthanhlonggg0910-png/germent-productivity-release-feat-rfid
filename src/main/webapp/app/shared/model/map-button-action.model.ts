import { NVButtonState } from 'app/shared/model/enumerations/nv-button-state.model';

export interface IMapButtonAction {
  id?: number;
  sourceAliasState?: number;
  targetState?: NVButtonState;
  trackingTypeName?: string;
  trackingTypeId?: number;
}

export class MapButtonAction implements IMapButtonAction {
  constructor(
    public id?: number,
    public sourceAliasState?: number,
    public targetState?: NVButtonState,
    public trackingTypeName?: string,
    public trackingTypeId?: number
  ) {}
}
