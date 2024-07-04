export interface LikedTopicsCardProps {
  id: number;
  topicName: string;
  topicDescription: string;
  topicCreatorName: string;
}

export interface MyTopicsCardProps {
  id: string;
  topicName: string;
  topicDescription: string;
  topicCreatorName: string;
}

export interface TopicsCardProps {
  id: string;
  topicName: string;
  topicDescription: string;
  topicCreatorName: string;
}

export interface MessagesCardProps {
  id: string;
  text: string;
  writer: string;
}

//Message wirter
export interface Writer {
  name: string;
  avatarUrl: string;
}
