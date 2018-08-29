import {Injectable} from '@angular/core';
import {ArticleSummary} from '../vo/article-summary';

export class ArticleSummaryQueryCondition {
  resultNumber: number;
}

@Injectable({
  providedIn: 'root'
})
export class ArticleSummaryService {
  constructor() {
  }

  private generateMockSummaries(articleNumber: number): ArticleSummary[] {
    const summaries: ArticleSummary[] = [];
    for (let i = 0; i < articleNumber; i++) {
      const s =
        new ArticleSummary();
      s.id = i;
      s.title = `测试文章的标题ABC ${i}`;
      s.content = `测试文章总结，测试文章总结，测试文章总结，测试文章总结，测试文章总结，测试文章总结，测试文章总结，测试文章总结，测试文章总结，`;
      s.createDate = new Date();
      s.updateDate = new Date();
      s.authorId = i;
      s.authorNickName = `测试作者ABC-昵称 ${i}`;
      s.coverImgId = 1;
      summaries.push(s);
    }
    return summaries;
  }

  query(condition: ArticleSummaryQueryCondition): ArticleSummary[] {
    return this.generateMockSummaries(condition.resultNumber);
  }
}
