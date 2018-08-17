import {Component, Input, OnInit} from '@angular/core';
import {ArticleSummaryCard} from '../../vo/ui/article-summary-card';

@Component({
  selector: 'nwen-article-summary-card',
  templateUrl: './article-summary-card.component.html',
  styleUrls: ['./article-summary-card.component.column-container.scss',
    './article-summary-card.component.jumbotron-container.scss']
})
export class ArticleSummaryCardComponent implements OnInit {
  @Input()
  card: ArticleSummaryCard;
  @Input()
  isInLastColumn: boolean;
  @Input()
  containerType: string;
  @Input()
  showArticle: boolean;
  @Input()
  showCoverImage: boolean;

  constructor() {
    this.showArticle = true;
    this.showCoverImage = true;
    this.containerType = 'column';
    this.isInLastColumn = true;
  }

  ngOnInit() {
  }
}
