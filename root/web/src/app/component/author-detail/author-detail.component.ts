import {Component, OnInit} from '@angular/core';
import {AnthologySummaryQueryCondition, AnthologySummaryService} from '../../service/anthology-summary.service';
import {AuthorDetailQueryCondition, AuthorDetailService} from '../../service/author-detail.service';
import {CommentService} from '../../service/comment.service';
import {UrlService} from '../../service/url.service';
import {AuthorDetail} from '../../vo/author-detail';
import {Comment} from '../../vo/comment';
import {Card} from '../../vo/ui/card';

@Component({
  selector: 'nwen-author-detail',
  templateUrl: './author-detail.component.html',
  styleUrls: ['./author-detail.component.scss']
})
export class AuthorDetailComponent implements OnInit {
  author: AuthorDetail;
  comments: Comment[];
  authorIconImageUrl: string;
  authorUrl: string;
  anthologyCards: Card[];
  relatedAuthorCards: Card[];

  constructor(private authorDetailService: AuthorDetailService,
              private anthologySummaryService: AnthologySummaryService,
              private commentService: CommentService,
              private urlService: UrlService) {
  }

  ngOnInit() {
    const authorDetailQueryCondition = new AuthorDetailQueryCondition();
    authorDetailQueryCondition.resultNumber = 1;
    this.author = this.authorDetailService.query(authorDetailQueryCondition)[0];
    this.comments = this.commentService.queryAuthorComments(1);
    this.authorIconImageUrl = this.urlService.generateImageUrl(this.author.iconImageId.toString());
    this.authorUrl = this.urlService.generateAuthorDetailRouterUrl(this.author.id.toString());
    const relatedAnthologyQueryCondition = new AnthologySummaryQueryCondition();
    relatedAnthologyQueryCondition.resultNumber = 6;
    this.anthologyCards = this.anthologySummaryService.query(relatedAnthologyQueryCondition).map(summary => {
      const result = new Card();
      result.title = summary.title;
      result.subTitle = summary.authorNickName;
      result.content = summary.content;
      result.coverImageUrl = this.urlService.generateImageUrl(summary.coverImgId.toString());
      return result;
    });
    const relatedAuthorDetailQueryCondition = new AuthorDetailQueryCondition();
    relatedAuthorDetailQueryCondition.resultNumber = 6;
    this.relatedAuthorCards = this.authorDetailService.query(relatedAuthorDetailQueryCondition).map(detail => {
      const result = new Card();
      result.title = detail.nickName;
      result.content = detail.description;
      result.coverImageUrl = this.urlService.generateImageUrl(detail.iconImageId.toString());
      return result;
    });
  }
}
