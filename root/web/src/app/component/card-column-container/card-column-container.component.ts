import {BreakpointObserver, Breakpoints} from '@angular/cdk/layout';
import {Component, Input, OnInit} from '@angular/core';
import {Card} from '../../vo/ui/card';

@Component({
  selector: 'nwen-card-column-container',
  templateUrl: './card-column-container.component.html',
  styleUrls: ['./card-column-container.component.scss']
})
export class CardColumnContainerComponent implements OnInit {
  private BREAKPOINT_COLUMN_NUMBER: Map<string[], number> = new Map([
    [[Breakpoints.XSmall], 1],
    [[Breakpoints.Small], 2],
    [[Breakpoints.Medium, Breakpoints.Large], 3],
    [[Breakpoints.XLarge], 4]
  ]);
  @Input()
  maxColumnNumber: number;
  @Input()
  cards: Card[];
  @Input()
  cardLayoutType: string;
  @Input()
  cardDisplayType: string;
  __columnNumber: number;
  __cardColumns: Card[][];

  constructor(private breakpointObserver: BreakpointObserver) {
    this.cards = [];
    this.__cardColumns = [];
    this.maxColumnNumber = null;
    this.cardLayoutType = 'column';
  }

  ngOnInit() {
    if (!this.maxColumnNumber || this.maxColumnNumber > 4 || this.maxColumnNumber <= 0) {
      this.maxColumnNumber = 3;
    }
    this.__columnNumber = this.maxColumnNumber;
    if (this.cards.length < this.__columnNumber) {
      this.__columnNumber = this.cards.length;
    }
    this.BREAKPOINT_COLUMN_NUMBER.forEach((v, k, m) => {
      this.breakpointObserver.observe(k)
        .subscribe(
          () => {
            if (this.breakpointObserver.isMatched(k)) {
              this.__columnNumber = v;
              if (this.__columnNumber > this.maxColumnNumber) {
                this.__columnNumber = this.maxColumnNumber;
              }
              if (this.__columnNumber > this.cards.length) {
                this.__columnNumber = this.cards.length;
              }
              // while (this.cards.length % this.__columnNumber !== 0) {
              //   this.__columnNumber--;
              // }
              this.refreshCardColumns();
            }
          }
        );
    });
  }

  private refreshCardColumns(): void {
    this.__cardColumns = [];
    for (let i = 0; i < this.__columnNumber; i++) {
      this.__cardColumns.push([]);
    }
    for (let i = 0; i < this.cards.length; i++) {
      const columnIndex = i % this.__columnNumber;
      this.__cardColumns[columnIndex].push(this.cards[i]);
    }
  }
}
