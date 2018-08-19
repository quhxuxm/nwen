import {Component, OnInit} from '@angular/core';
import {ArticleDetailService} from '../../service/article-detail.service';
import {ArticleSummaryQueryCondition, ArticleSummaryService} from '../../service/article-summary.service';
import {UrlService} from '../../service/url.service';
import {ArticleDetail} from '../../vo/article-detail';
import {Card} from '../../vo/ui/card';

@Component({
  selector: 'nwen-article-detail',
  templateUrl: './article-detail.component.html',
  styleUrls: ['./article-detail.component.scss']
})
export class ArticleDetailComponent implements OnInit {
  authorUrl: string;
  authorIconUrl: string;
  article: ArticleDetail;
  relatedArticleCards: Card[];

  constructor(private articleDetailService: ArticleDetailService, private articleSummaryService: ArticleSummaryService,
              private urlService: UrlService) {
    this.relatedArticleCards = [];
  }

  ngOnInit() {
    this.article = this.articleDetailService.query(1);
    this.authorUrl = this.urlService.generateAuthorDetailRouterUrl(this.article.authorId.toString());
    this.authorIconUrl = this.urlService.generateImageUrl(this.article.authorId.toString());
    const relatedArticleSummaryQueryCondition = new ArticleSummaryQueryCondition();
    relatedArticleSummaryQueryCondition.resultNumber = 3;
    const relatedArticleSummaries = this.articleSummaryService.query(relatedArticleSummaryQueryCondition);
    this.relatedArticleCards = relatedArticleSummaries.map(summary => {
      const result = new Card();
      result.title = summary.title;
      result.subTitle = summary.authorNickName;
      result.content = summary.content;
      result.coverImageUrl = this.urlService.generateImageUrl(summary.coverImgId.toString());
      return result;
    });
  }
}
