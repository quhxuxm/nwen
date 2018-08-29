import {Injectable} from '@angular/core';
import {ArticleDetail} from '../vo/article-detail';

@Injectable({
  providedIn: 'root'
})
export class ArticleDetailService {
  constructor() {
  }

  query(id: number): ArticleDetail {
    const result = new ArticleDetail();
    result.id = 1;
    result.title = `测试文章标题${id}`;
    result.anthologyTitle = `测试文集${id}`;
    result.authorNickName = `测试作者`;
    result.updateDate = new Date();
    result.authorIconId = 1;
    result.anthologyId = 1;
    result.authorId = 1;
    result.content = '';
    for (let i = 0; i < 20; i++) {
      result.content +=
        `<p>测试文章内容，测试文章内容。</p><p>测试文章内容：“测试文章内容：测试文章内容：测试文章内容,测试文章内容.”测试文章内容.</p>
          <script>alert("OK")</script><hr/><b>测试粗体内容</b><input type="text" name="username" />`;
    }
    return result;
  }
}
