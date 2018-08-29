import {Component, Input, OnInit} from '@angular/core';
import {UrlService} from '../../../service/url.service';
import {ArticleDetail} from '../../../vo/article-detail';

@Component({
  selector: 'nwen-article-cover',
  templateUrl: './article-cover.component.html',
  styleUrls: ['./article-cover.component.scss']
})
export class ArticleCoverComponent implements OnInit {
  @Input()
  article: ArticleDetail;
  authorIconUrl: string;
  anthologyUrl: string;
  authorUrl: string;

  constructor(private urlService: UrlService) {
  }

  ngOnInit() {
    this.authorIconUrl = this.urlService.generateImageUrl(this.article.authorIconId.toString());
    this.anthologyUrl = this.urlService.generateAnthologyDetailRouterUrl(this.article.anthologyId.toString());
    this.authorUrl = this.urlService.generateAuthorDetailRouterUrl(this.article.authorId.toString());
  }
}
