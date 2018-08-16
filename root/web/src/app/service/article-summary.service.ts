import {Injectable} from '@angular/core';
import {ArticleSummary} from '../vo/article-summary';

@Injectable({
  providedIn: 'root'
})
export class ArticleSummaryService {
  constructor() {
  }

  private generateMockSummaries(): ArticleSummary[] {
    const summaries: ArticleSummary[] = [];
    for (let i = 1; i <= 12; i++) {
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

  getSummariesOrderByBookmarkNumber(): ArticleSummary[] {
    console.log('getSummariesOrderByBookmarkNumber');
    return this.generateMockSummaries();
  }

  getSummariesOrderByCreatedDate(): ArticleSummary[] {
    console.log('getSummariesOrderByCreatedDate');
    return this.generateMockSummaries();
  }

  getSummariesOrderByViewNumber(): ArticleSummary[] {
    console.log('getSummariesOrderByViewNumber');
    return this.generateMockSummaries();
  }

  getSummariesOrderByPraiseNumber(): ArticleSummary[] {
    console.log('getSummariesOrderByPraiseNumber');
    return this.generateMockSummaries();
  }

  getSummariesOrderByCommentNumber(): ArticleSummary[] {
    console.log('getSummariesOrderByCommentNumber');
    return this.generateMockSummaries();
  }

  getSummariesOfLabels(labels: string[]): ArticleSummary[] {
    console.log('getSummariesOfLabels with labels = ' + labels.join(','));
    return this.generateMockSummaries();
  }

  getSummariesOfAnthology(anthologyId: number): ArticleSummary[] {
    console.log('getSummariesOfAnthology with anthology id = ' + anthologyId);
    return this.generateMockSummaries();
  }
}
