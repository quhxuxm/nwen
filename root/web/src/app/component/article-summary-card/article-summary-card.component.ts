import {Component, Input, OnInit} from '@angular/core';
import {ArticleSummary} from '../../vo/article-summary';

@Component({
  selector: 'nwen-article-summary-card',
  templateUrl: './article-summary-card.component.html',
  styleUrls: ['./article-summary-card.component.scss']
})
export class ArticleSummaryCardComponent implements OnInit {
  @Input()
  articleSummary: ArticleSummary;
  @Input()
  isLastColumn: boolean;

  constructor() {
  }

  ngOnInit() {
  }
}
