import {Component, OnInit} from '@angular/core';
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

  constructor(private articleSummaryService: ArticleSummaryService, private commentService: CommentService,
              private urlService: UrlService) {
  }

  ngOnInit() {
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
  }
}
