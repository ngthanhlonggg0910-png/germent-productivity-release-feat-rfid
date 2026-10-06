import { TopicType } from 'app/shared/model/enumerations/topic-type.model';
import { GroupButtonType } from 'app/shared/model/enumerations/group-button-type.model';

export interface IGroupTopic {
  id?: number;
  name?: string;
  topicType?: TopicType;
  type?: GroupButtonType;
}

export class GroupTopic implements IGroupTopic {
  constructor(public id?: number, public name?: string, public topicType?: TopicType, public type?: GroupButtonType) {}
}
