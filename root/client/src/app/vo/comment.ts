export class Comment {
  commenterId:number;
  commenterNickname:string;
  commenterIconImgId:number;
  content:string;
  commentDate:Date;
  parent: Comment;
}
