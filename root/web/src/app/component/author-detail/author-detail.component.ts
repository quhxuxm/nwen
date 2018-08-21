import {Component, OnInit} from '@angular/core';
import {AnthologyDetailService} from '../../service/anthology-detail.service';
import {AnthologySummaryQueryCondition, AnthologySummaryService} from '../../service/anthology-summary.service';
import {ArticleSummaryQueryCondition, ArticleSummaryService} from '../../service/article-summary.service';
import {CommentService} from '../../service/comment.service';
import {UrlService} from '../../service/url.service';
import {AnthologyDetail} from '../../vo/anthology-detail';
import {Comment} from '../../vo/comment';
import {Card} from '../../vo/ui/card';

@Component({
  selector: 'nwen-anthology-detail',
  templateUrl: './anthology-detail.component.html',
  styleUrls: ['./anthology-detail.component.scss']
})
export class AnthologyDetailComponent implements OnInit {
  anthology: AnthologyDetail;
  articleCards: Card[];
  comments: Comment[];
  authorIconImageUrl: string;
  authorUrl: string;
  relatedAnthologyCards: Card[];

  constructor(private anthologyDetailService: AnthologyDetailService,
              private anthologySummaryService: AnthologySummaryService,
              private articleSummaryService: ArticleSummaryService, private commentService: CommentService,
              private urlService: UrlService) {
  }

  ngOnInit() {
    this.anthology = this.anthologyDetailService.query(1);
    const anthologyArticleSummariesQueryCondition = new ArticleSummaryQueryCondition();
    anthologyArticleSummariesQueryCondition.resultNumber = 10;
    const articleSummaries = this.articleSummaryService.query(anthologyArticleSummariesQueryCondition);
    this.articleCards = articleSummaries.map(summary => {
      const card = new Card();
      card.title = summary.title;
      card.subTitle = summary.authorNickName;
      card.content = summary.content;
      card.coverImageUrl = this.urlService.generateImageUrl(summary.coverImgId.toString());
      return card;
    });
    this.comments = this.commentService.queryArticleComments(1);
    this.authorIconImageUrl = this.urlService.generateImageUrl(this.anthology.authorIconImgId.toString());
    this.authorUrl = this.urlService.generateAuthorDetailRouterUrl(this.anthology.authorId.toString());
    const relatedAnthologyQueryCondition = new AnthologySummaryQueryCondition();
    relatedAnthologyQueryCondition.resultNumber = 6;
    this.relatedAnthologyCards = this.anthologySummaryService.query(relatedAnthologyQueryCondition).map(summary => {
      const result = new Card();
      result.title = summary.title;
      result.subTitle = summary.authorNickName;
      result.content = summary.content;
      result.coverImageUrl = this.urlService.generateImageUrl(summary.coverImgId.toString());
      return result;
    });
  }
}
