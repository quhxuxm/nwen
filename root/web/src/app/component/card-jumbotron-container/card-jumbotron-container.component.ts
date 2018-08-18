import {Component, Input, OnInit} from '@angular/core';
import {Card} from '../../vo/ui/card';

@Component({
  selector: 'nwen-card-jumbotron-container',
  templateUrl: './card-jumbotron-container.component.html',
  styleUrls: ['./card-jumbotron-container.component.scss']
})
export class CardJumbotronContainerComponent implements OnInit {
  @Input()
  card: Card;
  @Input()
  cardDisplayType: string;

  constructor() {
    this.card = null;
    this.cardDisplayType = 'jumbotron';
  }

  ngOnInit() {
  }
}
