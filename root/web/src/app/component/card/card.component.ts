import {Component, Input, OnInit} from '@angular/core';
import {Card} from '../../vo/ui/card';

@Component({
  selector: 'nwen-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.column.scss',
    './card.component.jumbotron.scss']
})
export class CardComponent implements OnInit {
  @Input()
  card: Card;
  @Input()
  displayType: string;
  @Input()
  showRightBorder: boolean;

  constructor() {
    this.displayType = 'column';
    this.showRightBorder = true;
  }

  ngOnInit() {
  }
}
