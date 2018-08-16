import {Component, OnInit} from '@angular/core';
import {ArticleSummaryQueryCondition, ArticleSummaryService} from '../../service/article-summary.service';
import {ArticleSummary} from '../../vo/article-summary';
import {ArticleSummaryCard} from '../../vo/ui/article-summary-card';

@Component({
  selector: 'nwen-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  mostRecentArticleSummaryCards: ArticleSummaryCard[];
  mostPopularArticleSummaryCards: ArticleSummaryCard[];

  constructor(private articleSummaryService: ArticleSummaryService) {
    this.mostRecentArticleSummaryCards = null;
    this.mostPopularArticleSummaryCards = null;
  }

  ngOnInit() {
    let mostRecentArticleSummariesQueryCondition = new ArticleSummaryQueryCondition();
    mostRecentArticleSummariesQueryCondition.resultNumber = 6;
    const mostRecentArticleSummaries = this.articleSummaryService.query(mostRecentArticleSummariesQueryCondition);
    this.mostRecentArticleSummaryCards = mostRecentArticleSummaries.map(summary =>
      this.wrapArticleSummaryToCard(summary)
    );
    let mostPopularArticleSummariesQueryCondition = new ArticleSummaryQueryCondition();
    mostPopularArticleSummariesQueryCondition.resultNumber = 1
    const mostPopularArticleSummaries = this.articleSummaryService.query(mostPopularArticleSummariesQueryCondition);
    this.mostPopularArticleSummaryCards = mostPopularArticleSummaries.map(summary => {
      let result = this.wrapArticleSummaryToCard(summary);
      result.useFilter = false;
      return result;
    });
  }

  private wrapArticleSummaryToCard(summary: ArticleSummary): ArticleSummaryCard {
    const result = new ArticleSummaryCard();
    result.summary = summary;
    return result;
  }
}
