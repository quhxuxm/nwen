import {Component, Input, OnInit} from '@angular/core';
import {ArticleSummaryCard} from '../../vo/ui/article-summary-card';

@Component({
  selector: 'nwen-card-jumbotron-container',
  templateUrl: './card-jumbotron-container.component.html',
  styleUrls: ['./card-jumbotron-container.component.scss']
})
export class CardJumbotronContainerComponent implements OnInit {
  @Input()
  summaryCard: ArticleSummaryCard;
  @Input()
  showArticle: boolean;
  @Input()
  showCoverImage: boolean;

  constructor() {
    this.summaryCard = null;
    this.showArticle = true;
    this.showCoverImage = true;
  }

  ngOnInit() {
  }
}
