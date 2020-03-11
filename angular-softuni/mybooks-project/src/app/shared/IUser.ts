export interface IUser {
  _id: string;
  username: string;
  birthday: string;
  _acl: { creator: string };
  likes: string[];
  email: string;
  _kmd: { lmt: string, ect: string };
}
