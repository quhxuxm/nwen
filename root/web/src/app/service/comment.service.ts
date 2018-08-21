import {Injectable} from '@angular/core';
import {Comment} from '../vo/comment';

@Injectable({
  providedIn: 'root'
})
export class CommentService {
  constructor() {
  }

  queryArticleComments(articleId: number): Comment[] {
    const result: Comment[] = [];
    for (let i = 0; i < 5; i++) {
      const comment = new Comment();
      comment.content = '测试评论内容，测试评论内容，测试评论内容，测试评论内容测试评论内容，测试评论内容测试评论内容测试评论内容';
      comment.commenterNickname = '测试评论者昵称';
      comment.commenterIconImgId = 1;
      comment.commenterId = i;
      comment.commentDate = new Date();
      result.push(comment);
    }
    return result;
  }

  queryAnthologyComments(articleId: number): Comment[] {
    const result: Comment[] = [];
    for (let i = 0; i < 5; i++) {
      const comment = new Comment();
      comment.content = '测试评论内容，测试评论内容，测试评论内容，测试评论内容测试评论内容，测试评论内容测试评论内容测试评论内容';
      comment.commenterNickname = '测试评论者昵称';
      comment.commenterIconImgId = 1;
      comment.commenterId = i;
      comment.commentDate = new Date();
      result.push(comment);
    }
    return result;
  }

  queryAuthorComments(articleId: number): Comment[] {
    const result: Comment[] = [];
    for (let i = 0; i < 5; i++) {
      const comment = new Comment();
      comment.content = '测试评论内容，测试评论内容，测试评论内容，测试评论内容测试评论内容，测试评论内容测试评论内容测试评论内容';
      comment.commenterNickname = '测试评论者昵称';
      comment.commenterIconImgId = 1;
      comment.commenterId = i;
      comment.commentDate = new Date();
      result.push(comment);
    }
    return result;
  }
}
