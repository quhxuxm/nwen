import {Component, OnInit} from '@angular/core';
import {ArticleDetailService} from '../../service/article-detail.service';
import {ArticleDetail} from '../../vo/article-detail';

@Component({
  selector: 'nwen-article-detail',
  templateUrl: './article-detail.component.html',
  styleUrls: ['./article-detail.component.scss']
})
export class ArticleDetailComponent implements OnInit {
  article: ArticleDetail;

  constructor(private articleDetailService: ArticleDetailService) {
  }

  ngOnInit() {
    this.article = this.articleDetailService.query(1);
  }
}
