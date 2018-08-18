import {Component, Input, OnInit} from '@angular/core';
import {ArticleSummaryCard} from '../../vo/ui/article-summary-card';

@Component({
  selector: 'nwen-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.column-container.scss',
    './card.component.jumbotron-container.scss']
})
export class CardComponent implements OnInit {
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
