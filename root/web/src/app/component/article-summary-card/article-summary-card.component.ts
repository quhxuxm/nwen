import {Component, Input, OnInit} from '@angular/core';
import {ArticleSummaryCard} from '../../vo/ui/article-summary-card';

@Component({
  selector: 'nwen-article-summary-card',
  templateUrl: './article-summary-card.component.html',
  styleUrls: ['./article-summary-card.component.scss']
})
export class ArticleSummaryCardComponent implements OnInit {
  @Input()
  card: ArticleSummaryCard;
  @Input()
  isInLastColumn: boolean;

  constructor() {
  }

  ngOnInit() {
  }
}
