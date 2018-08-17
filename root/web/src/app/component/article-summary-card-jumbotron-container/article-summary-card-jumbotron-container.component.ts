import {Component, Input, OnInit} from '@angular/core';
import {ArticleSummaryCard} from '../../vo/ui/article-summary-card';

@Component({
  selector: 'nwen-article-summary-card-jumbotron-container',
  templateUrl: './article-summary-card-jumbotron-container.component.html',
  styleUrls: ['./article-summary-card-jumbotron-container.component.scss']
})
export class ArticleSummaryCardJumbotronContainerComponent implements OnInit {
  @Input()
  summaryCards: ArticleSummaryCard[];
  @Input()
  showArticle: boolean;
  @Input()
  showCoverImage: boolean;

  constructor() {
    this.summaryCards = null;
    this.showArticle = true;
    this.showCoverImage = true;
  }

  ngOnInit() {
  }
}
