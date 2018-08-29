import {Injectable} from '@angular/core';
import {AnthologySummary} from '../vo/anthology-summary';
import {ArticleSummary} from '../vo/article-summary';

export class AnthologySummaryQueryCondition {
  resultNumber: number;
}

@Injectable({
  providedIn: 'root'
})
export class AnthologySummaryService {
  constructor() {
  }

  private generateMockSummaries(anthologyNumber: number): ArticleSummary[] {
    const summaries: AnthologySummary[] = [];
    for (let i = 0; i < anthologyNumber; i++) {
      const s =
        new AnthologySummary();
      s.id = i;
      s.title = `测试文集标题`;
      s.content = `测试文集总结，测试文集总结测试文集总结测试文集总结测试文集总结`;
      s.createDate = new Date();
      s.updateDate = new Date();
      s.authorId = i;
      s.authorNickName = `测试作者ABC-昵称 ${i}`;
      s.coverImgId = 1;
      summaries.push(s);
    }
    return summaries;
  }

  query(condition: AnthologySummaryQueryCondition): ArticleSummary[] {
    return this.generateMockSummaries(condition.resultNumber);
  }
}
