import {Component, OnInit} from '@angular/core';
import {AnthologySummaryQueryCondition, AnthologySummaryService} from '../../service/anthology-summary.service';
import {ArticleSummaryQueryCondition, ArticleSummaryService} from '../../service/article-summary.service';
import {UrlService} from '../../service/url.service';
import {AnthologySummary} from '../../vo/anthology-summary';
import {ArticleSummary} from '../../vo/article-summary';
import {Card} from '../../vo/ui/card';

@Component({
  selector: 'nwen-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  recentCreateArticleCards: Card[];
  recentCreateAnthologies: Card[];
  recentUpdateAnthologies: Card[];
  hotAnthologies: Card[];
  hotArticles: Card[];
  topHotAnthology: Card;
  topHotArticle: Card;

  constructor(private articleSummaryService: ArticleSummaryService,
              private anthologySummaryService: AnthologySummaryService, private urlService: UrlService) {
    this.recentCreateArticleCards = null;
    this.topHotArticle = null;
  }

  ngOnInit() {
    this.loadTopHotArticle();
    this.loadTopHotAnthology();
    this.loadHotAnthologies();
    this.loadHotArticles();
    this.loadRecentCreateArticles();
    this.loadRecentCreateAnthologies();
    this.loadRecentUpdateAnthologies();
  }

  loadTopHotArticle() {
    const topHotArticleQueryCondition = new ArticleSummaryQueryCondition();
    topHotArticleQueryCondition.resultNumber = 1;
    const mostPopularArticleSummaries = this.articleSummaryService.query(topHotArticleQueryCondition);
    this.topHotArticle = mostPopularArticleSummaries.map(summary => {
      const result = this.wrapArticleSummaryToCard(summary);
      result.useCoverImageFilter = false;
      return result;
    })[0];
  }

  loadTopHotAnthology() {
    const topHotAnthologyQueryCondition = new AnthologySummaryQueryCondition();
    topHotAnthologyQueryCondition.resultNumber = 1;
    const topHotAnthologies = this.anthologySummaryService.query(topHotAnthologyQueryCondition);
    this.topHotAnthology = topHotAnthologies.map(summary => {
      const result = this.wrapAnthologySummaryToCard(summary);
      result.useCoverImageFilter = false;
      return result;
    })[0];
  }

  loadRecentCreateArticles() {
    const recentCreateArticleQueryCondition = new ArticleSummaryQueryCondition();
    recentCreateArticleQueryCondition.resultNumber = 6;
    const recentCreateArticles = this.articleSummaryService.query(recentCreateArticleQueryCondition);
    this.recentCreateArticleCards = recentCreateArticles.map(summary =>
      this.wrapArticleSummaryToCard(summary)
    );
  }

  loadRecentCreateAnthologies() {
    const recentCreateAnthologyQueryCondition = new AnthologySummaryQueryCondition();
    recentCreateAnthologyQueryCondition.resultNumber = 3;
    const recentCreateAnthology = this.anthologySummaryService.query(recentCreateAnthologyQueryCondition);
    this.recentCreateAnthologies = recentCreateAnthology.map(summary =>
      this.wrapAnthologySummaryToCard(summary)
    );
  }

  loadRecentUpdateAnthologies() {
    const recentUpdateAnthologyQueryCondition = new AnthologySummaryQueryCondition();
    recentUpdateAnthologyQueryCondition.resultNumber = 3;
    const recentUpdateAnthology = this.anthologySummaryService.query(recentUpdateAnthologyQueryCondition);
    this.recentUpdateAnthologies = recentUpdateAnthology.map(summary =>
      this.wrapAnthologySummaryToCard(summary)
    );
  }

  loadHotAnthologies() {
    const hotAnthologyQueryCondition = new AnthologySummaryQueryCondition();
    hotAnthologyQueryCondition.resultNumber = 3;
    const hotAnthologies = this.anthologySummaryService.query(hotAnthologyQueryCondition);
    this.hotAnthologies = hotAnthologies.map(summary =>
      this.wrapAnthologySummaryToCard(summary)
    );
  }

  loadHotArticles() {
    const hotArticleQueryCondition = new ArticleSummaryQueryCondition();
    hotArticleQueryCondition.resultNumber = 3;
    const hotArticles = this.articleSummaryService.query(hotArticleQueryCondition);
    this.hotArticles = hotArticles.map(summary =>
      this.wrapArticleSummaryToCard(summary)
    );
  }

  private wrapArticleSummaryToCard(summary: ArticleSummary): Card {
    const result = new Card();
    result.title = summary.title;
    result.subTitle = summary.authorNickName;
    result.content = summary.content;
    if (summary.coverImgId != null) {
      result.coverImageUrl = this.urlService.generateImageUrl(summary.coverImgId.toString());
    }
    if (summary.id != null) {
      result.titleRouterLink = this.urlService.generateArticleDetailRouterUrl(summary.id.toString());
      result.contentRouterLink = result.titleRouterLink;
      result.coverImageRouterLink = result.titleRouterLink;
    }
    if (summary.authorId != null) {
      result.subTitleRouterLink = this.urlService.generateAuthorDetailRouterUrl(summary.authorId.toString());
    }
    return result;
  }

  private wrapAnthologySummaryToCard(summary: AnthologySummary): Card {
    const result = new Card();
    result.title = summary.title;
    result.subTitle = summary.authorNickName;
    result.content = summary.content;
    if (summary.coverImgId != null) {
      result.coverImageUrl = this.urlService.generateImageUrl(summary.coverImgId.toString());
    }
    if (summary.id != null) {
      result.titleRouterLink = this.urlService.generateArticleDetailRouterUrl(summary.id.toString());
      result.contentRouterLink = result.titleRouterLink;
      result.coverImageRouterLink = result.titleRouterLink;
    }
    if (summary.authorId != null) {
      result.subTitleRouterLink = this.urlService.generateAuthorDetailRouterUrl(summary.authorId.toString());
    }
    return result;
  }
}
