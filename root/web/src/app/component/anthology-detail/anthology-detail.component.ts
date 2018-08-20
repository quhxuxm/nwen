import { Component, OnInit } from '@angular/core';
import {AnthologyDetail} from '../../vo/anthology-detail';
import {Card} from '../../vo/ui/card';

@Component({
  selector: 'nwen-anthology-detail',
  templateUrl: './anthology-detail.component.html',
  styleUrls: ['./anthology-detail.component.scss']
})
export class AnthologyDetailComponent implements OnInit {
  anthology: AnthologyDetail;
  articleCards: Card[];
  constructor() { }

  ngOnInit() {
  }

}
