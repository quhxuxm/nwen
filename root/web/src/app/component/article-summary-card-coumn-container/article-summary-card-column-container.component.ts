import {BreakpointObserver, Breakpoints} from '@angular/cdk/layout';
import {Component, Input, OnInit} from '@angular/core';
import {ArticleSummaryCard} from '../../vo/ui/article-summary-card';

@Component({
  selector: 'nwen-article-summary-card-column-container',
  templateUrl: './article-summary-card-column-container.component.html',
  styleUrls: ['./article-summary-card-column-container.component.scss']
})
export class ArticleSummaryCardColumnContainerComponent implements OnInit {
  private BREAKPOINT_COLUMN_NUMBER: Map<string[], number> = new Map([
    [[Breakpoints.XSmall, Breakpoints.Small], 1],
    [[Breakpoints.Medium], 2],
    [[Breakpoints.Large], 3],
    [[Breakpoints.XLarge], 4]
  ]);
  @Input()
  maxColumnNumber: number;
  @Input()
  summaryCards: ArticleSummaryCard[];
  @Input()
  showArticle: boolean;
  @Input()
  showCoverImage: boolean;
  columnNumber: number;
  summaryCardColumns: ArticleSummaryCard[][];

  constructor(private breakpointObserver: BreakpointObserver) {
    this.summaryCards = null;
    this.summaryCardColumns = null;
    this.maxColumnNumber = null;
    this.showArticle = true;
    this.showCoverImage = true;
  }

  ngOnInit() {
    if (!this.summaryCards) {
      console.error('No summaries assigned.');
      return;
    }
    if (!this.maxColumnNumber || this.maxColumnNumber > 4 || this.maxColumnNumber <= 0) {
      console.warn('No max column number assigned, use 3 columns as default.');
      this.maxColumnNumber = 3;
    }
    this.columnNumber = this.maxColumnNumber;
    if (this.summaryCards.length < this.columnNumber) {
      console.info('The summaries number is smaller than the column number, use the summary number as column number.');
      this.columnNumber = this.summaryCards.length;
    }
    this.BREAKPOINT_COLUMN_NUMBER.forEach((v, k, m) => {
      this.breakpointObserver.observe(k)
        .subscribe(
          () => {
            if (this.breakpointObserver.isMatched(k)) {
              this.columnNumber = v;
              if (this.columnNumber > this.maxColumnNumber) {
                this.columnNumber = this.maxColumnNumber;
              }
              if (this.columnNumber > this.summaryCards.length) {
                this.columnNumber = this.summaryCards.length;
              }
              this.refreshSummaryColumns();
            }
          }
        );
    });
  }

  private refreshSummaryColumns(): void {
    this.summaryCardColumns = [];
    for (let i = 0; i < this.columnNumber; i++) {
      this.summaryCardColumns.push([]);
    }
    for (let i = 0; i < this.summaryCards.length; i++) {
      const columnIndex = i % this.columnNumber;
      this.summaryCardColumns[columnIndex].push(this.summaryCards[i]);
    }
  }
}
